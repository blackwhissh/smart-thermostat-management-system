package com.blackwhissh.smartthermostat.controller;

import com.blackwhissh.smartthermostat.model.Thermostat;
import com.blackwhissh.smartthermostat.service.ThermostatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/thermostat")
public class ThermostatController {

    @Autowired
    private final ThermostatService thermostatService;

    @Autowired
    public ThermostatController(ThermostatService thermostatService) {
        this.thermostatService = thermostatService;
    }

    @GetMapping()
    public List<Thermostat> getThermostats(){
        return thermostatService.getThermostats();
    }

    @GetMapping(path = "{thermostatId}")
    public Optional<Thermostat> getThermostat(@PathVariable("thermostatId") Long id){
        return thermostatService.getThermostat(id);
    }

    @PostMapping
    public void addThermostat(@RequestBody Thermostat thermostat){
        thermostatService.addNewThermostat(thermostat);
    }


    @DeleteMapping(path = "{thermostatId}")
    public void deleteThermostat(@PathVariable("thermostatId") Long id) throws NullPointerException{
        thermostatService.deleteThermostat(id);
    }

    @PutMapping(path = "{thermostatId}")
    public void updateThermostat(@PathVariable("thermostatId") Long id,
                                 @RequestParam(required = false) String deviceName,
                                 @RequestParam(required = false) Double threshold){
        thermostatService.updateThermostat(id, deviceName, threshold);
    }



}
