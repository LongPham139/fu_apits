package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillCreateDTO {
    private String name;
    private String image;

}
