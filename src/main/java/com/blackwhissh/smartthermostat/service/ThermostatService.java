package com.blackwhissh.smartthermostat.service;

import com.blackwhissh.smartthermostat.model.Thermostat;
import com.blackwhissh.smartthermostat.repository.ThermostatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ThermostatService {

    private final ThermostatRepository thermostatRepository;

    @Autowired
    public ThermostatService(ThermostatRepository thermostatRepository) {
        this.thermostatRepository = thermostatRepository;
    }

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
        thermostatRepository.save(thermostat);
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
}
