package com.apits.apitssystembackend.response;

import com.apits.apitssystembackend.entity.Position;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private int id;
    private String name;
    private String employeeCode;
    private String image;
    private String jobLevel;
    private Date dob;
    private String phone;
    private String address;
    private String status;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Position position;
}
