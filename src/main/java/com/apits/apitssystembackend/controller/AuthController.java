package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.CandidateCreateDTO;
import com.apits.apitssystembackend.DTO.EnterpriseCreateDTO;
import com.apits.apitssystembackend.constant.account.AccountStatus;
import com.apits.apitssystembackend.constant.account.AccountSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.entity.Account;
import com.apits.apitssystembackend.repository.AccountRepository;
import com.apits.apitssystembackend.request.AuthenticateRequest;
import com.apits.apitssystembackend.request.RegisterRequest;
import com.apits.apitssystembackend.response.*;
import com.apits.apitssystembackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


import static com.apits.apitssystembackend.constant.account.AccountFailMessage.LOGIN_FAIL;
import static com.apits.apitssystembackend.constant.account.AccountSuccessMessage.LOGIN_SUCCESSFULL;
import static com.apits.apitssystembackend.constant.account.AccountSuccessMessage.REGISTER_SUCCESSFULL;

@RestController
@RequestMapping("account/auth")
@Tag(name = "Authenticate Controller")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;
    @Autowired
    private final AccountRepository accountRepository;


    @PostMapping("registerForCandidate")
    public ResponseEntity<ResponseDTO> register(
            @RequestBody CandidateCreateDTO request
    ) {
        ResponseDTO<AuthenticationResponse> responseDTO = new ResponseDTO<>();
        AuthenticationResponse auth = authService.registerForCandidate(request);
        responseDTO.setData(auth);
        responseDTO.setMessage(REGISTER_SUCCESSFULL);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("login")
    public ResponseEntity<ResponseDTO> login(
            @RequestBody AuthenticateRequest request
    ) {
        ResponseDTO<AccountResponseLoginDTO> responseDTO = new ResponseDTO<>();
        try {
            AccountResponseLoginDTO auth = authService.login(request);
            responseDTO.setData(auth);
            responseDTO.setMessage(LOGIN_SUCCESSFULL);
            responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
            return ResponseEntity.ok().body(responseDTO);
        } catch (BadCredentialsException badCredentialsException) {
            responseDTO.setData(null);
            responseDTO.setMessage(LOGIN_FAIL);
            responseDTO.setStatus(ResponseStatusDTO.FAILURE);
            return ResponseEntity.ok().body(responseDTO);
        }

    }

    @PostMapping("loginGoogle")
    public ResponseEntity<ResponseDTO> loginGoogle(@RequestBody AuthenticationResponse token) {
        ResponseDTO<LoginResponseDTO> responseDTO = new ResponseDTO();
        LoginResponseDTO loginResponseDTO = authService.loginGoogle(token.getToken());
        responseDTO.setData(loginResponseDTO);
        responseDTO.setMessage("Login success");
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("logout")
    public String getLogoutPage(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);

        return "redirect:/login";
    }

    @PostMapping("changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody AuthenticateRequest request) {
        ResponseDTO<Void> responseDTO = new ResponseDTO<>();
        authService.changePassword(request.getEmail(), request.getPassword());
        responseDTO.setMessage(AccountSuccessMessage.CHANGE_PASSWORD_ACCOUNT_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("registerForEnterprise")
    public ResponseEntity<ResponseDTO> registerForEnterprise(@RequestBody EnterpriseCreateDTO dto){
        ResponseDTO<AccountResponseLoginDTO> responseDTO = new ResponseDTO<>();
        AccountResponseLoginDTO auth = authService.registerForEnterprise(dto);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(REGISTER_SUCCESSFULL);
        responseDTO.setData(auth);
        return ResponseEntity.ok().body(responseDTO);
    }
}
