package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.Specialty_SkillCreateDTO;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.constant.special_skill.Special_SkillSuccessMessage;
import com.apits.apitssystembackend.custom.CandidateCustom.CandidateCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Special_SkillsCustomResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.Specialty_Skill.SpecialtiesSkillResponse;
import com.apits.apitssystembackend.response.Specialty_Skill.SpecialtySkillsResponse;
import com.apits.apitssystembackend.response.Specialty_Skill.Specialty_SkillResponse;
import com.apits.apitssystembackend.service.CustomResponseService;
import com.apits.apitssystembackend.service.Specialty_SkillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("special-skill")
@Tag(name = "Specialty Skill Controller")
@RequiredArgsConstructor
public class Specialty_SkillController {
    private final Specialty_SkillService specialtySkillService;
    private final CustomResponseService customResponseService;


    @GetMapping("getSpecSkillById/{id}")
    public ResponseEntity<ResponseDTO> getSpecSkillById(@PathVariable(name = "id") int id){
        ResponseDTO<Specialty_SkillResponse> responseDTO = new ResponseDTO<>();
        Specialty_SkillResponse data = specialtySkillService.getSpecializeSkillById(id);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setData(data);
        responseDTO.setMessage(Special_SkillSuccessMessage.GET_SPECIAL_SKILL_ID_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);

    }
    @GetMapping("getListSpecsBySkillId/{id}")
    public ResponseEntity<ResponseDTO> getListSpecsBySkillId(@PathVariable(name = "id") int id){
        ResponseDTO<SpecialtiesSkillResponse> responseDTO = new ResponseDTO<>();
        SpecialtiesSkillResponse data = specialtySkillService.getListSpecBySkillId(id);
        responseDTO.setData(data);
        responseDTO.setMessage(Special_SkillSuccessMessage.GET_LIST_SPECIAL_BY_SKILL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getListSkillsBySpecId/{id}")
    public ResponseEntity<ResponseDTO> getListSkillsBySpecId(@PathVariable(name = "id") int id){
        ResponseDTO<SpecialtySkillsResponse> responseDTO = new ResponseDTO<>();
        SpecialtySkillsResponse data = specialtySkillService.getListSkillBySpecicalId(id);
        responseDTO.setData(data);
        responseDTO.setMessage(Special_SkillSuccessMessage.GET_LIST_SKILL_BY_SPECIAL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("createSpecSkill")
    public ResponseEntity<ResponseDTO> createSpecSkill(@RequestBody Specialty_SkillCreateDTO dto){
        ResponseDTO<Specialty_SkillResponse> responseDTO = new ResponseDTO<>();
        Specialty_SkillResponse data = specialtySkillService.createSpecializeSkill(dto);
        responseDTO.setData(data);
        responseDTO.setMessage(Special_SkillSuccessMessage.CREATE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getAllSpecDetails")
    public ResponseEntity<ResponseDTO> getAllSpecDetails(){
        ResponseDTO<List<Special_SkillsCustomResponse>> responseDTO = new ResponseDTO<>();
        List<Special_SkillsCustomResponse> customResponse = customResponseService.getAllSpecial();
        responseDTO.setData(customResponse);
        responseDTO.setMessage("SUCCESS");
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getSpecialDetail")
    public ResponseEntity<ResponseDTO> getSpecialDetail(@RequestParam int id){
        ResponseDTO<Special_SkillsCustomResponse> responseDTO = new ResponseDTO<>();
        Special_SkillsCustomResponse customResponse = customResponseService.getSkillsBySpecial(id);
        responseDTO.setData(customResponse);
        responseDTO.setMessage("SUCCESS");
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getAllCourseByCandidate")
    public ResponseEntity<ResponseDTO> getAllCourseByCandidate(@RequestParam int id, @RequestParam String status){
        ResponseDTO<CandidateCustomResponse> responseDTO = new ResponseDTO<>();
        CandidateCustomResponse customResponse = customResponseService.getListSpecSkillLevelCourseByCandidateId(id, status);
        responseDTO.setData(customResponse);
        responseDTO.setMessage("SUCCESS");
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
