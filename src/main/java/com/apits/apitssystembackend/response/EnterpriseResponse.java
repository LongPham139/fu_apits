package com.apits.apitssystembackend.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnterpriseResponse {
    private int id;
    private String name;
    private String address;
    private String status;
    private String phone;
}
