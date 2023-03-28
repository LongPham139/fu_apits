package com.apits.apitssystembackend.custom.SpecialCustom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SpecialCustomResponse {
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Special_SkillsCustomResponse> listSpecial;
}
