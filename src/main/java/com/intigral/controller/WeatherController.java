package com.intigral.controller;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.intigral.constants.Constants;
import com.intigral.enums.ErrorEnum;
import com.intigral.exception.ApplicationException;
import com.intigral.services.WeatherService;
import com.neovisionaries.i18n.CountryCode;

@ComponentScan("com.intigral")
@Controller
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ResponseEntity<?> getWeather(@RequestParam(required = false, value = "city") String city,
			@RequestParam(required = false, value = "country") String country,
			@RequestParam(required = false, value = "lon") String lat,
			@RequestParam(required = false, value = "lon") String lon,
			@RequestParam(required = false, value = "type") String type)
			throws JsonParseException, JsonMappingException, IOException {

		validateQueryParam(city, country, lat, lon, type);
		// validate from configured city country if there is any API or db
		// configurations
		JsonNode node = weatherService.getWeather(city, country, lat, lon, type);
		return ResponseEntity.ok(node);

	}

	private void validateQueryParam(String city, String country, String lat, String lon, String type) {
		if (Constants.CITY.equalsIgnoreCase(type)) {
			if (StringUtils.isBlank(city) || StringUtils.isBlank(country)
					|| Objects.isNull(CountryCode.getByCode(country)))
				throw new ApplicationException(ErrorEnum.ERROR_INVALID_CITY_OR_COUNTRY);
		} else if (Constants.COORDINATES.equalsIgnoreCase(type)) {
			if (!NumberUtils.isCreatable(lat) || !NumberUtils.isCreatable(lon))
				throw new ApplicationException(ErrorEnum.ERROR_INVALID_LATTITUDE_OR_LONTITUTDE);

		} else {
			throw new ApplicationException(ErrorEnum.ERROR_INVALID_TYPE);
		}

	}

}