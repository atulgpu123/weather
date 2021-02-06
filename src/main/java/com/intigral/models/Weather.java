package com.intigral.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class Weather implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9079108590474178565L;
	public Coord coord;
    public List<WeatherData> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public Long dt;
    public Sys sys;
    public Long id;
    public String name;
    public Double cod;
    
    @Data
    public class WeatherData implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = -158124002826244464L;
		public Long id;
        public String main;
        public String description;
        public String icon;
    }
    @Data
    public class Sys implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = -1783521743836320878L;
		public int type;
        public Long id;
        public Double message;
        public String country;
        public Long sunrise;
        public Long sunset;
    }
    @Data
    public class Coord implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = 5880402856606610508L;
		public Double lon;
        public Double lat;
    }
    @Data
    public class Main implements Serializable{ 
        /**
		 * 
		 */
		private static final long serialVersionUID = 8918417954032011437L;
		public Double temp;
        public Double pressure;
        public Double humidity;
        public Double temp_min;
        public Double temp_max;
    }
    @Data
    public class Wind{
        public Double speed;
        public Double deg;
    }
    @Data
    public class Clouds{
        public Double all;
    }
   
}