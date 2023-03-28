package com.apits.apitssystembackend.response.Skill_Level_Course;

import com.apits.apitssystembackend.entity.Course;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Skill;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillLevelCourseResponse {
    private int id;
    private Skill skill;
    private Level level;
    private Course course;
}
