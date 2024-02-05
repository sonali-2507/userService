package com.userservice.user.controllers;

import com.userservice.user.controllers.Dto.ResponseStatus;
import com.userservice.user.controllers.Dto.UserRequestDto;
import com.userservice.user.controllers.Dto.UserResponseDto;
import com.userservice.user.models.User;
import com.userservice.user.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUserDetail")
    public ResponseEntity<UserResponseDto> saveUserDetail(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = new UserResponseDto();
        User user;
        try{
            user = userService.signUp(userRequestDto.getName(),userRequestDto.getEmail(),userRequestDto.getPassword(),userRequestDto.getLocation());

            userResponseDto.setUserId(user.getId());
            userResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception exception){
            userResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return ResponseEntity.ok(userResponseDto);

    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        Optional<User>user;
        try{
            user = userService.getUserById(id);
        }catch (Exception exception){
            throw new RuntimeException();
        }
       return user;

    }
}
