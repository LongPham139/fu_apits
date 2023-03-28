package com.apits.apitssystembackend.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionEmployeeUpdateDTO {
    private int positionId;
    private int empId;
}
