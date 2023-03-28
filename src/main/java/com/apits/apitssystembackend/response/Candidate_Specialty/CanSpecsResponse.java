package com.apits.apitssystembackend.response.Candidate_Specialty;

import com.apits.apitssystembackend.entity.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CanSpecsResponse {
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Specialty> candidateSpecialList;
}
