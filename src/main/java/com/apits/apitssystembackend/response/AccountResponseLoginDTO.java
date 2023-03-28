package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountResponseLoginDTO {
    private int id;
    private String email;
    private Date createAt;
    private String status;
    private String notificationToken;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Position position;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Role role;
    private Object information;
//    private Candidate candidate;
//    private Employee employee;
//    private Enterprise enterprise;
}
