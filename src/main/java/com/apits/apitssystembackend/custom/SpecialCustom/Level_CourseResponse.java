package com.apits.apitssystembackend.custom.SpecialCustom;

import com.apits.apitssystembackend.entity.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Level_CourseResponse {
    private int levelId;
    private String levelName;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Course> levelCourses;
    private String levelStatus;
}
