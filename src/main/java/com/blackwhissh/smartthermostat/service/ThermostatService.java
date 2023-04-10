package com.blackwhissh.smartthermostat.service;

import com.blackwhissh.smartthermostat.model.Thermostat;
import com.blackwhissh.smartthermostat.repository.ThermostatRepository;
import com.blackwhissh.smartthermostat.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Thermostat> getThermostats(Integer userId) {
        return thermostatRepository.findByUserId(userId);
    }

    public Optional<Thermostat> getThermostat(Long id, Integer userId) {

        boolean exists = thermostatRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Thermostat with given id " + id + " does not exists");
        }else{
            Optional<Thermostat> thermostat = thermostatRepository.findById(id);
            if(thermostat.get().getUserId() != userId){
                throw new IllegalStateException("Thermostat with id " + id + " with given user id " + userId + " does not exists");
            }else {
                return thermostatRepository.findById(id);
            }
        }
    }


    public void addNewThermostat(Thermostat thermostat) {


        thermostatRepository.save(thermostat);

    }

    public void deleteThermostat(Long id, Integer userId) {
        boolean exists = thermostatRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Thermostat with given id " + id + " does not exists");
        } else {
            Optional<Thermostat> thermostat = thermostatRepository.findById(id);
            if (thermostat.get().getUserId() != userId) {
                throw new IllegalStateException("Thermostat with id " + id + " with given user id " + userId + " does not exists");
            } else {
                thermostatRepository.deleteById(id);
            }
        }
    }

    @Transactional
    public void updateThermostat(Long id, String deviceName, Double threshold, Integer userId) {
        Thermostat thermostat = thermostatRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Thermostat with given id " + id + " does not exists"));

        if(thermostat.getUserId() != userId){
            throw new IllegalStateException("Thermostat with given user id " + userId + " does not exists");
        }

        if(deviceName != null && deviceName.length() > 0  && !Objects.equals(thermostat.getDeviceName() , deviceName)){
            thermostat.setDeviceName(deviceName);
            thermostatRepository.save(thermostat);
        }
        thermostat.setThreshold(threshold);
        thermostatRepository.save(thermostat);
    }

}
