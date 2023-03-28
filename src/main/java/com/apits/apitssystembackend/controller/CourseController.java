package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.CourseCreateDTO;
import com.apits.apitssystembackend.constant.course.CourseSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.CourseResponse;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@Tag(name = "Course Controller")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @GetMapping("getAllCourse")
    public ResponseEntity<ListResponseDTO> getAllCoursePaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ListResponseDTO<CourseResponse> responseDTO = new ListResponseDTO<>();
        List<CourseResponse> list = courseService.getAllCourse(pageNo, pageSize);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setData(list);
        responseDTO.setMessage(CourseSuccessMessage.GET_ALL_SUCCESSFULL);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getCourseById/{id}")
    public ResponseEntity<ResponseDTO> getCourseById(@PathVariable(name = "id") int id){
        ResponseDTO<CourseResponse> responseDTO = new ResponseDTO<>();
        CourseResponse course = courseService.getCourseById(id);
        responseDTO.setData(course);
        responseDTO.setMessage(CourseSuccessMessage.GET_COURSE_BY_ID_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getCourseByName")
    public ResponseEntity<ResponseDTO> getCourseByName(@RequestParam String name){
        ResponseDTO<CourseResponse> responseDTO = new ResponseDTO<>();
        CourseResponse courseResponse = courseService.getCourseByName(name);
        responseDTO.setData(courseResponse);
        responseDTO.setMessage(CourseSuccessMessage.GET_COURSE_BY_NAME_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("createCourse")
    public ResponseEntity<ResponseDTO> createCourse(@RequestBody CourseCreateDTO dto){
        ResponseDTO<CourseResponse> responseDTO = new ResponseDTO<>();
        CourseResponse courseResponse = courseService.createCourse(dto);
        responseDTO.setData(courseResponse);
        responseDTO.setMessage(CourseSuccessMessage.CREATE_COURSE_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("disableCourse")
    public ResponseEntity<ResponseDTO> disableCourse(@RequestParam int id){
        ResponseDTO<CourseResponse> responseDTO = new ResponseDTO<>();
        CourseResponse courseResponse = courseService.disableCourse(id);
        responseDTO.setData(courseResponse);
        responseDTO.setMessage(CourseSuccessMessage.DISABLE_COURSE_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("activeCourse")
    public ResponseEntity<ResponseDTO> activeCourse(@RequestParam int id){
        ResponseDTO<CourseResponse> responseDTO = new ResponseDTO<>();
        CourseResponse courseResponse = courseService.activeCourse(id);
        responseDTO.setData(courseResponse);
        responseDTO.setMessage(CourseSuccessMessage.ACTIVE_COURSE_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
