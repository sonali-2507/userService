package com.userservice.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.xml.crypto.Data;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass
//it allows u to automatically populate certain field in your JPA entities
//such as creation date, modification date, created by, and modified by
//@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
//    @CreatedDate
//    @Column(name = "created_date")
//    private Date createdAt;
//    @LastModifiedDate
//    @Column(name = "last_modified_date")
//    private Date lastModifiedAt;
}
