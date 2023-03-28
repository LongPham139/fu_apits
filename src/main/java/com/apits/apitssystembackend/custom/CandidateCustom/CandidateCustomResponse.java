package com.apits.apitssystembackend.custom.CandidateCustom;

import com.apits.apitssystembackend.custom.SpecialCustom.Special_SkillsCustomResponse;
import com.apits.apitssystembackend.entity.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CandidateCustomResponse {
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Candidate candidate;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Special_SkillsCustomResponse> listSpecial;
}
