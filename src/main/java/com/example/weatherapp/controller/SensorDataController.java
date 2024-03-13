package com.example.weatherapp.controller;

import com.example.weatherapp.dto.AverageSensorDataDto;
import com.example.weatherapp.model.SensorData;
import com.example.weatherapp.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Harnidh Kaur
 *
 * Class Name: Sensor Data Controller
 * Description: This is a REST controller to expose the API endpoints.
 */
@RestController
@RequestMapping("/api/sensors")
public class SensorDataController {
    @Autowired
    private SensorDataService service;

    /**
     * This API is used for saving sensor data based on request body.
     *
     * @param data - sensor data to be saved
     *
     * @return responseEntity - returns the object that was saved in the database
     */
    @PostMapping("/data")
    public ResponseEntity<?> registerSensorData(@RequestBody SensorData data) {
        SensorData savedData = service.saveSensorData(data);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    /**
     * This API gets the average metrics (temperature and humidity) for a specific sensor or all sensors.
     *
     * @param sensorId (optional) - Returns averages for one sensor if sensor id is provided, otherwise all averages
     * @param start - start time for calculating averages
     * @param end - end time for calculating averages
     *
     * @return responseEntity - response contains the average metrics for sensor(s)
     */
    @GetMapping("/average")
    public ResponseEntity<AverageSensorDataDto> getAverage(@RequestParam Optional<String> sensorId,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        AverageSensorDataDto average;
        average = service.calculateAverages(sensorId.orElse(null), start, end);
        return new ResponseEntity<>(average, HttpStatus.OK);
    }
}

