package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.SkillLevelCourseCreateDTO;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.constant.skill_level.Skill_LevelSuccessMessage;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillCoursesResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillLevelCourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillLevelsCourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillsLevelCourseResponse;
import com.apits.apitssystembackend.service.CustomResponseService;
import com.apits.apitssystembackend.service.SkillLevelCourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skill-level-course")
@RequiredArgsConstructor
@Tag(name = "Skill Level Course Controller")
public class SkillLevelCourseController {
    private final SkillLevelCourseService skillLevelService;
    private final CustomResponseService customResponseService;
    @GetMapping("getSkillLevelById/{id}")
    public ResponseEntity<ResponseDTO> getSkillLevelById(@PathVariable(name = "id")int id){
        ResponseDTO<SkillLevelsCourseResponse> responseDTO = new ResponseDTO<>();
        SkillLevelsCourseResponse skillLevelResponse = skillLevelService.getSkillLevelById(id);
        responseDTO.setData(skillLevelResponse);
        responseDTO.setMessage(Skill_LevelSuccessMessage.GET_SKILL_LEVEL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getListCourseBySkillId")
    public ResponseEntity<ResponseDTO> getListCourseBySkillId(@RequestParam int id){
        ResponseDTO<SkillCoursesResponse> responseDTO = new ResponseDTO<>();
        SkillCoursesResponse data = skillLevelService.getListCourseBySkillId(id);
        responseDTO.setData(data);
        responseDTO.setMessage(Skill_LevelSuccessMessage.GET_LIST_COURSE_BY_SKILL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("createSkillLevel")
    public ResponseEntity<ResponseDTO> createSkillLevel(SkillLevelCourseCreateDTO dto){
        ResponseDTO<SkillLevelCourseResponse> responseDTO = new ResponseDTO<>();
        SkillLevelCourseResponse data = skillLevelService.createSkillLevel(dto);
        responseDTO.setData(data);
        responseDTO.setMessage(Skill_LevelSuccessMessage.CREATE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("getListSkillsByLevelId")
    public ResponseEntity<ResponseDTO> getListSkillsByLevelId(@RequestParam int id){
        ResponseDTO<SkillsLevelCourseResponse> responseDTO = new ResponseDTO<>();
        SkillsLevelCourseResponse data = skillLevelService.getListSkillByLevelId(id);
        responseDTO.setData(data);
        responseDTO.setMessage(Skill_LevelSuccessMessage.GET_LIST_SKILL_BY_LEVEL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("getListLevelBySkillId")
    public ResponseEntity<ResponseDTO> getListLevelBySkillId(@RequestParam int id){
        ResponseDTO<SkillLevelsCourseResponse> responseDTO = new ResponseDTO<>();
        SkillLevelsCourseResponse data = skillLevelService.getListLevelBySkillId(id);
        responseDTO.setData(data);
        responseDTO.setMessage(Skill_LevelSuccessMessage.GET_LIST_LEVEL_BY_SKILL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

//    @GetMapping("findCourseIdByLevelId")
//    public ResponseEntity<ResponseDTO> findCourseIdByLevelId(@RequestParam int id){
//        ResponseDTO<Level_CourseCustomResponse> responseDTO = new ResponseDTO<>();
//        Level_CourseCustomResponse data = customResponseService.getListCourseByLevelId(id);
//        responseDTO.setData(data);
//        responseDTO.setMessage(Skill_LevelSuccessMessage.GET_LIST_LEVEL_BY_SKILL_ID_SUCCESS);
//        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
//        return ResponseEntity.ok().body(responseDTO);
//    }
}
