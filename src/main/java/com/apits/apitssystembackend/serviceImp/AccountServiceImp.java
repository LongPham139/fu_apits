package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.AccountCreateDTO;
import com.apits.apitssystembackend.DTO.AccountUpdateDTO;
import com.apits.apitssystembackend.DTO.EmployeeCreateDTO;
import com.apits.apitssystembackend.constant.account.AccountFailMessage;
import com.apits.apitssystembackend.constant.account.AccountStatus;
import com.apits.apitssystembackend.constant.account.AccountSuccessMessage;
import com.apits.apitssystembackend.constant.apits.ApitsMail;
import com.apits.apitssystembackend.constant.employee.EmployeeStatus;
import com.apits.apitssystembackend.constant.position.PositionErrorMessage;
import com.apits.apitssystembackend.constant.role.RoleFailMessage;
import com.apits.apitssystembackend.constant.role.RoleName;
import com.apits.apitssystembackend.entity.*;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.*;
import com.apits.apitssystembackend.response.AccountEmployeeResponse;
import com.apits.apitssystembackend.response.AccountResponse;
import com.apits.apitssystembackend.service.AccountService;
import com.apits.apitssystembackend.service.JwtService;
import com.apits.apitssystembackend.service.RoleService;
import com.apits.apitssystembackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;
    private final CandidateRepository candidateRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final StringUtils stringUtils;
    private final ModelMapper modelMapper;
    private final PositionRepository positionRepository;


    @Override
    public List<AccountResponse> getAllAccount() {
        List<AccountResponse> result = new ArrayList<>();
        List<Account> list = accountRepository.findAll();
        if (list.size() > 0) {
            for (Account account : list
            ) {
                AccountResponse tmp = mappedAccount(account);
                result.add(tmp);
            }
        } else {
            throw new ListEmptyException(AccountFailMessage.LIST_ACCOUNT_IS_EMPTY);
        }
        return result;
    }

    @Override
    public List<AccountResponse> getAllPaging(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Account> pageResult = accountRepository.findAll(pageable);
        List<AccountResponse> list = new ArrayList<>();
        if (pageResult.hasContent()) {
            for (Account account : pageResult.getContent()
            ) {
                AccountResponse accountResponse = mappedAccount(account);
                list.add(accountResponse);
            }
        } else
            throw new ListEmptyException(AccountFailMessage.LIST_ACCOUNT_IS_EMPTY);
        return list;
    }

    @Override
    public List<AccountResponse> getAllAccountsByRole(int roleId, int pageNo, int pageSize) {
        Role role = roleRepository.findById(roleId);
        if (role == null) {
            throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Account> pageResult = accountRepository.findByRole(role, pageable);
        List<AccountResponse> list = new ArrayList<>();
        if (pageResult.hasContent()) {
            for (Account acc : pageResult.getContent()) {
                AccountResponse tmp = mappedAccount(acc);
                list.add(tmp);
            }
        } else
            throw new ListEmptyException(AccountFailMessage.LIST_ACCOUNT_IS_EMPTY);
        return list;
    }

    @Override
    public AccountResponse createAccountAdmin(AccountCreateDTO dto) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        Account account = accountRepository.findAccountByEmail(dto.getEmail());
        if (account == null) {
            account = Account.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(roleService.getRoleByName(RoleName.ROLE_ADMIN))
                    .provider(Provider.LOCAL)
                    .createAt(java.sql.Date.valueOf(dateFormat))
                    .status(AccountStatus.ACTIVATED)
                    .build();
            var jwtToken = jwtService.generateToken(account);
            account.setNotificationToken(jwtToken);
            accountRepository.save(account);
        }
        return mappedAccount(account);
    }


    @Override
    public AccountEmployeeResponse createEmployee(EmployeeCreateDTO dto) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        String email = "";
        String genNewEmail = "";
        String name = stringUtils.getShortName(dto.getName());
        Position position = positionRepository.findByName(dto.getPositionName()).orElseThrow(()->new NotFoundException(PositionErrorMessage.POSITION_NOT_EXIST));
        List<Account> tmp = accountRepository.findLastAccountLikeName(name);
        Account account = Account.builder()
                .password(passwordEncoder.encode("123"))
                .status(AccountStatus.ACTIVATED)
                .role(roleService.getRoleByName(RoleName.ROLE_EMPLOYEE))
                .createAt(java.sql.Date.valueOf(dateFormat))
                .provider(Provider.LOCAL)
                .build();
        if (tmp.isEmpty()) {
            email = stringUtils.getShortName(dto.getName()) + ApitsMail.MAIL_APITS;
            account.setEmail(email);
        } else {
            for (int i = 0; i < tmp.size(); i++) {
                email = tmp.get(tmp.size()-1).getEmail();
            }
            genNewEmail = stringUtils.generateNewEmail(email, ApitsMail.MAIL_APITS);
            account.setEmail(genNewEmail);
        }
        accountRepository.save(account);
        Employee employee = Employee.builder()
                .image(dto.getImage())
                .phone(dto.getPhone())
                .dob(dto.getDob())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .employeeName(dto.getName())
                .account(account)
                .position(position)
                .status(EmployeeStatus.ACTIVATE)
                .build();
        var jwtToken = jwtService.generateToken(account);
        account.setNotificationToken(jwtToken);
        employeeRepository.save(employee);
        employee.setEmployeeCode("EMP" + 1000 + employee.getId());
        employeeRepository.save(employee);
        account.setEmployee(employee);
        accountRepository.save(account);
        return modelMapper.map(account, AccountEmployeeResponse.class);
    }

    @Override
    public AccountResponse updateAccount(int id, AccountUpdateDTO accountUpdateDTO) {
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            account.setStatus(accountUpdateDTO.getStatus());
            account.setRole(roleRepository.findByName(accountUpdateDTO.getRoleName()));
            accountRepository.save(account);
            return mappedAccount(account);
        } else throw new NotFoundException(AccountFailMessage.ACCOUNT_NOT_FOUND);
    }

    @Override
    public String deleteAccount(int id) {
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            account.setStatus(AccountStatus.DISABLED);
            return AccountSuccessMessage.DELETE_ACCOUNT_SUCCESSFULL;
        } else return AccountFailMessage.DELETE_ACCOUNT_FAIL;
    }

    @Override
    public AccountResponse removeAccount(int id) {
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            account.setStatus(AccountStatus.DISABLED);
            account.setNotificationToken("");
            accountRepository.save(account);
        } else
            throw new NotFoundException(AccountFailMessage.ACCOUNT_NOT_FOUND);
        return mappedAccount(account);
    }

    @Override
    public AccountResponse getAccountById(int id) {
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            return mappedAccount(account);
        } else
            throw new NotFoundException(AccountFailMessage.ACCOUNT_NOT_FOUND);
    }

    @Override
    public Account getAccountByEmail(String email) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account == null)
            throw new NotFoundException(AccountFailMessage.ACCOUNT_NOT_FOUND);
        return account;
    }


    private AccountResponse mappedAccount(Account account) {
        AccountResponse tmp = new AccountResponse();
        tmp.setId(account.getId());
        tmp.setEmail(account.getEmail());
        tmp.setRole(account.getRole());
        tmp.setStatus(account.getStatus());
        tmp.setCreateAt(account.getCreateAt());
        tmp.setNotificationToken(account.getNotificationToken());
        return tmp;
    }

}
