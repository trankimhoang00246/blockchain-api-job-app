package com.java.withblockchain.service;


import com.java.withblockchain.model.dto.AuthDto;
import com.java.withblockchain.model.form.LoginForm;


public interface AuthService {
    AuthDto login(LoginForm form);
    AuthDto refreshJWT(String refreshToken);
}