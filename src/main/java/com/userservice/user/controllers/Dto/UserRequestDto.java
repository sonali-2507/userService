package com.userservice.user.controllers.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String password;

    private String name;
    private String location;
}
