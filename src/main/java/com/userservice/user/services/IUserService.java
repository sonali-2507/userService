package com.userservice.user.services;

import com.userservice.user.UserApplication;
//import com.userservice.user.controllers.Dto.UserRequestDto;
import com.userservice.user.controllers.Dto.UserResponseDto;
import com.userservice.user.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
//   User signUp(String name,String email, String password, String location);

//   Optional<User> getUserById(Long id);
   UserResponseDto getUserDetails(Long userId);
   UserResponseDto setUserRoles(Long userId, List<Long> roleIds);


}
