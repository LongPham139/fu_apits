package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Employee;
import com.apits.apitssystembackend.entity.Position;
import com.apits.apitssystembackend.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountEmployeeResponse {
    private int id;
    private String email;
    private Date createAt;
    private String status;
    private String notificationToken;
    private Role role;
    private Employee employee;
}
