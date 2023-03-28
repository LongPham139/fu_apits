package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.CandidateCreateDTO;
import com.apits.apitssystembackend.constant.account.AccountSuccessMessage;
import com.apits.apitssystembackend.constant.candidate.CandidateSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.DTO.CandidateUpdateDTO;
import com.apits.apitssystembackend.custom.CandidateCustom.CandidateCustomResponse;
import com.apits.apitssystembackend.request.EmailRequest;
import com.apits.apitssystembackend.response.CandidateResponse;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.CandidateService;
import com.apits.apitssystembackend.service.CustomResponseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidate")
@Tag(name = "Candidate Controller")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    private final CustomResponseService customResponseService;

    @GetMapping
    public ResponseEntity<ListResponseDTO> getAllCandidate() {
        ListResponseDTO<CandidateResponse> responseDTO = new ListResponseDTO<>();
        List<CandidateResponse> list = candidateService.getAllCandidate();
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(AccountSuccessMessage.GET_ALL_SUCCESSFULL);
        responseDTO.setData(list);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("getCandidateByID")
    public ResponseEntity<ResponseDTO> getCandidateById(@RequestParam int id) {
        ResponseDTO<CandidateResponse> responseDTO = new ResponseDTO<>();
        CandidateResponse tmp = candidateService.getCandidateById(id);
        responseDTO.setData(tmp);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(CandidateSuccessMessage.GET_CANDIDATE_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("getCandidateByEmail")
    public ResponseEntity<?> getCandidateByEmail(@RequestBody EmailRequest email) {
        if (email.getEmail().isEmpty() || email.getEmail().isBlank()) {
            return getAllCandidate();
        } else {
            ResponseDTO<CandidateResponse> responseDTO = new ResponseDTO<>();
            CandidateResponse candidateResponse = candidateService.getCandidateByEmail(email);
            responseDTO.setData(candidateResponse);
            responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
            responseDTO.setMessage(CandidateSuccessMessage.GET_CANDIDATE_SUCCESS);
            return ResponseEntity.ok().body(responseDTO);
        }

    }

    @PostMapping(value = "create")
    public ResponseEntity<ResponseDTO> create(@RequestBody CandidateCreateDTO dto)  {
        ResponseDTO<CandidateResponse> responseDTO = new ResponseDTO<>();
        CandidateResponse tmp = candidateService.createCandidate(dto);
        responseDTO.setMessage(CandidateSuccessMessage.CREATE_CANDIDATE_SUCCESS);
        responseDTO.setData(tmp);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("update")
    public ResponseEntity<ResponseDTO> update(@RequestParam int id, @RequestBody CandidateUpdateDTO dto) {
        ResponseDTO<CandidateResponse> responseDTO = new ResponseDTO<>();
        CandidateResponse tmp = candidateService.updateCandidate(id, dto);
        responseDTO.setData(tmp);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(CandidateSuccessMessage.UPDATE_CANDIDATE_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable(name = "id") int id){
        ResponseDTO<CandidateResponse> responseDTO = new ResponseDTO<>();
        CandidateResponse response = candidateService.removeCandidate(id);
        responseDTO.setData(response);
        responseDTO.setMessage(CandidateSuccessMessage.REMOVE_CANDIDATE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getAllSkill")
    public ResponseEntity<ResponseDTO> getAll(@RequestParam int id, @RequestParam String status){
        ResponseDTO<CandidateCustomResponse> responseDTO = new ResponseDTO<>();
        CandidateCustomResponse data = customResponseService.getListSpecSkillLevelCourseByCandidateId(id, status);
        responseDTO.setData(data);
        responseDTO.setMessage("SUCCESS");
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/getCandidatesForAssignJob")
    public List<CandidateResponse> getCandidatesForAssignJob(@RequestBody List<Integer> skillIds) {
        return candidateService.findCandidateBySkillIds(skillIds);
    }
}
