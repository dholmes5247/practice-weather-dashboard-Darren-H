package com.example.weather;

public class WeatherResponse {
    public Weather[] weather;
    public Main main;

    public static class Weather {
        public String description;
    }

    public static class Main {
        public double temp;
        public int humidity;
    }
}

