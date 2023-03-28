package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialtyResponse {
    private int id;
    private String name;
}
