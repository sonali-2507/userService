package com.userservice.user.controllers.Dto;

import com.userservice.user.models.Role;
import com.userservice.user.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
//    private  ResponseStatus responseStatus;
//    private Long userId;
    private String email;
    private Set<Role>roles = new HashSet<>();

    //create a deep copy from existing user
    public static UserResponseDto from (User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        //set other values when required
        return  userResponseDto;
    }

}
