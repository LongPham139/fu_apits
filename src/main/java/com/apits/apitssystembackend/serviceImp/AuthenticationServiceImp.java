package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.CandidateCreateDTO;
import com.apits.apitssystembackend.DTO.EnterpriseCreateDTO;
import com.apits.apitssystembackend.constant.account.AccountFailMessage;
import com.apits.apitssystembackend.constant.account.AccountStatus;
import com.apits.apitssystembackend.constant.enterprise.EnterpriseStatus;
import com.apits.apitssystembackend.constant.role.RoleFailMessage;
import com.apits.apitssystembackend.constant.role.RoleName;
import com.apits.apitssystembackend.entity.*;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.jwt.JWTConfig;
import com.apits.apitssystembackend.repository.AccountRepository;
import com.apits.apitssystembackend.repository.CandidateRepository;
import com.apits.apitssystembackend.repository.EnterpriseRepository;
import com.apits.apitssystembackend.repository.RoleRepository;
import com.apits.apitssystembackend.request.AuthenticateRequest;
import com.apits.apitssystembackend.response.*;
import com.apits.apitssystembackend.service.AuthenticationService;
import com.apits.apitssystembackend.service.JwtService;
import com.apits.apitssystembackend.service.RoleService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final CandidateRepository candidateRepository;
    private final JWTConfig jwtConfig;
    private final EnterpriseRepository enterpriseRepository;


    @Override
    public AuthenticationResponse registerForCandidate(CandidateCreateDTO request) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        Account account = accountRepository.findAccountByEmail(request.getEmail());
        if (account != null) {
            throw new ExistException(AccountFailMessage.ACCOUNT_EXIST);
        } else {
            account = Account.builder()
                    .provider(Provider.LOCAL)
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(roleService.getRoleByName(RoleName.ROLE_CANDIDATE))
                    .createAt(java.sql.Date.valueOf(dateFormat))
                    .status(AccountStatus.ACTIVATED)
                    .build();
            var jwtToken = jwtService.generateToken(account);
            account.setNotificationToken(jwtToken);
            accountRepository.save(account);
            Candidate candidate = Candidate.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .cv(request.getCv())
                    .description(request.getDescription())
                    .payment(request.getPayment())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .gender(request.getGender())
                    .dob(request.getDob())
                    .image(request.getImage())
                    .account(account)
                    .createAt(java.sql.Date.valueOf(dateFormat))
                    .status(AccountStatus.ACTIVATED)
                    .build();
            candidateRepository.save(candidate);
            candidate.setCandidateCode("CDD" + (1000 + candidate.getId()));
            candidateRepository.save(candidate);
            account.setCandidate(candidate);
            accountRepository.save(account);
            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        }
    }

    @Override
    public AccountResponseLoginDTO login(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var account = accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException(AccountFailMessage.ACCOUNT_NOT_FOUND));
        var jwtToken = jwtService.generateToken(account);
        AccountResponseLoginDTO dto = AccountResponseLoginDTO.builder()
                .id(account.getId())
                .status(account.getStatus())
                .createAt(account.getCreateAt())
                .role(account.getRole())
                .notificationToken(jwtToken)
                .email(account.getEmail())
                .build();
        if (account.getCandidate() != null) {
//            dto.setCandidate(account.getCandidate());
            dto.setInformation(account.getCandidate());
        } else if (account.getEmployee() != null) {
//            dto.setEmployee(account.getEmployee());
            dto.setPosition(account.getEmployee().getPosition());
            dto.setInformation(account.getEmployee());
        } else if (account.getEnterprise() != null) {
//            dto.setEnterprise(account.getEnterprise());
            dto.setInformation(account.getEnterprise());
        }
        return dto;
    }

    @Override
    public LoginResponseDTO loginGoogle(String token) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(RoleName.ROLE_CANDIDATE);
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObject = new JSONObject(body);

        String email = jsonObject.get("email").toString();
        String image = jsonObject.get("picture").toString();
        String name = jsonObject.get("name").toString();
        LoginResponseDTO loginResponseDTO = null;

        Account account = accountRepository.findAccountByEmail(email);
        if (account == null) {
            account = registerAccountForGoogleLogin(email, name, image, token);
        }
        if (account.getNotificationToken() == null) {
            account.setNotificationToken(token);
            accountRepository.save(account);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(account.getEmail(), null);

        String tokenResponse = Jwts.builder().setSubject(authentication.getName())
                .claim(("authorities"), simpleGrantedAuthorities).claim("id", account.getId())
                .setIssuedAt((new Date())).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(14)))
                .signWith(jwtConfig.secretKey()).compact();

        loginResponseDTO = LoginResponseDTO.builder()
                .accountId(account.getId())
                .email(account.getEmail())
                .status(account.getStatus())
                .roleName(account.getRole().getName())
                .candidate(account.getCandidate())
                .token(tokenResponse)
                .provider(account.getProvider())
                .build();
        return loginResponseDTO;
    }

    @Override
    public Account registerAccountForGoogleLogin(String email, String name, String image, String token) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        Role role = roleRepository.findByName(RoleName.ROLE_CANDIDATE);
        if (role == null) {
            throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
        }
        Candidate candidate = candidateRepository.findCandidateByEmail(email);
        if (candidate == null) {
            candidate = Candidate.builder()
                    .email(email)
                    .image(image)
                    .name(name)
                    .dob(java.sql.Date.valueOf("2000-01-01"))
                    .status(AccountStatus.ACTIVATED)
                    .build();
        } else {
            candidate.setImage(image);
            candidate.setDob(java.sql.Date.valueOf("2000-01-01"));
        }
        Account account = Account.builder()
                .email(email)
                .role(role)
                .candidate(candidate)
                .createAt(java.sql.Date.valueOf(dateFormat))
                .notificationToken(token)
                .provider(Provider.GOOGLE)
                .status(AccountStatus.ACTIVATED)
                .password(passwordEncoder.encode(""))
                .build();
        candidateRepository.save(candidate);
        candidate.setCandidateCode("CDD" + 1000 + candidate.getId());
        candidateRepository.save(candidate);
        Account credentialInRepo = accountRepository.save(account);
        candidate.setAccount(credentialInRepo);
        candidateRepository.save(candidate);
        return credentialInRepo;
    }

    @Override
    public Account changePassword(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null) {
            account.setPassword(passwordEncoder.encode(password));
            accountRepository.save(account);
            return account;
        } else
            throw new NotFoundException(AccountFailMessage.ACCOUNT_NOT_FOUND);
    }

    @Override
    public AccountResponseLoginDTO registerForEnterprise(EnterpriseCreateDTO dto) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        Account account = accountRepository.findAccountByEmail(dto.getEmail());
        if (account != null) {
            throw new ExistException(AccountFailMessage.ACCOUNT_EXIST);
        } else {
            account = Account.builder()
                    .provider(Provider.LOCAL)
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(roleService.getRoleByName(RoleName.ROLE_ENTERPRISE))
                    .createAt(java.sql.Date.valueOf(dateFormat))
                    .status(AccountStatus.ACTIVATED)
                    .build();
            var jwtToken = jwtService.generateToken(account);
            account.setNotificationToken(jwtToken);
            accountRepository.save(account);
            Enterprise enterprise = Enterprise.builder()
                    .name(dto.getName())
                    .phone(dto.getPhone())
                    .email(dto.getEmail())
                    .address(dto.getAddress())
                    .status(EnterpriseStatus.ACTIVATE)
                    .account(account)
                    .build();
            enterpriseRepository.save(enterprise);
            account.setEnterprise(enterprise);
            accountRepository.save(account);
            return AccountResponseLoginDTO.builder()
                    .id(account.getId())
                    .status(account.getStatus())
                    .createAt(account.getCreateAt())
                    .role(account.getRole())
                    .notificationToken(jwtToken)
                    .email(account.getEmail())
                    .information(enterprise)
                    .build();
        }
    }


}
