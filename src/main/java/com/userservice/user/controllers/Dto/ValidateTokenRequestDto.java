package com.userservice.user.controllers.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
