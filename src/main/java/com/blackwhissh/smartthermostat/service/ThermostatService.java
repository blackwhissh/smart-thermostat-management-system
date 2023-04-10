package com.blackwhissh.smartthermostat.service;

import com.blackwhissh.smartthermostat.model.Thermostat;
import com.blackwhissh.smartthermostat.repository.ThermostatRepository;
import com.blackwhissh.smartthermostat.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThermostatService {


    @Autowired
    private final ThermostatRepository thermostatRepository;

    @Autowired
    private final UserRepository userRepository;

    public List<Thermostat> getThermostats() {
        return thermostatRepository.findAll();
    }

    public Optional<Thermostat> getThermostat(Long id) {
        boolean exists = thermostatRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Thermostat with given id " + id + " does not exists");
        }else {
            return thermostatRepository.findById(id);
        }
    }


    public void addNewThermostat(Thermostat thermostat) {

        boolean exists = userRepository.existsById(thermostat.getUserId());

        if(exists){
            throw new IllegalStateException("User with given ID: " + thermostat.getUserId() + " Already exists");
        }else {
            thermostatRepository.save(thermostat);
        }

    }

    public void deleteThermostat(Long id) {
        boolean exists = thermostatRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Thermostat with given id " + id + " does not exists");
        }else{
            thermostatRepository.deleteById(id);
        }
    }

    @Transactional
    public void updateThermostat(Long id, String deviceName, Double threshold) {
        Thermostat thermostat = thermostatRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Thermostat with given id " + id + " does not exists"));

        if(deviceName != null && deviceName.length() > 0 && !Objects.equals(thermostat.getDeviceName(), deviceName)){
            thermostat.setDeviceName(deviceName);
            thermostatRepository.save(thermostat);
        }
        thermostat.setThreshold(threshold);
        thermostatRepository.save(thermostat);
    }

//    public void assignUser(Long thermostatId, Long userId) {
//        Thermostat thermostat = thermostatRepository.findById(thermostatId)
//                .orElseThrow(() -> new IllegalStateException("Thermostat with given id " + thermostatId + " does not exists"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException("User with given id " + userId + " does not exists"));
//
//        thermostat.setUser(user);
//        thermostatRepository.save(thermostat);
//    }
}
