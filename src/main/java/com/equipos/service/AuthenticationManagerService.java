package com.equipos.service;

import com.equipos.model.dto.AuthenticationDTO;
import com.equipos.model.payload.AuthenticationPayload;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationManagerService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtTokenUtil;

    public AuthenticationManagerService(AuthenticationManager authenticationManager, JwtService jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public AuthenticationDTO authenticate(AuthenticationPayload authenticationPayload) {
        Authentication  authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationPayload.getUsername(), authenticationPayload.getPassword()));
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        String token = getToken(userDetails);
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setToken(token);
        return authenticationDTO;
    }

    private String getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final String username = userDetails.getUsername();
        return jwtTokenUtil.doGenerateToken(Map.of("role", roles), username);
    }
}
