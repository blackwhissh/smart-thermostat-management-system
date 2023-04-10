package com.blackwhissh.smartthermostat;

import com.blackwhissh.smartthermostat.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class SmartThermostatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartThermostatApplication.class, args);


	}


}
