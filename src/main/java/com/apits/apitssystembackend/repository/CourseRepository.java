package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    public Optional<Course> findCourseById(int id);
    public List<Course> findAll();
    public Optional<Course> findCourseByName(String name);
}
