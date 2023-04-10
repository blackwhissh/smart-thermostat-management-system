package com.blackwhissh.smartthermostat.controller;

import com.blackwhissh.smartthermostat.model.Thermostat;
import com.blackwhissh.smartthermostat.security.config.JwtService;
import com.blackwhissh.smartthermostat.service.ThermostatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/thermostat")
public class ThermostatController {

    private final JwtService jwtService;

    private final ThermostatService thermostatService;

    @Autowired
    public ThermostatController(JwtService jwtService, ThermostatService thermostatService) {
        this.jwtService = jwtService;
        this.thermostatService = thermostatService;
    }

    @GetMapping()
    public List<Thermostat> getThermostats(@RequestHeader ("Authorization") String sessionToken){
        Integer userId = jwtService.extractUserId(sessionToken.substring(7));
        return thermostatService.getThermostats(userId);
    }

    @GetMapping(path = "{thermostatId}")
    public Optional<Thermostat> getThermostat(@PathVariable("thermostatId") Long id, @RequestHeader ("Authorization") String sessionToken){
        Integer userId = jwtService.extractUserId(sessionToken.substring(7));
        return thermostatService.getThermostat(id, userId);
    }

    @PostMapping
    public void addThermostat(@RequestBody Thermostat thermostat, @RequestHeader("Authorization") String sessionToken){
        Integer userId = jwtService.extractUserId(sessionToken.substring(7));
        thermostat.setUserId(userId);
        thermostatService.addNewThermostat(thermostat);
    }


    @DeleteMapping(path = "{thermostatId}")
    public void deleteThermostat(@PathVariable("thermostatId") Long id, @RequestHeader ("Authorization") String sessionToken) throws NullPointerException{
        Integer userId = jwtService.extractUserId(sessionToken.substring(7));
        thermostatService.deleteThermostat(id, userId);
    }

    @PutMapping(path = "{thermostatId}")
    public void updateThermostat(@PathVariable("thermostatId") Long id,
                                 @RequestParam(required = false) String deviceName,
                                 @RequestParam(required = false) Double threshold,
                                 @RequestHeader ("Authorization") String sessionToken){
        Integer userId = jwtService.extractUserId(sessionToken.substring(7));

        thermostatService.updateThermostat(id, deviceName, threshold, userId);
    }




}
