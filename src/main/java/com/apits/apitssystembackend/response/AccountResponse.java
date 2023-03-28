package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountResponse {
    private int id;
    private String email;
    private Date createAt;
    private String status;
    private String notificationToken;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Role role;

}
