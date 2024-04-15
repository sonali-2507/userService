package com.userservice.user.controllers;

import com.userservice.user.controllers.Dto.*;
import com.userservice.user.models.SessionStatus;
import com.userservice.user.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private IAuthService authService;
    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        authService.logout(request.getToken(), request.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto request) {
        UserResponseDto userDto = authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody  ValidateTokenRequestDto request) {
        SessionStatus sessionStatus = authService.validate(request.getUserId(),request.getToken() );

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }

}
