package com.apits.apitssystembackend.DTO;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeUpdateDTO {
    private String image;
    private String gender;
    private String name;
    private Date Dob;
    private String phone;
    private String address;
}
