package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.SpecialtyCreateDTO;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.constant.specialty.SpecialtySuccessMessage;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.SpecialtyResponse;
import com.apits.apitssystembackend.service.SpecialtyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialty")
@Tag(name = "Special Controller")
@RequiredArgsConstructor
public class SpecialtyController {
    private final SpecialtyService specialtyService;
    @GetMapping("getAll")
    public ResponseEntity<ListResponseDTO> getAllSpec(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ListResponseDTO<SpecialtyResponse> responseDTO = new ListResponseDTO<>();
        List<SpecialtyResponse> list = specialtyService.getAllSpecialty(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(SpecialtySuccessMessage.GET_ALL_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("getSpecById/{id}")
    public ResponseEntity<ResponseDTO> getSpecById(@PathVariable(name = "id") int id){
        ResponseDTO<SpecialtyResponse> responseDTO = new ResponseDTO<>();
        SpecialtyResponse spec = specialtyService.getSpecialtyById(id);
        responseDTO.setData(spec);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(SpecialtySuccessMessage.GET_ID_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("disableSpec/{id}")
    public ResponseEntity<ResponseDTO> disableSpec(@PathVariable(name = "id") int id){
        ResponseDTO<SpecialtyResponse> responseDTO = new ResponseDTO<>();
        SpecialtyResponse specialtyResponse = specialtyService.disableSpecialty(id);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setData(specialtyResponse);
        responseDTO.setMessage(SpecialtySuccessMessage.DISABLE_SPEC_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("activeSpec/{id}")
    public ResponseEntity<ResponseDTO> activeSpec(@PathVariable(name = "id") int id){
        ResponseDTO<SpecialtyResponse> responseDTO = new ResponseDTO<>();
        SpecialtyResponse specialtyResponse = specialtyService.activeSpecialty(id);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setData(specialtyResponse);
        responseDTO.setMessage(SpecialtySuccessMessage.ACTIVE_SPEC_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("getSpecByName")
    public ResponseEntity<ResponseDTO> getSpecByName(@RequestParam String name){
        ResponseDTO<SpecialtyResponse> responseDTO = new ResponseDTO<>();
        SpecialtyResponse spec = specialtyService.getSpecialtyByName(name);
        responseDTO.setData(spec);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(SpecialtySuccessMessage.GET_NAME_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("createSpec")
    public ResponseEntity<ResponseDTO> createSpec(@RequestBody SpecialtyCreateDTO dto){
        ResponseDTO<SpecialtyResponse> responseDTO = new ResponseDTO<>();
        SpecialtyResponse spec = specialtyService.createSpecialty(dto);
        responseDTO.setMessage(SpecialtySuccessMessage.CREATE_SPEC_SUCCESS);
        responseDTO.setData(spec);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

}
