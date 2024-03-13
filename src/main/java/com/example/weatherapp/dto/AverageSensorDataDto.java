package com.example.weatherapp.dto;

/**
 * @author Harnidh Kaur
 *
 * Class Name: Average Sensor Data DTO
 * Description: This data transfer object (DTO) holds the average temperature and humidity values,
 * simplifying the transfer of multiple pieces of information.
 */
public class AverageSensorDataDto {
    private double averageTemperature;
    private double averageHumidity;

    public AverageSensorDataDto(double averageTemperature, double averageHumidity) {
        this.averageTemperature = averageTemperature;
        this.averageHumidity = averageHumidity;
    }

    // Getters
    public double getAverageTemperature() {
        return averageTemperature;
    }

    public double getAverageHumidity() {
        return averageHumidity;
    }

    // Setters
    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }
}

