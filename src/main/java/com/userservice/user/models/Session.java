package com.userservice.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel {
    private  String token;
    private Date expiryAt;
    @ManyToOne
    private  User user;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}
