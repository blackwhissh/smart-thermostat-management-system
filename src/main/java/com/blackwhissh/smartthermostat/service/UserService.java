package com.blackwhissh.smartthermostat.service;

import com.blackwhissh.smartthermostat.model.User;
import com.blackwhissh.smartthermostat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with provided ID: " + userId + " does not exists");
        }else{
            return userRepository.findById(userId);
        }
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if(!exists){
            throw new IllegalStateException("User with ID: " + userId + " is not found");
        }else {
            userRepository.deleteById(userId);
        }
    }
}
