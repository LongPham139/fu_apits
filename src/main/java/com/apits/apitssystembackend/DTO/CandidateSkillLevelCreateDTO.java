package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CandidateSkillLevelCreateDTO {
    private int candidateId;
    private int skillId;
    private int levelId;
}
