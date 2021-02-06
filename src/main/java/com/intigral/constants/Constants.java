package com.intigral.constants;

import java.util.Set;

public class Constants {

	private Constants() {
	}

	public static final String COORDINATES = "COORDINATES";
	public static final String CITY = "CITY";
	public static final Set<String> ALLOWED_TYPE = Set.of(COORDINATES, CITY);

}
