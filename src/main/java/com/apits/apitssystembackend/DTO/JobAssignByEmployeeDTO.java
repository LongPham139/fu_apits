package com.apits.apitssystembackend.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobAssignByEmployeeDTO {
    private String name;
    private String email;
}
