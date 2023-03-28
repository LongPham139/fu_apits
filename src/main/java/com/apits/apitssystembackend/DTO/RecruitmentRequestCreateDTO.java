package com.apits.apitssystembackend.DTO;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentRequestCreateDTO {
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
    private int enterpriseId;
    private List<Integer> skillIds;
}
