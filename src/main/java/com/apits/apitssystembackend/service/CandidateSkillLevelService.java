package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.CandidateSkillLevelCreateDTO;
import com.apits.apitssystembackend.entity.CandidateSkillLevel;
import com.apits.apitssystembackend.response.CandidateSkillLevel.CandidateSkillLevelResponse;
import com.apits.apitssystembackend.response.CandidateSkillLevel.CandidateSkillsLevelsResponse;
import org.springframework.stereotype.Service;

@Service
public interface CandidateSkillLevelService {
    CandidateSkillLevelResponse findById(int id);
    CandidateSkillLevelResponse create(CandidateSkillLevelCreateDTO dto);
    CandidateSkillsLevelsResponse getListSkillByCandidateId(int id, String status);

}
