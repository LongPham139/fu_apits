package com.apits.apitssystembackend.response.Skill_Level_Course;

import com.apits.apitssystembackend.custom.SpecialCustom.Level_CourseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillLevelsCourseResponse {
    private int skillId;
    private String skillName;
    private String skillImage;
    private String status;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Level_CourseResponse> skillLevels;

}
