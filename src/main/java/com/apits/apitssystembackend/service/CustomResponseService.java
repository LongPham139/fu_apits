package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.custom.CandidateCustom.CandidateCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Level_CourseResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Skill_LevelsCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.SpecialCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Special_SkillsCustomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomResponseService {
    CandidateCustomResponse getListSpecSkillLevelCourseByCandidateId(int id, String status);
    // With Special 
    SpecialCustomResponse getSpecialCustomBySpecId(int specId);
    Level_CourseResponse getListCourseByLevelId(int levelId);
    Skill_LevelsCustomResponse getLevelBySkillId(int skillId);
    Special_SkillsCustomResponse getSkillsBySpecial(int specialId);
    List<Special_SkillsCustomResponse> getAllSpecial();
    //End
    // With Candidate
   Skill_LevelsCustomResponse getLevelBySkillId(int skillId, List<Integer> listlevel);
   Special_SkillsCustomResponse getSkillsBySpecial(int specialId, List<Integer> listSkill, List<Integer> listLevel);

    


}
