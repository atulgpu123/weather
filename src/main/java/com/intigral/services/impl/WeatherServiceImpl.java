package com.intigral.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.intigral.constants.Constants;
import com.intigral.models.WeatherUrl;
import com.intigral.services.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeatherUrl weatherData;

	@Override
	@Cacheable(cacheNames = "weatherCache", key = "#root.args[0]+#root.args[1]+#root.args[2]+#root.args[3]+#root.args[4]")
	public JsonNode getWeather(String city, String country, String lat, String lon, String type) {

		UriComponents uriComponents = null;

		if (Constants.CITY.equalsIgnoreCase(type)) {
			uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(weatherData.getUrl()).path("")
					.query("q={keyword}&appid={appid}")
					.buildAndExpand(String.join(",", city, country), weatherData.getApiKey());
			log.info("uriComponents.toUriString(){}", uriComponents.toUriString());
		} else {
			uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(weatherData.getUrl()).path("")
					.query("lat={lat}&lon={lon}&appid={appid}").buildAndExpand(lat, lon, weatherData.getApiKey());
		}
		String uri = uriComponents.toUriString();
		log.info("uriComponents.toUriString(){}", uriComponents.toUriString());
		ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper.convertValue(resp.getBody(), JsonNode.class);

	}

}
