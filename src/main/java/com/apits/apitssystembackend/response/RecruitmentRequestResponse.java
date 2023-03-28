package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Enterprise;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentRequestResponse {
    private int id;
    private Date date;
    private Date expiryDate;
    private String industry;
    private String name;
    private int amount;
    private String jobLevel;
    private String experience;
    private String typeOfWork;
    private String salaryDetail;
    private String description;
    private String requirement;
    private String status;
    private Enterprise creator;
}
