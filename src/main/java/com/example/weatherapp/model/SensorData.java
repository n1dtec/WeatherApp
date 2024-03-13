package com.example.weatherapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * @author Harnidh Kaur
 *
 * Class Name: Sensor Data
 * Description: This is the domain model to represent the metrics which can be read by weather sensor.
 */
@Entity
public class SensorData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String sensorId;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private LocalDateTime recordedAt;

    // Constructor
    public SensorData() {}

    // Constructor with parameters
    public SensorData(String sensorId, double temperature, double humidity, double windSpeed, LocalDateTime recordedAt) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.recordedAt = recordedAt;
    }

    // Getters and Setters

    public double getTemperature() { // This is the getter method for temperature
        return temperature;
    }

    public void setTemperature(double temperature) { // This is the setter method for temperature
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
