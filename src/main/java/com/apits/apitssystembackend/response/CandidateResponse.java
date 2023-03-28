package com.apits.apitssystembackend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponse {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private String gender;
    private Date dob;
    private String address;
    private String status;
    private String cv;
    private String momoNumber;
    private Date createAt;
}
