package com.intigral.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface WeatherService {

	JsonNode getWeather(String city, String country, String lat, String lon, String type);
}
