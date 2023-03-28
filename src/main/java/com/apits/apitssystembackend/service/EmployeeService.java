package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.EmployeeCreateDTO;
import com.apits.apitssystembackend.DTO.PositionEmployeeUpdateDTO;
import com.apits.apitssystembackend.entity.Employee;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.response.EmployeeResponse;
import com.apits.apitssystembackend.DTO.EmployeeUpdateDTO;
import org.springframework.stereotype.Service;


@Service
public interface EmployeeService {
    public EmployeeResponse getEmployeeById(int id);

    public EmployeeResponse updateEmployee(int id, EmployeeUpdateDTO updateDTO);

    public Employee deleteEmployeeById(int id);

    Employee activeEmployeeById(int id);

    public ResponseWithTotalPage<EmployeeResponse> getAllEmployees(int pageNo, int pageSize);
    Employee setPosition(PositionEmployeeUpdateDTO dto);


}
