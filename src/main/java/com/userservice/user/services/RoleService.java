package com.userservice.user.services;

import com.userservice.user.models.Role;
import com.userservice.user.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(String name) {
        Role role =  new Role();
        role.setRole(name);
        return roleRepository.save(role);
    }
}
