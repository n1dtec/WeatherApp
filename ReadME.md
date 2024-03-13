# Weather Data Service API Documentation

This documentation provides an overview of the Weather Data Service API, including descriptions of each API endpoint, the choice of frameworks and technologies, and detailed steps for running and testing the project.

## Project Description

The Weather Data Service is a RESTful API application designed to register and query weather sensor data. It allows for the registration of weather metrics such as temperature, humidity, and wind speed from various sensors and supports querying the average value of these metrics (temperature and humidity) over specific date ranges. The solution can be extended to support more metrics.

## Technology Stack and Frameworks

* **Spring Boot**: I have chosen Spring Boot framework for its ease of use, productivity, and the vast ecosystem. It simplifies the development of stand-alone, production-grade Spring-based applications.
* **Spring Data JPA**: This library simplifies data access operations, reducing the amount of boilerplate code required and allowing more focus on the business logic. 
* **H2 Database**: This is an in-memory database used for simplicity and ease of setup, making it ideal for proof of concept, early development and testing phases. 
* **Mockito**: For writing unit and integration tests, I am using Mockito since due to its reliability and functionality for testing various aspects of the application.

## How to Run the Application

1. **Prerequisites**: Java JDK (version 8 or above) and Maven installed on your system.
2. **Clone the repository in your IDE of choice**:
```markdown
git clone https://github.com/n1dtec/WeatherApp.git
cd weatherapp
```
3. **Build the project**
```markdown
mvn clean install
```
4. **Run the application**
```markdown
mcn spring-boot:run
```
The application will start running on http://localhost:8080.

## API Endpoints

### Register Sensor Data

* **URL**: /api/sensors/data 
* **Method**: POST 
* **Description**: Register sensor data for a specific sensor.
* **Request Body**:
```json
{
  "sensorId": "sensor123",
  "temperature": 22.5,
  "humidity": 45.0,
  "windSpeed": 5.5,
  "recordedAt": "2024-03-12T10:00:00"
}
```
* **Sample Response**: HTTP 201 Created on success. 

### Query Average Sensor Data
  
* **URL**: /api/sensors/average
* **Method**: GET
* **Description**: Query the average metric value for all sensors or a specific sensor in a date range.
* **Parameters**:
  * **sensorId** (optional): The ID of the sensor. Omit to calculate the average for all sensors.
  * **start**: Start of the date range (ISO date-time format).
  * **end**: End of the date range (ISO date-time format).
* **Response**:
```markdown
{
  "averageTemperature": 23.5,
  "averageHumidity":50.0
}
```
Returns the average temperature and average humidity within the specified date range.

## How to Test the Application

Using Postman or Curl
1. **Register Sensor Data**:
  * Use the following command for Windows PowerShell:
```markdown
curl -Method POST -uri http://localhost:8080/api/sensors/data -H @{ "content-type" = "application/json"} -Body '{"sensorId": "sensor123", "temperature": 22.5, "humidity": 45.0, "windSpeed": 5.5, "recordedAt": "2024-03-12T10:00:00"}'
```
  * Use the following command for other terminals:

```markdown
curl -X POST http://localhost:8080/api/sensors/data -H 'Content-Type: application/json' -d '{"sensorId": "sensor123", "temperature": 22.5, "humidity": 45.0, "windSpeed": 5.5, "recordedAt": "2024-03-12T10:00:00"}'
```
2. **Query Average Sensor Data**:
  * Use the following command for Windows PowerShell:
```markdown
curl -Method GET -uri 'http://localhost:8080/api/sensors/average?start=2024-03-12T00:00:00&end=2024-03-13T00:00:00'
```
  * Use the following command for other terminals:
```markdown
curl -X GET 'http://localhost:8080/api/sensors/average?start=2024-03-12T00:00:00&end=2024-03-13T00:00:00'
```

Using unit tests
* **Run the Tests using Maven**:
```markdown
mvn test
```
This command will execute all tests in the project, printing the results to the console.

