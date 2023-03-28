package com.apits.apitssystembackend.response.Skill_Level_Course;

import com.apits.apitssystembackend.entity.Course;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillCoursesResponse {
    private int skillId;
    private String skillName;
    private String skillImage;
    private String skillStatus;
    private List<Course> courseList;
}
