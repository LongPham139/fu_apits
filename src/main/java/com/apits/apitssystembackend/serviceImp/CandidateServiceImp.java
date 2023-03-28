package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.CandidateCreateDTO;
import com.apits.apitssystembackend.constant.account.AccountFailMessage;
import com.apits.apitssystembackend.constant.account.AccountStatus;
import com.apits.apitssystembackend.constant.candidate.CandidateFailMessage;
import com.apits.apitssystembackend.DTO.CandidateUpdateDTO;
import com.apits.apitssystembackend.constant.candidate.CandidateStatus;
import com.apits.apitssystembackend.constant.role.RoleName;
import com.apits.apitssystembackend.entity.Account;
import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.Provider;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.AccountRepository;
import com.apits.apitssystembackend.repository.CandidateRepository;
import com.apits.apitssystembackend.repository.RoleRepository;
import com.apits.apitssystembackend.request.EmailRequest;
import com.apits.apitssystembackend.response.CandidateResponse;
import com.apits.apitssystembackend.service.AccountService;
import com.apits.apitssystembackend.service.CandidateService;
import com.apits.apitssystembackend.service.JwtService;
import com.apits.apitssystembackend.entity.*;
import com.apits.apitssystembackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.stream.Collectors;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImp implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final ModelMapper mapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SpecializeSkillRepository specializeSkillRepository;
    private final CandidateSpecializeRepository candidateSpecializeRepository;

    @Override
    public List<CandidateResponse> getAllCandidate() {
        List<CandidateResponse> result = new ArrayList<>();
        List<Candidate> list = candidateRepository.findAll();
        if (list == null) {
            throw new ListEmptyException(CandidateFailMessage.LIST_CANDIDATE_IS_EMPTY);
        }
        for (Candidate candidate : list) {
            CandidateResponse tmp = mapper.map(candidate, CandidateResponse.class);
            result.add(tmp);
        }
        return result;
    }

    @Override
    public CandidateResponse getCandidateByEmail(EmailRequest email) {
        Candidate response = candidateRepository.findCandidateByEmail(email.getEmail());
        if (response == null) {
            throw new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND);
        }
        CandidateResponse result = mapper.map(response, CandidateResponse.class);
        return result;
    }

    @Override
    public CandidateResponse getCandidateById(int id) {
        Candidate response = candidateRepository.findCandidateById(id);
        if (response == null) {
            throw new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND);
        }
        CandidateResponse result = mapper.map(response, CandidateResponse.class);
        return result;
    }

    @Override
    public CandidateResponse createCandidate(CandidateCreateDTO dto) {
        Account tmp = accountRepository.findAccountByEmail(dto.getEmail());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        if (tmp == null) {
            tmp = Account.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .status(AccountStatus.ACTIVATED)
                    .createAt(Date.valueOf(dateFormat))
                    .provider(Provider.LOCAL)
                    .role(roleRepository.findByName(RoleName.ROLE_CANDIDATE))
                    .build();
            var jwtToken = jwtService.generateToken(tmp);
            tmp.setNotificationToken(jwtToken);
            accountRepository.save(tmp);
        }else throw new ExistException(AccountFailMessage.ACCOUNT_EXIST);
        Candidate candidate = Candidate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .image(dto.getImage())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .payment(dto.getPayment())
                .description(dto.getDescription())
                .cv(dto.getCv())
                .createAt(Date.valueOf(dateFormat))
                .dob(Date.valueOf(dateFormat))
                .status(CandidateStatus.ACTIVATED)
                .account(tmp)
                .build();
        candidateRepository.save(candidate);
        candidate.setCandidateCode("CDD" + 1000 + candidate.getId());
        candidateRepository.save(candidate);
        tmp.setCandidate(candidate);
        accountRepository.save(tmp);
        return mapper.map(candidate, CandidateResponse.class);
    }

    @Override
    public CandidateResponse updateCandidate(int id, CandidateUpdateDTO dto) {
        Candidate candidate = candidateRepository.findCandidateById(id);
        if (candidate != null) {

            candidate.setGender(dto.getGender());
            candidate.setName(dto.getName());
            candidate.setPhone(dto.getPhone());
            candidate.setImage(dto.getImage());
            candidate.setDob(dto.getDob());
            candidate.setAddress(dto.getAddress());
            candidate.setCv(dto.getCv());
            candidateRepository.save(candidate);
            return mapper.map(candidate, CandidateResponse.class);
        } else
            throw new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND);
    }

    @Override
    public CandidateResponse removeCandidate(int id) {
        Candidate tmp = candidateRepository.findCandidateById(id);
        if(tmp != null){
            tmp.setStatus(CandidateStatus.DISABLED);
            candidateRepository.save(tmp);
            return mapper.map(tmp, CandidateResponse.class);
        }else throw new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND);
    }

    @Override
    public List<CandidateResponse> findCandidateBySkillIds(List<Integer> skillIds) {
        List<SpecializeSkill> specializeSkills = specializeSkillRepository.findBySkillIds(skillIds, skillIds.size());
        List<Candidate> candidates = new ArrayList<>();
        List<Integer> candidateIds = new ArrayList<>();
        List<CandidateResponse> result = new ArrayList<>();

        specializeSkills.forEach(s -> {
            List<CandidateSpecialize> candidateSpecializes = candidateSpecializeRepository.findBySpecializeId(s.getSpecializeId());
            if (Objects.nonNull(candidateSpecializes) && !candidateSpecializes.isEmpty()) {
                candidateIds.addAll(candidateSpecializes.stream().map(CandidateSpecialize::getCandidateId).collect(Collectors.toList()));
            }
        });

        if (!candidateIds.isEmpty()) {
            candidates.addAll(candidateRepository.findByIds(candidateIds));
        }

        if (!candidates.isEmpty()) {
            candidates.forEach(c -> result.add(mappedCandidate(c)));
        }

        return result;
    }

    private CandidateResponse mappedCandidate(Candidate candidate) {
        CandidateResponse tmp = new CandidateResponse();
        tmp.setImage(candidate.getImage());
        tmp.setId(candidate.getId());
        tmp.setDob(candidate.getDob());
        tmp.setEmail(candidate.getEmail());
        tmp.setAddress(candidate.getAddress());
        tmp.setGender(candidate.getGender());
        tmp.setName(candidate.getName());
        tmp.setPhone(candidate.getPhone());
        tmp.setStatus(candidate.getStatus());
        return tmp;
    }
}
