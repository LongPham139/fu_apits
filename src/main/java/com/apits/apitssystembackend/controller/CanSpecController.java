package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.CanSpecCreateDTO;
import com.apits.apitssystembackend.constant.canspec.CanSpecSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.Candidate_Specialty.CanSpecResponse;
import com.apits.apitssystembackend.response.Candidate_Specialty.CanSpecsResponse;
import com.apits.apitssystembackend.response.Candidate_Specialty.CansSpecResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.CandidateSpecialtyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("canspec")
@Tag(name = "Candidate Speciality Controller")
@RequiredArgsConstructor
public class CanSpecController {
    private final CandidateSpecialtyService candidateSpecialtyService;

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> create(@RequestBody CanSpecCreateDTO dto){
        ResponseDTO<CanSpecResponse> responseDTO = new ResponseDTO<>();
        CanSpecResponse candidateResponse = candidateSpecialtyService.create(dto);
        responseDTO.setData(candidateResponse);
        responseDTO.setMessage(CanSpecSuccessMessage.CREATE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("getListSpecsWithCan/{id}")
    public ResponseEntity<ResponseDTO> getListWithCandidate(@PathVariable(name = "id") int id){
        ResponseDTO<CanSpecsResponse> responseDTO = new ResponseDTO<>();
        CanSpecsResponse canSpecsResponse = candidateSpecialtyService.getSpecialByCanId(id);
        responseDTO.setData(canSpecsResponse);
        responseDTO.setMessage(CanSpecSuccessMessage.GET_LIST_SPECIAL_BY_CANDIDATE_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getCanSpecById/{id}")
    public ResponseEntity<ResponseDTO> getCanSpecById(@PathVariable(name = "id") int id){
        ResponseDTO<CanSpecResponse> responseDTO = new ResponseDTO<>();
        CanSpecResponse canSpecResponse = candidateSpecialtyService.getCanSpecById(id);
        responseDTO.setData(canSpecResponse);
        responseDTO.setMessage(CanSpecSuccessMessage.GET_CANDIDATE_SPECIAL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getListCansWithSpec")
    public ResponseEntity<ResponseDTO> getListCansWithSpec(@RequestParam int specId){
        ResponseDTO<CansSpecResponse> responseDTO = new ResponseDTO<>();
        CansSpecResponse cansSpecResponse = candidateSpecialtyService.getCandidatesBySpecialId(specId);
        responseDTO.setData(cansSpecResponse);
        responseDTO.setMessage(CanSpecSuccessMessage.GET_LIST_CANDIDATE_BY_SPECIAL_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
