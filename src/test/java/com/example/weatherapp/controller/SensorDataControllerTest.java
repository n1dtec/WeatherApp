package com.example.weatherapp.controller;

import com.example.weatherapp.dto.AverageSensorDataDto;
import com.example.weatherapp.service.SensorDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author Harnidh Kaur
 */
@WebMvcTest(SensorDataController.class)
public class SensorDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorDataService sensorDataService;

    @Test
    public void testRegisterSensorData() throws Exception {
        mockMvc.perform(post("/api/sensors/data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sensorId\":\"sensor1\", \"temperature\":25.5, \"humidity\":50.0, \"windSpeed\":5.5, \"recordedAt\":\"2020-01-01T00:00:00\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAverageSingleSensor() throws Exception {
        AverageSensorDataDto averageDto = new AverageSensorDataDto(25.0, 60.0);
        Mockito.when(sensorDataService.calculateAverages(Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class)))
                .thenReturn(averageDto);

        mockMvc.perform(get("/api/sensors/average?sensorId=sensor1&start=2020-01-01T00:00:00&end=2020-01-02T00:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"averageTemperature\":25.0,\"averageHumidity\":60.0}"));
    }

    @Test
    public void testGetAverageAllSensors() throws Exception {
        AverageSensorDataDto averageDto = new AverageSensorDataDto(25.0, 60.0);
        Mockito.when(sensorDataService.calculateAverages(Mockito.any(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class)))
                .thenReturn(averageDto);

        mockMvc.perform(get("/api/sensors/average?start=2020-01-01T00:00:00&end=2020-01-02T00:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"averageTemperature\":25.0,\"averageHumidity\":60.0}"));
    }
}

