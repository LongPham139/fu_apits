package com.apits.apitssystembackend.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListJobAssignByEmployee {
    private int requestId;
    private int employeeId;
    private List<JobAssignByEmployeeDTO> ListJobAssignByEmployee;
}
