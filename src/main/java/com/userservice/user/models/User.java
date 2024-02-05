package com.userservice.user.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@Entity
public class User extends  BaseModel {
    private String name;
    private String emailId;
    private String password;
    private String location;
}
