package com.userservice.user.controllers.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRolesRequestDto {
//    private String email;
//    private String password;
//
//    private String name;
//    private String location;
    private List<Long>roleIds;

}
