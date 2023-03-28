package com.apits.apitssystembackend.custom.SpecialCustom;

import com.apits.apitssystembackend.entity.Skill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Skill_LevelsCustomResponse {
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Skill skill;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Level_CourseResponse> skillLevels;
}
