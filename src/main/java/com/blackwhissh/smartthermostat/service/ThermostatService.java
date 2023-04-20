package com.blackwhissh.smartthermostat.service;

import com.blackwhissh.smartthermostat.exceptions.ThermostatNotFoundException;
import com.blackwhissh.smartthermostat.model.Thermostat;
import com.blackwhissh.smartthermostat.repository.ThermostatRepository;
import com.blackwhissh.smartthermostat.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            throw new ThermostatNotFoundException();
        }else{
            Optional<Thermostat> thermostat = thermostatRepository.findById(id);
            if(thermostat.get().getUserId() != userId){
                throw new ThermostatNotFoundException();
            }else {
                return thermostatRepository.findById(id);
            }
        }
    }


    public ResponseEntity<String> addNewThermostat(Thermostat thermostat) {

        thermostatRepository.save(thermostat);

        return new ResponseEntity<>("Thermostat added successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<String> deleteThermostat(Long id, Integer userId) {
        boolean exists = thermostatRepository.existsById(id);
        if (!exists) {
            throw new ThermostatNotFoundException();
        } else {
            Optional<Thermostat> thermostat = thermostatRepository.findById(id);
            if (thermostat.get().getUserId() != userId) {
                throw new ThermostatNotFoundException();
            } else {
                thermostatRepository.deleteById(id);
            }
        }
        return new ResponseEntity<>("Thermostat deleted successfully", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateThermostat(Long id, String deviceName, Double threshold, Integer userId) {
        Thermostat thermostat = thermostatRepository.findById(id)
                .orElseThrow(ThermostatNotFoundException::new);

        if(thermostat.getUserId() != userId){
            throw new ThermostatNotFoundException();
        }

        if(deviceName != null && deviceName.length() > 0  && !Objects.equals(thermostat.getDeviceName() , deviceName)){
            thermostat.setDeviceName(deviceName);
            thermostatRepository.save(thermostat);
        }
        thermostat.setThreshold(threshold);
        thermostatRepository.save(thermostat);
        return new ResponseEntity<>("Thermostat updated successfully", HttpStatus.OK);
    }

}
