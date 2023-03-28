package com.apits.apitssystembackend.response.Candidate_Specialty;

import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CanSpecResponse {
    private int id;
    private String status;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Candidate candidate;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Specialty specialty;
}
