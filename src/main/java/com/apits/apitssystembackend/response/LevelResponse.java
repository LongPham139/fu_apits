package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Specialty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LevelResponse {
    private int id;
    private String status;
    private String name;
    private int specialtyId;
}
