package com.apits.apitssystembackend.response.Specialty_Skill;

import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.entity.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specialty_SkillResponse {
    private int id;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Specialty specialty;
    private Skill skill;
}
