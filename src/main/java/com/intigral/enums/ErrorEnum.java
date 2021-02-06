package com.intigral.enums;

import java.lang.constant.Constable;

public enum ErrorEnum implements Constable{

	ERROR_INVALID_TYPE("ERROR_INVALID_TYPE", "invalid type,", "FUNCTIONAL"),
	ERROR_INVALID_CITY_OR_COUNTRY("ERROR_INVALID_CITY_OR_COUNTRY", "invalid city,country or country code,", "FUNCTIONAL"),
	ERROR_INVALID_LATTITUDE_OR_LONTITUTDE("ERROR_INVALID_LATTITUDE_OR_LONTITUTDE", "invalid DATA,", "FUNCTIONAL");

	String code;
	String desc;
	String type;

	
	private ErrorEnum(String code, String desc, String type) {
		this.code = code;
		this.desc = desc;
		this.type = type;
	}

	public String getCode()
	{
		return code;
	}
	public String getDesc()
	{
		return desc;
	}
	public String getType()
	{
		return type;
	}
}
