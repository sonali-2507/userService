package com.userservice.user.controllers.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {
    private String token;
    private Long userId;
}
