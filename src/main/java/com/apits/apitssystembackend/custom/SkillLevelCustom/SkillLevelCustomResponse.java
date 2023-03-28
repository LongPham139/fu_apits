package com.apits.apitssystembackend.custom.SkillLevelCustom;

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
public class SkillLevelCustomResponse {
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//    private Skill skill;
    private int skillId;
    private String skillName;
    private String skillImage;
    private String skillStatus;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Level> candidateLevelList;


}
