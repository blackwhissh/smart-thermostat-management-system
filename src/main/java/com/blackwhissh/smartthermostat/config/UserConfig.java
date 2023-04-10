package com.blackwhissh.smartthermostat.config;

import com.blackwhissh.smartthermostat.model.Role;
import com.blackwhissh.smartthermostat.model.User;
import com.blackwhissh.smartthermostat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setEmail("user@gmail.com");
        user1.setPassword("1234");
        user1.setActive(true);
        user1.setRole(Role.USER);


        User user2 = new User();
        user2.setEmail("admin@gmail.com");
        user2.setPassword("1234");
        user2.setActive(true);
        user2.setRole(Role.ADMIN);

        userRepository.save(user1);
        userRepository.save(user2);


    }
}
