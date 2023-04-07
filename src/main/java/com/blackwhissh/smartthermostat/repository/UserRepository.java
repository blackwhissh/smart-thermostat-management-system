package com.blackwhissh.smartthermostat.repository;

import com.blackwhissh.smartthermostat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}