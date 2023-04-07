package com.blackwhissh.smartthermostat.repository;

import com.blackwhissh.smartthermostat.model.Thermostat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThermostatRepository extends JpaRepository<Thermostat, Long> {
}
