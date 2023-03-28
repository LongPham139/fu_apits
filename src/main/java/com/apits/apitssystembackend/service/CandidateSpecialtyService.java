package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.CanSpecCreateDTO;
import com.apits.apitssystembackend.response.Candidate_Specialty.CanSpecResponse;
import com.apits.apitssystembackend.response.Candidate_Specialty.CanSpecsResponse;
import com.apits.apitssystembackend.response.Candidate_Specialty.CansSpecResponse;
import org.springframework.stereotype.Service;

@Service
public interface CandidateSpecialtyService {
    public CanSpecResponse getCanSpecById(int id);
    CanSpecResponse create(CanSpecCreateDTO dto);
    CanSpecsResponse getSpecialByCanId(int candidateId);
    CansSpecResponse getCandidatesBySpecialId(int specialId);
}
