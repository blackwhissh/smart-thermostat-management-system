package com.blackwhissh.smartthermostat.controller;

import com.blackwhissh.smartthermostat.model.User;
import com.blackwhissh.smartthermostat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = {"userId"})
    public Optional<User> getUser(@PathVariable ("userId") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) throws NullPointerException {
        userService.deleteUser(userId);
    }





}
