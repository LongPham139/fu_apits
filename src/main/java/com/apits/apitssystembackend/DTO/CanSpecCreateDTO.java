package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanSpecCreateDTO {
    private int candidateId;
    private int specialId;
}
