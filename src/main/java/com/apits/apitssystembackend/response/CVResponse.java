package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Candidate;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVResponse {
    private int id;
    private String linkCV;
    private String status;
    private int candidateId;
}
