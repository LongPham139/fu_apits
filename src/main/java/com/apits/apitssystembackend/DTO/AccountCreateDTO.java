package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountCreateDTO {
    private String email;
    private String password;
}

