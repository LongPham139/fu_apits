package com.apits.apitssystembackend.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CourseCreateDTO {
    private String name;
    private String link;
}
