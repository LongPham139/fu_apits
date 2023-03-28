package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.CandidateSkillLevelCreateDTO;
import com.apits.apitssystembackend.constant.candidate_skill_level.CandidateSkillLevelSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.CandidateSkillLevel.CandidateSkillLevelResponse;
import com.apits.apitssystembackend.response.CandidateSkillLevel.CandidateSkillsLevelsResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.CandidateSkillLevelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("candidate-skill-level")
@Tag(name = "Candidate Skill Level Controller")
@RequiredArgsConstructor
public class CandidateSkillLevelController {
    private final CandidateSkillLevelService candidateSkillLevelService;
    @GetMapping("findById")
    public ResponseEntity<ResponseDTO> findById(@RequestParam int id){
        ResponseDTO<CandidateSkillLevelResponse> responseDTO = new ResponseDTO<>();
        CandidateSkillLevelResponse data = candidateSkillLevelService.findById(id);
        responseDTO.setData(data);
        responseDTO.setMessage(CandidateSkillLevelSuccessMessage.GET_CANDIDATE_SKILL_SKILL_LEVEL_BY_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("create")
    public ResponseEntity<ResponseDTO> create(@RequestBody CandidateSkillLevelCreateDTO dto){
        ResponseDTO<CandidateSkillLevelResponse> responseDTO = new ResponseDTO<>();
        CandidateSkillLevelResponse data = candidateSkillLevelService.create(dto);
        responseDTO.setData(data);
        responseDTO.setMessage(CandidateSkillLevelSuccessMessage.CREATE_CANDIDATE_SKILL_LEVEL_BY_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getListSkillByCandidate")
    public ResponseEntity<ResponseDTO> getListSkillByCandidate(@RequestParam int id, @RequestParam String status){
        ResponseDTO<CandidateSkillsLevelsResponse> responseDTO = new ResponseDTO<>();
        CandidateSkillsLevelsResponse data = candidateSkillLevelService.getListSkillByCandidateId(id, status);
        responseDTO.setData(data);
        responseDTO.setMessage(CandidateSkillLevelSuccessMessage.GET_LIST_SKILL_LEVELS_BY_CANDIDATE_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

}
