package weatherviewer.services;

import weatherviewer.pojo.SingleDayWeather;

public interface WeatherService {

	//request weather for current day
	public SingleDayWeather getCurrentWeather(String city, String weatherProvider) 
			throws Exception;
	
	//request weather for tomorrow
	public SingleDayWeather getTomorrowWeather(String city, String weatherProvider)
			throws Exception;
}
