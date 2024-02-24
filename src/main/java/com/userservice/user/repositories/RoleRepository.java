package com.userservice.user.repositories;

import com.userservice.user.models.Role;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByIdIn(List<Long>roleIds);
}
