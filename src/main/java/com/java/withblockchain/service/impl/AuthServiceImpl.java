package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.InvalidRefreshTokenException;
import com.java.withblockchain.model.dto.AuthDto;
import com.java.withblockchain.model.form.LoginForm;
import com.java.withblockchain.security.JwtService;
import com.java.withblockchain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;

    @Override
    public AuthDto login(LoginForm form) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getAddress(), null);

        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateRefreshToken(authentication);

        return AuthDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthDto refreshJWT(String refreshToken) {
        if( refreshToken != null ){
            refreshToken = refreshToken.replaceFirst("Bearer ", "");
            if(jwtService.validateRefreshToken(refreshToken) ){
                Authentication auth = jwtService.createAuthentication(refreshToken);
                return AuthDto.builder()
                        .refreshToken(refreshToken)
                        .accessToken(jwtService.generateRefreshToken(auth))
                        .build();
            }
        }
        throw new InvalidRefreshTokenException(refreshToken);
    }
}
