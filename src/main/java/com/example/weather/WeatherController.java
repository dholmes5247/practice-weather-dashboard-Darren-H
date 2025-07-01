package com.example.weather;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component // Marks this class as a Spring-managed component so it can be injected
public class WeatherController {

    // OpenWeatherMap API key
    private final String API_KEY = "b6c1703a59c748c6476b9ae6b4aac237";

    // RestTemplate is used to make HTTP requests to external APIs
    private final RestTemplate restTemplate = new RestTemplate();


     // Fetches and prints weather data for a given city, state, and country.

    public void getWeatherForCity(String city, String state, String country) {
        try {

            // Step 1: Use OpenWeatherMap's Geo API to get latitude and longitude for the city

            String geoUrl = "http://api.openweathermap.org/geo/1.0/direct?q=" +
                    city + "," + state + "," + country + "&limit=1&appid=" + API_KEY;

            // Make the API call and map the JSON response to an array of GeoLocation objects

            GeoLocation[] locations = restTemplate.getForObject(geoUrl, GeoLocation[].class);

            // If no locations were found, print an error and exit
            if (locations == null || locations.length == 0) {
                System.out.println("\nCITY NOT found: " + city);
                return;
            }

            // Extract lat and lon from the result

            double lat = locations[0].lat;
            double lon = locations[0].lon;

            // Step 2: Use OpenWeatherMap's Weather API to get current weather using lat/lon
            String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
                    + "&lon=" + lon + "&appid=" + API_KEY + "&units=imperial";

            // Make the API call and map the JSON response
            WeatherResponse response = restTemplate.getForObject(weatherUrl, WeatherResponse.class);



            // Step 3: Print the weather data to the console

            System.out.println("\nWeather for " + city + ", " + state + ", " + country + ":");
            System.out.println("Temperature: " + response.main.temp + " °F");
            System.out.println("Description: " + response.weather[0].description);
            System.out.println("Humidity: " + response.main.humidity + "%");

        } catch (Exception e) {
            // Catch and print errors
            System.out.println("Error fetching weather for " + city + ": " + e.getMessage());
        }

    }
}


