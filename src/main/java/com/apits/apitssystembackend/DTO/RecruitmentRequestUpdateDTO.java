package com.apits.apitssystembackend.DTO;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentRequestUpdateDTO {
    private Date expiryDate;
    private String industry;
    private String name;
    private int amount;
    private String jobLevel;
    private String experience;
    private String typeOfWork;
    private String salaryFrom;
    private String salaryTo;
    private String description;
    private String requirement;
}
