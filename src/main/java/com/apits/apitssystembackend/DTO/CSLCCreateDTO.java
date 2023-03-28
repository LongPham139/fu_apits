package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CSLCCreateDTO {
    private int candidateId;
    private int skillId;
    private int levelId;
    private int courseId;
}
