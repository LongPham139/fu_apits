package com.apits.apitssystembackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateUpdateDTO {
    private String name;
    private String phone;
    private String image;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String address;
    private String cv;
    private String payment;
    private String description;
}
