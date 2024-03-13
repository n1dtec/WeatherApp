package com.example.weatherapp.repository;

import com.example.weatherapp.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Harnidh Kaur
 *
 * Class Name: Sensor Data Repository
 * Description: This is a repository interface for accessing sensor data from the database.
 */
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findBySensorIdAndRecordedAtBetween(String sensorId, LocalDateTime start, LocalDateTime end);
    List<SensorData> findByRecordedAtBetween(LocalDateTime start, LocalDateTime end);
}

