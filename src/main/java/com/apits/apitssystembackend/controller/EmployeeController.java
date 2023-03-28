package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.EmployeeUpdateDTO;
import com.apits.apitssystembackend.DTO.PositionEmployeeUpdateDTO;
import com.apits.apitssystembackend.constant.employee.EmployeeSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.response.EmployeeResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Tag(name = "EmployeeController")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper mapper;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO> getEmployeeById(@RequestParam(name = "id") int employeeId) {
        ResponseDTO<EmployeeResponse> responseDTO = new ResponseDTO();
        EmployeeResponse employee = employeeService.getEmployeeById(employeeId);
        responseDTO.setData(employee);
        responseDTO.setMessage(EmployeeSuccessMessage.GET_EMPLOYEE_BY_ID_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<ResponseDTO> getAllEmployees(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {
        ResponseDTO<ResponseWithTotalPage> responseDTO = new ResponseDTO();
        ResponseWithTotalPage<EmployeeResponse> list = employeeService.getAllEmployees(pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(EmployeeSuccessMessage.GET_ALL_EMPLOYEE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEmployee(
            @RequestParam("id") int id,
            @RequestBody EmployeeUpdateDTO updateDTO) {
        ResponseDTO<EmployeeResponse> responseDTO = new ResponseDTO();
        EmployeeResponse employee = employeeService.updateEmployee(id, updateDTO);
        responseDTO.setData(employee);
        responseDTO.setMessage(EmployeeSuccessMessage.UPDATE_EMPLOYEE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<ResponseDTO> disableEmployee(@PathVariable("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setData(employeeService.deleteEmployeeById(id));
        responseDTO.setMessage(EmployeeSuccessMessage.DISABLE_EMPLOYEE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDTO> activeEmployee(@PathVariable("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setData(employeeService.activeEmployeeById(id));
        responseDTO.setMessage(EmployeeSuccessMessage.ACTIVE_EMPLOYEE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("setPosition")
    public ResponseEntity<ResponseDTO> setPosition(PositionEmployeeUpdateDTO dto){
        ResponseDTO<EmployeeResponse> responseDTO = new ResponseDTO<>();
        EmployeeResponse emp = mapper.map(employeeService.setPosition(dto), EmployeeResponse.class);
        responseDTO.setData(emp);
        responseDTO.setMessage(EmployeeSuccessMessage.SET_POSITION_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
