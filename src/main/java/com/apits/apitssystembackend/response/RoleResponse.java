package com.apits.apitssystembackend.response;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {
    private int roleID;
    private String roleName;
    private String roleStatus;
}
