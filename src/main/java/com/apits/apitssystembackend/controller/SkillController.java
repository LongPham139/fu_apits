package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.LevelCreateDTO;
import com.apits.apitssystembackend.DTO.SkillCreateDTO;
import com.apits.apitssystembackend.constant.level.LevelSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.constant.skill.SkillSuccessMessage;
import com.apits.apitssystembackend.response.LevelResponse;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.SkillResponse;
import com.apits.apitssystembackend.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skill")
@Tag(name = "Skill Controller")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping("getAllPaging")
    public ResponseEntity<ListResponseDTO> getAllLevelPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ListResponseDTO<SkillResponse> responseDTO = new ListResponseDTO<>();
        List<SkillResponse> list = skillService.getAllSkillPaging(pageNo, pageSize);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setData(list);
        responseDTO.setMessage(SkillSuccessMessage.GET_ALL_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getSkillById/{id}")
    public ResponseEntity<ResponseDTO> getSkillById(@PathVariable(name = "id") int id){
        ResponseDTO<SkillResponse> responseDTO = new ResponseDTO<>();
        SkillResponse skill = skillService.getSkillById(id);
        responseDTO.setData(skill);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(LevelSuccessMessage.GET_LEVEL_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
//    @PostMapping("getSkillByLevelId/{levelId}")
//    public ResponseEntity<ListResponseDTO> getSkillByLevelId(@PathVariable(name = "levelId") int levelId){
//        ListResponseDTO<SkillResponse> responseDTO = new ListResponseDTO<>();
//        List<SkillResponse> list = skillService.getSkillByLevelId(levelId);
//        responseDTO.setMessage(SkillSuccessMessage.GET_SKILL_BY_LEVEL_SUCCESS);
//        responseDTO.setData(list);
//        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
//        return ResponseEntity.ok().body(responseDTO);
//    }
    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createSkill(@RequestBody SkillCreateDTO dto){
        ResponseDTO<SkillResponse> responseDTO = new ResponseDTO<>();
        SkillResponse skillResponse = skillService.createSkill(dto);
        responseDTO.setData(skillResponse);
        responseDTO.setMessage(SkillSuccessMessage.CREATE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
