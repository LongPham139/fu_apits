package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.CourseCreateDTO;
import com.apits.apitssystembackend.constant.course.CourseErrorMessage;
import com.apits.apitssystembackend.constant.course.CourseStatus;
import com.apits.apitssystembackend.constant.course.CourseSuccessMessage;
import com.apits.apitssystembackend.constant.level.LevelErrorMessage;
import com.apits.apitssystembackend.entity.Course;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.CourseRepository;
import com.apits.apitssystembackend.repository.LevelRepository;
import com.apits.apitssystembackend.response.CourseResponse;
import com.apits.apitssystembackend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CourseResponse> getAllCourse(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Course> pageResult = courseRepository.findAll(pageable);
        List<CourseResponse> list = new ArrayList<>();
        if (pageResult.hasContent()) {
            for (Course course :
                    pageResult.getContent()) {
                CourseResponse courseResponse = modelMapper.map(course, CourseResponse.class);
                list.add(courseResponse);
            }
        } else throw new ListEmptyException(CourseErrorMessage.LIST_COURSE_IS_EMPTY);

        return list;
    }

    @Override
    public CourseResponse getCourseById(int id) {
        Course course = courseRepository.findCourseById(id)
                .orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse getCourseByName(String name) {
        Course course = courseRepository.findCourseByName(name)
                .orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse createCourse(CourseCreateDTO dto) {
        CourseResponse courseResponse = null;
        Optional<Course> courseOptional = courseRepository.findCourseByName(dto.getName());
        if (courseOptional.isPresent())
            throw new ExistException(CourseErrorMessage.COURSE_EXIST);
        else {
            Course course = Course.builder()
                    .link(dto.getLink())
                    .name(dto.getName())
                    .status(CourseStatus.ACTIVATED)
                    .build();
            courseRepository.save(course);
            courseResponse = modelMapper.map(course, CourseResponse.class);
        }
        return courseResponse;
    }

    @Override
    public CourseResponse updateCourse(int id, CourseCreateDTO dto) {
        Course course = courseRepository.findCourseById(id)
                .orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
        course.setLink(dto.getLink());
        course.setName(dto.getName());
        courseRepository.save(course);
        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse activeCourse(int id) {
        Course course = courseRepository.findCourseById(id)
                .orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
        course.setStatus(CourseStatus.ACTIVATED);
        courseRepository.save(course);
        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse disableCourse(int id) {
        Course course = courseRepository.findCourseById(id)
                .orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
        course.setStatus(CourseStatus.DISABLED);
        courseRepository.save(course);
        return modelMapper.map(course, CourseResponse.class);
    }


}
