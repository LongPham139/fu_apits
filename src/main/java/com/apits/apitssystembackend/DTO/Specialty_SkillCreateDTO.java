package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialty_SkillCreateDTO {
    private int specialtyId;
    private int skillId;
}
