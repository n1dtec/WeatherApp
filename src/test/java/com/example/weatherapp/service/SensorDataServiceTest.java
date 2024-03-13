package com.example.weatherapp.service;

import com.example.weatherapp.model.SensorData;
import com.example.weatherapp.repository.SensorDataRepository;
import com.example.weatherapp.dto.AverageSensorDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Harnidh Kaur
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SensorDataServiceTest {

    @Mock
    private SensorDataRepository sensorDataRepository;

    @InjectMocks
    private SensorDataService sensorDataService;

    @Test
    public void testCalculateAveragesNoData() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        when(sensorDataRepository.findByRecordedAtBetween(start, end)).thenReturn(Collections.emptyList());

        AverageSensorDataDto averages = sensorDataService.calculateAverages(null, start, end);

        assertEquals(Double.NaN, averages.getAverageTemperature(), "Average temperature should be NaN for no data");
        assertEquals(Double.NaN, averages.getAverageHumidity(), "Average humidity should be NaN for no data");
    }

    @Test
    public void testCalculateAveragesWithSingleDataPoint() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        List<SensorData> data = Arrays.asList(
                new SensorData("sensor1", 20.0, 70.0, 5.0, LocalDateTime.now())
        );
        when(sensorDataRepository.findByRecordedAtBetween(start, end)).thenReturn(data);

        AverageSensorDataDto averages = sensorDataService.calculateAverages(null, start, end);

        assertEquals(20.0, averages.getAverageTemperature(), "Average temperature should be the mean of the data points");
        assertEquals(70.0, averages.getAverageHumidity(), "Average humidity should be the mean of the data points");
    }

    @Test
    public void testCalculateAveragesWithMultipleDataPoints() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        List<SensorData> data = Arrays.asList(
                new SensorData("sensor1", 20.0, 70.0, 5.0, LocalDateTime.now()),
                new SensorData("sensor2", 30.0, 50.0, 6.0, LocalDateTime.now().plusHours(1))
        );
        when(sensorDataRepository.findByRecordedAtBetween(start, end)).thenReturn(data);

        AverageSensorDataDto averages = sensorDataService.calculateAverages(null, start, end);

        assertEquals(25.0, averages.getAverageTemperature(), "Average temperature should be the mean of the data points");
        assertEquals(60.0, averages.getAverageHumidity(), "Average humidity should be the mean of the data points");
    }
}
