package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.RecruitmentRequest;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignCandidateResponse {
    private int id;
    private Date date;
    private String status;
    private String screeningStatus;
    private RecruitmentRequest recruitmentRequest;
    private Candidate candidate;
}
