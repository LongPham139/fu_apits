package com.apits.apitssystembackend.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentRequestSearchDTO {
    private String jobName;
    private String industry;
    private String jobLevel;
    private String typeOfWork;
    private String salaryFrom;
    private String salaryTo;
    private String experience;
}
