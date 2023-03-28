package com.apits.apitssystembackend.response.Specialty_Skill;

import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.entity.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialtySkillsResponse {
    private Specialty specialty;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Skill> skillList;
}
