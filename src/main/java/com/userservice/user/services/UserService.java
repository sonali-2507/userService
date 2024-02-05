package com.userservice.user.services;

import com.userservice.user.models.User;
import com.userservice.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signUp(String name, String email, String password, String location) {
       Optional<User>userOptional = userRepository.findByEmailId(email);
       if(userOptional.isPresent()){
           throw new RuntimeException();
       }
        User user = new User();
       user.setName(name);
       user.setEmailId(email);
       user.setPassword(password);
       user.setLocation(location);
       return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
