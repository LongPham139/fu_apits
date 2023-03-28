package com.apits.apitssystembackend.response.CandidateSkillLevelCourse;

import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.SkillLevelCourse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CandidateSkillLevelCourseResponse {
    private int id;
    private Candidate candidate;
    private SkillLevelCourse skillLevelCourse;
}
