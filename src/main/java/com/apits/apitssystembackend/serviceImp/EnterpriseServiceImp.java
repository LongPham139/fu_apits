package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.EnterpriseCreateDTO;
import com.apits.apitssystembackend.DTO.EnterpriseUpdateDTO;
import com.apits.apitssystembackend.constant.account.AccountFailMessage;
import com.apits.apitssystembackend.constant.account.AccountStatus;
import com.apits.apitssystembackend.constant.employee.EmployeeErrorMessage;
import com.apits.apitssystembackend.constant.employee.EmployeeStatus;
import com.apits.apitssystembackend.constant.enterprise.EnterpriseErrorMessage;
import com.apits.apitssystembackend.constant.enterprise.EnterpriseStatus;
import com.apits.apitssystembackend.constant.enterprise.EnterpriseSuccessMessage;
import com.apits.apitssystembackend.entity.Employee;
import com.apits.apitssystembackend.entity.Enterprise;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.EnterpriseRepository;
import com.apits.apitssystembackend.response.EmployeeResponse;
import com.apits.apitssystembackend.response.EnterpriseResponse;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImp {

    private final EnterpriseRepository enterpriseRepository;

    private final ModelMapper modelMapper;

    public EnterpriseResponse getEnterpriseById(int id){
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EnterpriseErrorMessage.ENTERPRISE_NOT_FOUND_EXCEPTION));
        EnterpriseResponse enterpriseResponse = modelMapper.map(enterprise, EnterpriseResponse.class);
        return enterpriseResponse;
    }

    public EnterpriseResponse createEnterprise(EnterpriseCreateDTO createDTO) {
        Enterprise enterprise = Enterprise.builder()
                .name(createDTO.getName())
                .address(createDTO.getAddress())
                .phone(createDTO.getPhone())
                .status(EnterpriseStatus.ACTIVATE)
                .build();
        enterpriseRepository.save(enterprise);
        EnterpriseResponse response = modelMapper.map(enterprise, EnterpriseResponse.class);
        return response;
    }
    public EnterpriseResponse updateEnterprise(int id, EnterpriseUpdateDTO updateDTO) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EnterpriseErrorMessage.ENTERPRISE_NOT_FOUND_EXCEPTION));
        enterprise.setAddress(updateDTO.getAddress());
        enterprise.setPhone(updateDTO.getPhone());
        enterpriseRepository.save(enterprise);
        EnterpriseResponse response = modelMapper.map(enterprise, EnterpriseResponse.class);
        return response;
    }

    //TODO fix disable
    public Enterprise disableEnterpriseById(int id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EnterpriseErrorMessage.ENTERPRISE_NOT_FOUND_EXCEPTION));
        if (enterprise.getAccount() != null) {
            if (enterprise.getAccount().getStatus().equals(EnterpriseStatus.DISABLE)) {
                throw new NotFoundException(EnterpriseErrorMessage.DISABLE_ACCOUNT_ENTERPRISE_FAIL);
            }
            enterprise.getAccount().setStatus(EnterpriseStatus.DISABLE);
            enterprise.setStatus(EnterpriseStatus.DISABLE);
            Enterprise enterpriseSave = enterpriseRepository.save(enterprise);
            return enterpriseSave;
        }
        return null;
    }

    public Enterprise activeEnterpriseById(int id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EnterpriseErrorMessage.ENTERPRISE_NOT_FOUND_EXCEPTION));
        if (enterprise.getAccount() != null) {
            enterprise.getAccount().setStatus(EnterpriseStatus.ACTIVATE);
            enterprise.setStatus(EnterpriseStatus.ACTIVATE);
            Enterprise enterpriseSaved = enterpriseRepository.save(enterprise);
            return enterpriseSaved;
        }
        return null;
    }
    public ResponseWithTotalPage<EnterpriseResponse> getAllEnterprise(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Enterprise> pageResult = enterpriseRepository.findAll(pageable);
        List<EnterpriseResponse> list = new ArrayList<EnterpriseResponse>();
        ResponseWithTotalPage<EnterpriseResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Enterprise enterprise : pageResult.getContent()) {
                EnterpriseResponse enterpriseResponse = modelMapper.map(enterprise, EnterpriseResponse.class);
                list.add(enterpriseResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(EnterpriseErrorMessage.LIST_EMPTY_EXCEPTION);
        return result;
    }
}
