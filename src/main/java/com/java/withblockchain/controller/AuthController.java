package com.java.withblockchain.controller;

import com.java.withblockchain.model.form.LoginForm;
import com.java.withblockchain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm form){
        return new ResponseEntity<>(authService.login(form), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken){
        return new ResponseEntity<>(authService.refreshJWT(refreshToken), HttpStatus.OK);
    }
}