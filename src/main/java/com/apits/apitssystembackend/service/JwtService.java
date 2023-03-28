package com.apits.apitssystembackend.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {

    public String extractUserName(String token);
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    public String generateToken(UserDetails userDetails);
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public Claims extractAllClaims(String token);

}
