package com.blackwhissh.smartthermostat.config;

import com.blackwhissh.smartthermostat.model.User;
import com.blackwhissh.smartthermostat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("1234");

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("1234");

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("1234");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(admin);

    }
}
