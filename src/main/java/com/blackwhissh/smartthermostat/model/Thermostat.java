package com.blackwhissh.smartthermostat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Random;

@Entity
@Table(name = "thermostats")
@AllArgsConstructor
@Getter
@Setter
public class Thermostat {
    @Id
    @SequenceGenerator(
            name = "thermostat_sequence",
            sequenceName = "thermostat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "thermostat_sequence"
    )
    @Column(name = "thermostat_id")
    private Long id;

    @Column(name = "thermostat_name")
    private String deviceName;

    @Column(name = "thermostat_temperature")
    private Double temperature;

    @Column(name = "thermostat_threshold")
    private Double threshold;

    @Column(name = "thermostat_condition")
    private String condition;

    @Column(name = "user_id")
    private Integer userId;



    public Thermostat() {
        DecimalFormat df = new DecimalFormat("#.##");
        //Generate value between 5 - 35
        Random random = new Random();
        var x = random.nextDouble() * 30 + 5;

        //Display fewer digits
        double factor = Math.pow(10,2);
        this.temperature = Math.round(x*factor)/factor;
    }

    public Thermostat(Long id, String deviceName, Double threshold) {
        this.id = id;
        this.deviceName = deviceName;
        this.threshold = threshold;

        updateCondition();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
        updateCondition();
    }

    public String getCondition() {
        return condition;
    }


    public void updateCondition() {
        if(temperature < threshold){
            condition = "NORMAL";
        }else if(temperature > threshold){
            condition = "CRITICAL";
        }else{
            condition = "IDLE";
        }
    }

    @Override
    public String toString() {
        return "Thermostat{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", temperature=" + temperature +
                ", threshold=" + threshold +
                ", condition='" + condition + '\'' +
                '}';
    }
}
