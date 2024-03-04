package com.userservice.user.controllers.Dto;

import com.userservice.user.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private Set<Role> roles;
}
