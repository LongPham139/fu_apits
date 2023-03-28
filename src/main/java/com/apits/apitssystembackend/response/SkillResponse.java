package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Level;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillResponse {
    private int id;
    private String status;
    private String name;
    private String image;
}
