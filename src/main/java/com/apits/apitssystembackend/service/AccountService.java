package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.AccountCreateDTO;
import com.apits.apitssystembackend.DTO.AccountUpdateDTO;
import com.apits.apitssystembackend.DTO.EmployeeCreateDTO;
import com.apits.apitssystembackend.entity.Account;
import com.apits.apitssystembackend.response.AccountEmployeeResponse;
import com.apits.apitssystembackend.response.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    List<AccountResponse> getAllAccount();
    List<AccountResponse> getAllPaging(int pageNo, int pageSize);
    List<AccountResponse> getAllAccountsByRole(int roleId, int pageNo, int pageSize);
    AccountResponse getAccountById(int id);
    Account getAccountByEmail(String email);
    AccountResponse createAccountAdmin(AccountCreateDTO accountCreateDTO);
    public AccountEmployeeResponse createEmployee(EmployeeCreateDTO dto);
    AccountResponse updateAccount(int id,AccountUpdateDTO accountUpdateDTO);
    AccountResponse removeAccount(int id);
    String deleteAccount(int id);






}
