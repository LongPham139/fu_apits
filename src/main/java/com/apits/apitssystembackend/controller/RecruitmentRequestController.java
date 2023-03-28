package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.RecruitmentRequestCreateDTO;
import com.apits.apitssystembackend.DTO.RecruitmentRequestUpdateDTO;
import com.apits.apitssystembackend.constant.recruitment_request.RecruitmentRequestSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.RecruitmentRequestResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.serviceImp.RecruitmentRequestServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitmentRequest")
@RequiredArgsConstructor
public class RecruitmentRequestController {
    private final RecruitmentRequestServiceImp recruitmentRequestServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllRecruitmentRequests(@RequestParam(defaultValue = "0") int pageNo,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO response = new ResponseDTO();
        ResponseWithTotalPage responsePages = recruitmentRequestServiceImp.getAllRecruitmentRequests(pageNo, pageSize);
        response.setData(responsePages);
        response.setMessage(RecruitmentRequestSuccessMessage.GET_ALL_RECRUITMENT_REQUEST_SUCCESS);
        response.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/getById")
    public ResponseEntity<ResponseDTO> getRecruitmentRequestById(@RequestParam("id") int id) {
        ResponseDTO<RecruitmentRequestResponse> responseDTO = new ResponseDTO();
        RecruitmentRequestResponse response = recruitmentRequestServiceImp.getRecruitmentRequestById(id);
        responseDTO.setData(response);
        responseDTO.setMessage(RecruitmentRequestSuccessMessage.GET_RECRUITMENT_REQUEST_BY_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getByCreator")
    public ResponseEntity<ResponseDTO> getAllRecruitmentRequestByCreator(@RequestParam("id") int id,
                                                                         @RequestParam(defaultValue = "0") int pageNo,
                                                                         @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO response = new ResponseDTO();
        ResponseWithTotalPage responsePages = recruitmentRequestServiceImp.getAllRecruitmentRequestByCreator(id, pageNo, pageSize);
        response.setData(responsePages);
        response.setMessage(RecruitmentRequestSuccessMessage.GET_ALL_RECRUITMENT_REQUEST_SUCCESS);
        response.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createRecruitmentRequest(@RequestBody RecruitmentRequestCreateDTO createDTO) {
        ResponseDTO<RecruitmentRequestResponse> responseDTO = new ResponseDTO();
        RecruitmentRequestResponse response = recruitmentRequestServiceImp.createRecruitmentRequest(createDTO);
        responseDTO.setData(response);
        responseDTO.setMessage(RecruitmentRequestSuccessMessage.CREATE_RECRUITMENT_REQUEST_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateRecruitmentRequest(@RequestParam("id") int id,
                                                                @RequestBody RecruitmentRequestUpdateDTO updateDTO) {
        ResponseDTO<RecruitmentRequestResponse> responseDTO = new ResponseDTO();
        RecruitmentRequestResponse planDetailDTO = recruitmentRequestServiceImp.updateRecruitmentRequest(id, updateDTO);
        responseDTO.setData(planDetailDTO);
        responseDTO.setMessage(RecruitmentRequestSuccessMessage.UPDATE_RECRUITMENT_REQUEST_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("/closeRecruitmentRequest/{id}")
    public ResponseEntity<ResponseDTO> closeRecruitmentRequest(@RequestParam("id") int id) {
        ResponseDTO<RecruitmentRequestResponse> responseDTO = new ResponseDTO();
        RecruitmentRequestResponse response = recruitmentRequestServiceImp.closeRecruitmentRequest(id);
        responseDTO.setData(response);
        responseDTO.setMessage(RecruitmentRequestSuccessMessage.CLOSED_RECRUITMENT_REQUEST_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/openRecruitmentRequest/{id}")
    public ResponseEntity<ResponseDTO> openRecruitmentRequest(@RequestParam("id") int id) {
        ResponseDTO<RecruitmentRequestResponse> responseDTO = new ResponseDTO();
        RecruitmentRequestResponse response = recruitmentRequestServiceImp.openRecruitmentRequest(id);
        responseDTO.setData(response);
        responseDTO.setMessage(RecruitmentRequestSuccessMessage.CLOSED_RECRUITMENT_REQUEST_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getClosedRecruitmentRequest")
    public ResponseEntity<ResponseDTO> getAllClosedRecruitmentRequest(@RequestParam(defaultValue = "0") int pageNo,
                                                                      @RequestParam(defaultValue = "10") int pageSize) {
        ResponseDTO response = new ResponseDTO();
        ResponseWithTotalPage responseWithTotalPages = recruitmentRequestServiceImp.getAllClosedRecruitmentRequest(pageNo, pageSize);
        response.setData(responseWithTotalPages);
        response.setMessage(RecruitmentRequestSuccessMessage.GET_CLOSED_RECRUITMENT_REQUEST_SUCCESS);
        response.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(response);
    }
}
