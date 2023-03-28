package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.AccountUpdateDTO;
import com.apits.apitssystembackend.DTO.EmployeeCreateDTO;
import com.apits.apitssystembackend.constant.account.AccountSuccessMessage;
import com.apits.apitssystembackend.constant.employee.EmployeeSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.DTO.AccountCreateDTO;
import com.apits.apitssystembackend.DTO.AccountRemoveDTO;
import com.apits.apitssystembackend.repository.AccountRepository;
import com.apits.apitssystembackend.response.AccountEmployeeResponse;
import com.apits.apitssystembackend.response.AccountResponse;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@Tag(name = "Account Controller")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping("getAllAccounts")
    public ResponseEntity<ListResponseDTO> getAllAccounts() {
        ListResponseDTO<AccountResponse> responseDTO = new ListResponseDTO<>();
        List<AccountResponse> list = accountService.getAllAccount();
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(AccountSuccessMessage.GET_ALL_SUCCESSFULL);
        responseDTO.setData(list);
        return ResponseEntity.ok().body(responseDTO);

    }

    @GetMapping("getAllPaging")
    public ResponseEntity<ListResponseDTO> getAllPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        ListResponseDTO<AccountResponse> responseDTO = new ListResponseDTO<>();
        List<AccountResponse> list = accountService.getAllPaging(pageNo, pageSize);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(AccountSuccessMessage.GET_ALL_SUCCESSFULL);
        responseDTO.setData(list);
        return ResponseEntity.ok().body(responseDTO);

    }

    @GetMapping("getAllByRoleId")
    public ResponseEntity<ListResponseDTO> getAllByRoleId(
            @RequestParam int roleId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        ListResponseDTO<AccountResponse> responseDTO = new ListResponseDTO<>();
        List<AccountResponse> list = accountService.getAllAccountsByRole(roleId, pageNo, pageSize);
        responseDTO.setData(list);
        responseDTO.setMessage(AccountSuccessMessage.GET_ALL_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);

    }

    @GetMapping("getAccountById")
    public ResponseEntity<ResponseDTO> getAccountById(@RequestParam int id) {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO<>();
        AccountResponse account = accountService.getAccountById(id);
        responseDTO.setData(account);
        responseDTO.setMessage(AccountSuccessMessage.GET_ACCOUNT_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("createAdmin")
    public ResponseEntity<ResponseDTO> createAccountAdmin(@RequestBody AccountCreateDTO dto) {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO<>();
        AccountResponse account = accountService.createAccountAdmin(dto);
        responseDTO.setData(account);
        responseDTO.setMessage(AccountSuccessMessage.CREATE_ACCOUNT_ADMIN_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("createEmployee")
    public ResponseEntity<ResponseDTO> createAccountEmployee(@RequestBody EmployeeCreateDTO dto) {
        ResponseDTO<AccountEmployeeResponse> responseDTO = new ResponseDTO<>();
        AccountEmployeeResponse account = accountService.createEmployee(dto);
        responseDTO.setData(account);
        responseDTO.setMessage(EmployeeSuccessMessage.CREATE_EMPLOYEE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("update/{id}")
    public ResponseEntity<ResponseDTO> updateAccount(@PathVariable(name = "id") int id, @RequestBody AccountUpdateDTO dto) {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO<>();
        AccountResponse accountResponse = accountService.updateAccount(id, dto);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(AccountSuccessMessage.UPDATE_ACCOUNT_SUCCESSFULL);
        responseDTO.setData(accountResponse);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("disable/{id}")
    public ResponseEntity<ResponseDTO> removeAccount(@PathVariable(name = "id") int id) {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO<>();
        AccountResponse accountResponse = accountService.removeAccount(id);
        responseDTO.setData(accountResponse);
        responseDTO.setMessage(AccountSuccessMessage.REMOVE_ACCOUNT_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam int id) {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO<>();
        String msg = accountService.deleteAccount(id);
        responseDTO.setMessage(msg);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
