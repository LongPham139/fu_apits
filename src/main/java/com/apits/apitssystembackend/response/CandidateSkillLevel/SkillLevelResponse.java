package com.apits.apitssystembackend.response.CandidateSkillLevel;

import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Skill;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillLevelResponse {
    private Skill skill;
    private List<Level> levelList;
}
