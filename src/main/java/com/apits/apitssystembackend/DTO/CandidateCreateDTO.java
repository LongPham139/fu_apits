package com.apits.apitssystembackend.DTO;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CandidateCreateDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String image;
    private String gender;
    private Date dob;
    private String address;
    private String payment;
    private String description;
    private String cv;
}
