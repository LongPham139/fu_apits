package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.AccountCreateDTO;
import com.apits.apitssystembackend.DTO.CandidateCreateDTO;
import com.apits.apitssystembackend.DTO.EnterpriseCreateDTO;
import com.apits.apitssystembackend.entity.Account;
import com.apits.apitssystembackend.request.AuthenticateRequest;
import com.apits.apitssystembackend.request.RegisterRequest;
import com.apits.apitssystembackend.response.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public AuthenticationResponse registerForCandidate(CandidateCreateDTO request);
    public AccountResponseLoginDTO login(AuthenticateRequest request);
    public LoginResponseDTO loginGoogle(String token);
    public Account registerAccountForGoogleLogin(String email, String name, String image, String token);
    public Account changePassword(String email, String pasword);
    AccountResponseLoginDTO registerForEnterprise(EnterpriseCreateDTO dto);
//    public Account registerForCandidate(AccountCreateDTO dto);
}
