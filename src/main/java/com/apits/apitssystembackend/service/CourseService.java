package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.CourseCreateDTO;
import com.apits.apitssystembackend.response.CourseResponse;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseResponse> getAllCourse(int pageNo, int pageSize);
    CourseResponse getCourseById(int id);
    CourseResponse getCourseByName(String name);
    CourseResponse createCourse(CourseCreateDTO dto);
    CourseResponse updateCourse(int id, CourseCreateDTO dto);
    CourseResponse activeCourse(int id);
    CourseResponse disableCourse(int id);
}
