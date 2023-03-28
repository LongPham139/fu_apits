package com.apits.apitssystembackend.response.CandidateSkillLevel;

import com.apits.apitssystembackend.custom.SkillLevelCustom.SkillLevelCustomResponse;
import com.apits.apitssystembackend.entity.Candidate;
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
public class CandidateSkillLevelResponse {
    private int id;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Candidate candidate;
    private SkillLevelCustomResponse skillLevelCustomResponse;
    private String status;
}
