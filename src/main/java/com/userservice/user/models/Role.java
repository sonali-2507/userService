package com.userservice.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Role extends BaseModel{

    private String role;
}
