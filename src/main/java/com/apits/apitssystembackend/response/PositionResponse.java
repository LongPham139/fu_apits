package com.apits.apitssystembackend.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionResponse {
    private int id;
    private String name;
    private String status;
    private int numberUsePosition;

}
