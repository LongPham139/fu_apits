package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.Provider;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private int accountId;

    private String token;
    private String roleName;
    private String email;
    private String status;
    private Candidate candidate;
    private Provider provider;
}
