package com.apits.apitssystembackend.custom.SpecialCustom;

import com.apits.apitssystembackend.entity.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Special_SkillsCustomResponse {
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Specialty specialty;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Skill_LevelsCustomResponse> specialSkills;
}
