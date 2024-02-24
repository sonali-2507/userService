package com.userservice.user.services;

import com.userservice.user.controllers.Dto.UserResponseDto;
import com.userservice.user.models.SessionStatus;
import org.springframework.http.ResponseEntity;


public interface IAuthService {
    ResponseEntity<UserResponseDto> login(String email, String password);
    void logout(String token, Long userId);
    UserResponseDto signUp(String email, String password);
    SessionStatus validate(Long userId,String token );
}
