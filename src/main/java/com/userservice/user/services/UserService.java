package com.userservice.user.services;

import com.userservice.user.controllers.Dto.UserResponseDto;
import com.userservice.user.models.Role;
import com.userservice.user.models.User;
import com.userservice.user.repositories.RoleRepository;
import com.userservice.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public UserResponseDto getUserDetails(Long userId){
            return  new UserResponseDto();//returning an empty user for now. Update this to fetch user details from the DB
    }
    public UserResponseDto setUserRoles(Long userId, List<Long>roleIds){
        Optional<User>userOptional = userRepository.findById(userId);
        Set<Role>roles = roleRepository.findAllByIdIn(roleIds);

        if(userOptional.isEmpty()){
            return  null;
        }
        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));
        User savedUser = userRepository.save(user);
        return  UserResponseDto.from(savedUser);
    }
/*
    @Override
    public User signUp(String name, String email, String password, String location) {
       Optional<User>userOptional = userRepository.findByEmailId(email);
       if(userOptional.isPresent()){
           throw new RuntimeException();
       }
        User user = new User();
       user.setName(name);
       user.setEmailId(email);
//       user.setPassword(password);
        UUID uuid = UUID.randomUUID();
//        UUID uuid = UUID.fromString(password); //doesnt work
        user.setPasswordUuid(uuid);
       user.setLocation(location);
       return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    */

}
