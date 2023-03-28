package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountUpdateDTO {
    private String status;
    private String roleName;
}
