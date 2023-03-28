package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.SkillLevelCourseCreateDTO;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillCoursesResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillLevelCourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillLevelsCourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillsLevelCourseResponse;
import org.springframework.stereotype.Service;

@Service
public interface SkillLevelCourseService {
    public SkillLevelCourseResponse createSkillLevel(SkillLevelCourseCreateDTO dto);
    public SkillLevelsCourseResponse getSkillLevelById(int id);
    public SkillsLevelCourseResponse getListSkillByLevelId(int levelId);
    public SkillLevelsCourseResponse getListLevelBySkillId(int skillId);
    public SkillCoursesResponse getListCourseBySkillId(int skillId);
}
