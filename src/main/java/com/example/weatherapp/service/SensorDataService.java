package com.example.weatherapp.service;

import com.example.weatherapp.dto.AverageSensorDataDto;
import com.example.weatherapp.model.SensorData;
import com.example.weatherapp.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Harnidh Kaur
 *
 * Class Name: Sensor Data Service
 * Description: This is the service layer to handle business logic of the application.
 */
@Service
public class SensorDataService {
    @Autowired
    private SensorDataRepository repository;

    /**
     * This method is used for saving sensor data in the database
     *
     * @param data - data object to be saved in the database
     *
     * @return SensorData - sensor data that was saved in the database
     */
    public SensorData saveSensorData(SensorData data) {
        return repository.save(data);
    }

    /**
     * This method return the average metrics (temperature and humidity) for input Sensor Data.
     * This can be extended for other metrics as needed.
     *
     * @param sensorId - Sensor id for which we need to calculate average
     * @param start - start time for calculating average
     * @param end - end time for calculating average
     *
     * @return averageSensorData - object containing averages of sensor metrics
     */
    public AverageSensorDataDto calculateAverages(String sensorId, LocalDateTime start, LocalDateTime end) {
        List<SensorData> data;
        if (sensorId == null) {
            data = repository.findByRecordedAtBetween(start, end);
        } else {
            data = repository.findBySensorIdAndRecordedAtBetween(sensorId, start, end);
        }

        double averageTemperature = data.stream().mapToDouble(SensorData::getTemperature).average().orElse(Double.NaN);
        double averageHumidity = data.stream().mapToDouble(SensorData::getHumidity).average().orElse(Double.NaN);

        return new AverageSensorDataDto(averageTemperature, averageHumidity);
    }
}

