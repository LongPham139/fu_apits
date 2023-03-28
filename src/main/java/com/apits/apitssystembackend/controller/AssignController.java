package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.constant.assign.AssignSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.AssignCandidateResponse;
import com.apits.apitssystembackend.response.AssignResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.service.AssignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assign")
@RequiredArgsConstructor
public class AssignController {
    private final AssignService assignService;


    @GetMapping("getById/{id}")
    public ResponseEntity<ResponseDTO> getAssignById(@RequestParam("id") int id) {
        ResponseDTO<AssignResponse> responseDTO = new ResponseDTO();
        AssignResponse assignResponse = assignService.getAssignById(id);
        responseDTO.setData(assignResponse);
        responseDTO.setMessage(AssignSuccessMessage.GET_BY_ID);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllJobAssign(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<AssignResponse> list = assignService.getAllAssign(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AssignSuccessMessage.GET_ALL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getByEmployee")
    public ResponseEntity<ResponseDTO> getJobAssignByCandidate(
            @RequestParam int employeeId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<AssignResponse> list = assignService.getAssignByEmployee(employeeId, pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AssignSuccessMessage.GET_ALL_BY_HUMAN_RESOURCE);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getByRecruitmentRequest")
    public ResponseEntity<ResponseDTO> getAllJobAssignByRecruitmentRequest(
            @RequestParam int requestId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<AssignResponse> list = assignService.getAllAssignByRecruitmentRequest(requestId, pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AssignSuccessMessage.GET_ALL_BY_RECRUITMENT_REQUEST);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getAllPending")
    public ResponseEntity<ResponseDTO> getAllPendingJobAssign(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<AssignResponse> list = assignService.getAllPendingAssign(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AssignSuccessMessage.GET_ALL_PENDING);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/getAllApproved")
    public ResponseEntity<ResponseDTO> getAllApprovedJobAssign(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<AssignResponse> list = assignService.getAllApprovedAssign(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AssignSuccessMessage.GET_ALL_APPROVED);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getAllCancel")
    public ResponseEntity<ResponseDTO> getAllCancelJobAssign(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<AssignResponse> list = assignService.getAllCancelAssign(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AssignSuccessMessage.GET_ALL_CANCELED);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<ResponseDTO> cancelJobAssign(@RequestParam("id") int id, @RequestParam int employeeId) {
        ResponseDTO<AssignResponse> responseDTO = new ResponseDTO();
        AssignResponse assignResponse = assignService.cancelAssign(id, employeeId);
        responseDTO.setData(assignResponse);
        responseDTO.setMessage(AssignSuccessMessage.CANCEL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("/approvedByCandidate/{id}")
    public ResponseEntity<ResponseDTO> approvedJobAssign(@RequestParam("id") int id,
                                                        @RequestParam int candidateId) {
        ResponseDTO<AssignCandidateResponse> responseDTO = new ResponseDTO();
        AssignCandidateResponse assignCandidateResponse = assignService.approvedAssign(id, candidateId);
        responseDTO.setData(assignCandidateResponse);
        responseDTO.setMessage(AssignSuccessMessage.APPROVE);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/rejectedByCandidate/{id}")
    public ResponseEntity<ResponseDTO> rejectedJobAssign(@RequestParam("id") int id,
                                                        @RequestParam int candidateId) {
        ResponseDTO<AssignCandidateResponse> responseDTO = new ResponseDTO();
        AssignCandidateResponse assignCandidateResponse = assignService.rejectedAssign(id, candidateId);
        responseDTO.setData(assignCandidateResponse);
        responseDTO.setMessage(AssignSuccessMessage.REJECT);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

}
