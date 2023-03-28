package com.apits.apitssystembackend.response.Skill_Level_Course;

import com.apits.apitssystembackend.entity.Course;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Skill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillsLevelCourseResponse {
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Skill> skillList;
}
