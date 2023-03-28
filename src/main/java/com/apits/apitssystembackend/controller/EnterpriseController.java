package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.EmployeeUpdateDTO;
import com.apits.apitssystembackend.DTO.EnterpriseCreateDTO;
import com.apits.apitssystembackend.DTO.EnterpriseUpdateDTO;
import com.apits.apitssystembackend.DTO.PositionCreateDTO;
import com.apits.apitssystembackend.constant.employee.EmployeeSuccessMessage;
import com.apits.apitssystembackend.constant.enterprise.EnterpriseSuccessMessage;
import com.apits.apitssystembackend.constant.position.PositionSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.*;
import com.apits.apitssystembackend.serviceImp.EnterpriseServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {
    private final EnterpriseServiceImp enterpriseServiceImp;

    @GetMapping("/getEnterpriseById")
    public ResponseEntity<ResponseDTO> getEnterpriseById(@RequestParam(name = "id") int enterpriseId) {
        ResponseDTO<EnterpriseResponse> responseDTO = new ResponseDTO<>();
        EnterpriseResponse enterpriseResponse = enterpriseServiceImp.getEnterpriseById(enterpriseId);
        responseDTO.setData(enterpriseResponse);
        responseDTO.setMessage(ResponseStatusDTO.SUCCESS);
        responseDTO.setStatus(EnterpriseSuccessMessage.GET_ENTERPRISE_BY_ID_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/create")
    public ResponseDTO createPosition(@RequestBody EnterpriseCreateDTO createDTO) {
        ResponseDTO<EnterpriseResponse> responseDTO = new ResponseDTO();
        EnterpriseResponse enterpriseResponse = enterpriseServiceImp.createEnterprise(createDTO);
        responseDTO.setData(enterpriseResponse);
        responseDTO.setMessage(PositionSuccessMessage.CREATE_POSITION);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return responseDTO;
    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<ResponseDTO> disableEnterprise(@PathVariable("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setData(enterpriseServiceImp.disableEnterpriseById(id));
        responseDTO.setMessage(EnterpriseSuccessMessage.DISABLE_ENTERPRISE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDTO> activeEnterprise(@PathVariable("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setData(enterpriseServiceImp.activeEnterpriseById(id));
        responseDTO.setMessage(EnterpriseSuccessMessage.ACTIVE_ENTERPRISE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEmployee(
            @RequestParam("id") int id,
            @RequestBody EnterpriseUpdateDTO updateDTO) {
        ResponseDTO<EnterpriseResponse> responseDTO = new ResponseDTO();
        EnterpriseResponse enterpriseResponse = enterpriseServiceImp.updateEnterprise(id, updateDTO);
        responseDTO.setData(enterpriseResponse);
        responseDTO.setMessage(EnterpriseSuccessMessage.UPDATE_ENTERPRISE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getAllEnterprise")
    public ResponseEntity<ResponseDTO> getAllEnterprise(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<EnterpriseResponse> list = enterpriseServiceImp.getAllEnterprise(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(EnterpriseSuccessMessage.GET_ALL_ENTERPRISE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
