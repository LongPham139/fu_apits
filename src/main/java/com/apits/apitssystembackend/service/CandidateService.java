package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.CandidateCreateDTO;
import com.apits.apitssystembackend.DTO.CandidateUpdateDTO;
import com.apits.apitssystembackend.request.EmailRequest;
import com.apits.apitssystembackend.response.CandidateResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CandidateService {
    List<CandidateResponse> getAllCandidate();
    CandidateResponse getCandidateByEmail(EmailRequest email);
    CandidateResponse getCandidateById(int id);
    CandidateResponse createCandidate(CandidateCreateDTO candidateCreateDTO);
    CandidateResponse updateCandidate(int id, CandidateUpdateDTO dto);
    CandidateResponse removeCandidate(int id);
    List<CandidateResponse> findCandidateBySkillIds(List<Integer> skillIds);
}
