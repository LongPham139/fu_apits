package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.CountTotalDTO;
import com.apits.apitssystembackend.DTO.EmployeeCreateDTO;
import com.apits.apitssystembackend.DTO.PositionEmployeeUpdateDTO;
import com.apits.apitssystembackend.constant.account.AccountFailMessage;
import com.apits.apitssystembackend.constant.account.AccountStatus;
import com.apits.apitssystembackend.constant.apits.ApitsMail;
import com.apits.apitssystembackend.constant.employee.EmployeeErrorMessage;
import com.apits.apitssystembackend.constant.employee.EmployeeStatus;
import com.apits.apitssystembackend.constant.position.PositionErrorMessage;
import com.apits.apitssystembackend.constant.role.RoleName;
import com.apits.apitssystembackend.entity.Account;
import com.apits.apitssystembackend.entity.Employee;
import com.apits.apitssystembackend.entity.Position;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.EmployeeRepository;
import com.apits.apitssystembackend.repository.PositionRepository;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.response.EmployeeResponse;
import com.apits.apitssystembackend.DTO.EmployeeUpdateDTO;
import com.apits.apitssystembackend.service.AccountService;
import com.apits.apitssystembackend.service.EmployeeService;
import com.apits.apitssystembackend.service.JwtService;
import com.apits.apitssystembackend.service.PositionService;
import com.apits.apitssystembackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;
    private final ModelMapper mapper;
    private final PositionRepository positionRepository;
    private final StringUtils stringUtils;


    @Override
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }

    @Override
    public EmployeeResponse updateEmployee(int id, EmployeeUpdateDTO updateDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));

        employee.setImage(updateDTO.getImage());
        employee.setGender(updateDTO.getGender());


        employee.setDob(updateDTO.getDob());
        employee.setEmployeeName(updateDTO.getName());
        employee.setEmployeeCode(employee.getEmployeeCode());
        employee.setPhone(updateDTO.getPhone());
        employee.setAddress(updateDTO.getAddress());
        employee.setPosition(employee.getPosition());
        Employee employeeSaved = employeeRepository.save(employee);
        EmployeeResponse response = mapper.map(employeeSaved, EmployeeResponse.class);
        return response;
    }

    @Override
    public Employee deleteEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));
        if (employee.getAccount() != null) {
            if (employee.getAccount().getStatus().equals(AccountStatus.DISABLED)) {
                throw new NotFoundException(AccountFailMessage.ACCOUNT_ALREADY_DELETED);
            }
            employee.getAccount().setStatus(AccountStatus.DISABLED);
            employee.setStatus(EmployeeStatus.DISABLE);
            Employee employeeSaved = employeeRepository.save(employee);
            return employeeSaved;
        }
        return null;
    }

    @Override
    public Employee activeEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));
        if (employee.getAccount() != null) {
            employee.getAccount().setStatus(AccountStatus.ACTIVATED);
            employee.setStatus(EmployeeStatus.ACTIVATE);
            Employee employeeSaved = employeeRepository.save(employee);
            return employeeSaved;
        }
        return null;
    }

    @Override
    public ResponseWithTotalPage<EmployeeResponse> getAllEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Employee> pageResult = employeeRepository.findAll(pageable);
        List<EmployeeResponse> list = new ArrayList<EmployeeResponse>();
        ResponseWithTotalPage<EmployeeResponse> result = new ResponseWithTotalPage<>();

        if (pageResult.hasContent()) {
            for (Employee employee : pageResult.getContent()) {
                EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
                list.add(employeeResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(EmployeeErrorMessage.LIST_EMPTY_EXCEPTION);
        return result;
    }

    @Override
    public Employee setPosition(PositionEmployeeUpdateDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmpId())
                .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));
        employee.setPosition(positionRepository.findById(dto.getPositionId()).orElseThrow(() -> new NotFoundException(PositionErrorMessage.POSITION_NOT_EXIST)));
        employeeRepository.save(employee);
        return employee;
    }


}
