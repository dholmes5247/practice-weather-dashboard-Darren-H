package com.example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication // Marks this as a Spring Boot application and enables component scanning
public class WeatherApplication implements CommandLineRunner {


    // Injects the WeatherController bean so it can be used in this class
    @Autowired
    private WeatherController weatherController;


    // Main method – entry point for the Spring Boot application
    public static void main(String[] args) {
        // Launches the Spring Boot application and initializes the Spring context
        SpringApplication.run(WeatherApplication.class, args);
    }

    /* The run() method is executed automatically after the application starts.
     Here, it fetches weather data for a few sample cities. */


    @Override
    public void run(String... args) {
        // Calls the controller to fetch and display weather for each specified city
        weatherController.getWeatherForCity("Portland", "OR", "US");
        weatherController.getWeatherForCity("St Louis", "MO", "US");
        weatherController.getWeatherForCity("Miami", "FL", "US");
        weatherController.getWeatherForCity("Bangor", "MA", "US");
        weatherController.getWeatherForCity("Marseille", "", "France");
        weatherController.getWeatherForCity("Berlin", "", "Germany");
        weatherController.getWeatherForCity("Kiev", "", "Ukraine");
        weatherController.getWeatherForCity("Kiev", "MO", "US");
    }
}



/* @SpringBootApplication: Tells Spring to scan for components and configure the app.

CommandLineRunner: Runs custom code after the app starts.

@Autowired: Automatically provides the WeatherController dependency.

run(): Triggers the weather fetch for several cities when the app launches. */
