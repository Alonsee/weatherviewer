package weatherviewer.services;

import java.io.IOException;

import weatherviewer.exceptions.CityNotFoundException;
import weatherviewer.exceptions.CreateCityException;
import weatherviewer.pojo.SingleDayWeather;

public interface WeatherService {

	//request weather for current day
	public SingleDayWeather getCurrentWeather(String city, String weatherProvider) 
			throws IOException, CityNotFoundException, CreateCityException;
	
	//request weather for tomorrow
	public SingleDayWeather getTomorrowWeather(String city, String weatherProvider)
			throws IOException, CityNotFoundException, CreateCityException;
}
