package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.SkillCreateDTO;
import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.response.SkillResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {
    List<SkillResponse> getAllSkillPaging(int pageNo, int pageSize);
    SkillResponse getSkillById(int id);
//    List<SkillResponse> getSkillByLevelId(int levelId);
    SkillResponse createSkill(SkillCreateDTO dto);
}
