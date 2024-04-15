package com.userservice.user.controllers;

import com.userservice.user.controllers.Dto.SetUserRolesRequestDto;
import com.userservice.user.controllers.Dto.UserResponseDto;
import com.userservice.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserDetails(@PathVariable("id") Long userId){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail("test@test.com");
//        UserResponseDto userResponseDto = userService.getUserDetails(userId);
        return  new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserResponseDto> setUserRoles(@PathVariable("id") Long userId,
                                                        @RequestBody SetUserRolesRequestDto request ){
        UserResponseDto userResponseDto = userService.setUserRoles(userId,request.getRoleIds());
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }


/*
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

 */
}
