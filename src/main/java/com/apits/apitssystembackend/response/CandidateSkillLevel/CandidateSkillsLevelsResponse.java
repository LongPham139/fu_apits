package com.apits.apitssystembackend.response.CandidateSkillLevel;

import com.apits.apitssystembackend.custom.SkillLevelCustom.SkillLevelCustomResponse;
import com.apits.apitssystembackend.entity.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CandidateSkillsLevelsResponse {
    private List<SkillLevelCustomResponse> candidateSkills;

}
