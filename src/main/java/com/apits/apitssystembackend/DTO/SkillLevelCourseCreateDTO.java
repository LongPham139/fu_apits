package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillLevelCourseCreateDTO {
    private int skillId;
    private int levelId;
    private int courseId;
}
