package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.Specialty_SkillCreateDTO;
import com.apits.apitssystembackend.entity.SpecializeSkill;
import com.apits.apitssystembackend.response.Specialty_Skill.SpecialtiesSkillResponse;
import com.apits.apitssystembackend.response.Specialty_Skill.SpecialtySkillsResponse;
import com.apits.apitssystembackend.response.Specialty_Skill.Specialty_SkillResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Specialty_SkillService {
    public Specialty_SkillResponse createSpecializeSkill(Specialty_SkillCreateDTO dto);
    //getByID, getListSpecBySkillId, getListSkillBySpec
    public Specialty_SkillResponse getSpecializeSkillById(int id);
    public SpecialtiesSkillResponse getListSpecBySkillId(int skillId);
    public SpecialtySkillsResponse getListSkillBySpecicalId(int specId);
}
