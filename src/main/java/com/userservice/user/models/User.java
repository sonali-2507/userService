package com.userservice.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.random.RandomGenerator;
@Entity
@Getter
@Setter
public class User extends  BaseModel {
//    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>() ;
//    private UUID passwordUuid;

}
