package weatherviewer.services;


import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import weatherviewer.exceptions.CityNotFoundException;
import weatherviewer.exceptions.CreateCityException;
import weatherviewer.pojo.City;
import weatherviewer.pojo.SingleDayWeather;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	CityService CityServiceImpl;
	
	@Autowired
	public void setCityServiceImpl(CityServiceImpl CityServiceImpl) {
		this.CityServiceImpl=CityServiceImpl;
	}
	
	@Override
	public SingleDayWeather getCurrentWeather(String selectedcity,String weatherProvider) throws IOException, CityNotFoundException, CreateCityException 
		{
		//search for the selected city
		City city=CityServiceImpl.searchCity(selectedcity);
		
		//create the weather object 
		SingleDayWeather currentWeather=new SingleDayWeather(city, new GregorianCalendar(),weatherProvider);
		
		if(weatherProvider.equalsIgnoreCase("yandex")) {	
				//creating a document from url
				Document document=Jsoup.connect("https://yandex.ru/pogoda/"+city.getCityDescribeYa()+"/details").get();
	
				//parsing a document selecting a value for morning, day, evening, night
				Elements temperatures = document.select(".weather-table__temp");
				currentWeather.setTemperature(new String[] {temperatures.get(0).text(),
															temperatures.get(1).text(),
															temperatures.get(2).text(),
															temperatures.get(3).text()});
				
				Elements cloudiness=document.select(".weather-table__body-cell_type_condition");
				currentWeather.setCloudiness(new String[] {cloudiness.get(0).text(),
															cloudiness.get(1).text(),
															cloudiness.get(2).text(),
															cloudiness.get(3).text()});
				
				Elements airpressure=document.select(".weather-table__body-cell_type_air-pressure");
				currentWeather.setPressure(new String[] {airpressure.get(0).text(),
														airpressure.get(1).text(),
														airpressure.get(2).text(),
														airpressure.get(3).text()});
				
				Elements humidity=document.select(".weather-table__body-cell_type_humidity");
				currentWeather.setHumidity(new String[] {humidity.get(0).text(),
														humidity.get(1).text(),
														humidity.get(2).text(),
														humidity.get(3).text()});
				
				Elements wind=document.select(".weather-table__wind .wind-speed");
				Elements wind_direction=document.select(".weather-table__wind-direction");
				currentWeather.setWind(new String[] {wind.get(0).text()+" / "+wind_direction.get(0).childNode(0).attr("title").split(":")[1],
														wind.get(1).text()+" / "+wind_direction.get(1).childNode(0).attr("title").split(":")[1],
														wind.get(2).text()+" / "+wind_direction.get(2).childNode(0).attr("title").split(":")[1],
														wind.get(3).text()+" / "+wind_direction.get(3).childNode(0).attr("title").split(":")[1]});
				
			return currentWeather;
		}
		
		if(weatherProvider.equalsIgnoreCase("gismeteo")) {
				Document document=Jsoup.connect("https://www.gismeteo.ru/weather"+city.getCityDescribeGo()).get();
				
				//parsing a document selecting a value for morning, day, evening, night
				Elements temperatures = document.select(".chart__temperature .unit_temperature_c");
				currentWeather.setTemperature(new String[] {temperatures.get(1).text(),
															temperatures.get(3).text(),
															temperatures.get(5).text(),
															temperatures.get(7).text()});
					
				
				Elements cloudiness=document.select(".w_icon .tooltip");
				currentWeather.setCloudiness(new String[] {cloudiness.get(1).childNode(0).attr("data-text"),
															cloudiness.get(3).childNode(0).attr("data-text"),
															cloudiness.get(5).childNode(0).attr("data-text"),
															cloudiness.get(7).childNode(0).attr("data-text")});
				
				Elements airpressure=document.select(".widget__row_pressure .unit_pressure_mm_hg_atm");
				currentWeather.setPressure(new String[] {airpressure.get(1).text(),
														airpressure.get(3).text(),
														airpressure.get(5).text(),
														airpressure.get(7).text()});
				
				Elements humidity=document.select(".widget__row_humidity .w-humidity");
				currentWeather.setHumidity(new String[] {humidity.get(1).text(),
														humidity.get(3).text(),
														humidity.get(5).text(),
														humidity.get(7).text()});
				
				Elements wind=document.select(".widget__row_wind .unit_wind_m_s");
				Elements wind_direction=document.select(".widget__row_wind .w_wind__direction");
				currentWeather.setWind(new String[] {wind.get(1).text()+" / "+wind_direction.get(1).text(),
														wind.get(3).text()+" / "+wind_direction.get(3).text(),
														wind.get(5).text()+" / "+wind_direction.get(5).text(),
														wind.get(7).text()+" / "+wind_direction.get(7).text()});
				
			return currentWeather;
		}
		return null;
	}

	@Override
	public SingleDayWeather getTomorrowWeather(String selectedcity,String weatherProvider) throws IOException, CityNotFoundException, CreateCityException
		{
		City city=CityServiceImpl.searchCity(selectedcity);
		GregorianCalendar currentDate=new GregorianCalendar();
		currentDate.roll(Calendar.DAY_OF_YEAR,1);
		SingleDayWeather currentWeather=new SingleDayWeather(city, new GregorianCalendar(),weatherProvider);
		if(weatherProvider.equalsIgnoreCase("yandex")) {
				Document document=Jsoup.connect("https://yandex.ru/pogoda/"+city.getCityDescribeYa()+"/details").get();
	
				Elements temperatures = document.select(".weather-table__temp");
				currentWeather.setTemperature(new String[] {temperatures.get(4).text(),
															temperatures.get(5).text(),
															temperatures.get(6).text(),
															temperatures.get(7).text()});
				
				Elements cloudiness=document.select(".weather-table__body-cell_type_condition");
				currentWeather.setCloudiness(new String[] {cloudiness.get(4).text(),
															cloudiness.get(5).text(),
															cloudiness.get(6).text(),
															cloudiness.get(7).text()});
				
				Elements airpressure=document.select(".weather-table__body-cell_type_air-pressure");
				currentWeather.setPressure(new String[] {airpressure.get(4).text(),
														airpressure.get(5).text(),
														airpressure.get(6).text(),
														airpressure.get(7).text()});
				
				Elements humidity=document.select(".weather-table__body-cell_type_humidity");
				currentWeather.setHumidity(new String[] {humidity.get(4).text(),
														humidity.get(5).text(),
														humidity.get(6).text(),
														humidity.get(7).text()});
				
				Elements wind=document.select(".weather-table__wind .wind-speed");
				Elements wind_direction=document.select(".weather-table__wind-direction");
				currentWeather.setWind(new String[] {wind.get(4).text()+" / "+wind_direction.get(4).childNode(0).attr("title").split(":")[1],
														wind.get(5).text()+" / "+wind_direction.get(5).childNode(0).attr("title").split(":")[1],
														wind.get(6).text()+" / "+wind_direction.get(6).childNode(0).attr("title").split(":")[1],
														wind.get(7).text()+" / "+wind_direction.get(7).childNode(0).attr("title").split(":")[1]});
				
			return currentWeather;
		}
		
		if(weatherProvider.equalsIgnoreCase("gismeteo")) {
				Document document=Jsoup.connect("https://www.gismeteo.ru/weather"+city.getCityDescribeGo()+"/tomorrow/").get();
	
				Elements temperatures = document.select(".chart__temperature .unit_temperature_c");
				currentWeather.setTemperature(new String[] {temperatures.get(1).text(),
															temperatures.get(3).text(),
															temperatures.get(5).text(),
															temperatures.get(7).text()});
					
				
				Elements cloudiness=document.select(".w_icon .tooltip");
				currentWeather.setCloudiness(new String[] {cloudiness.get(1).childNode(0).attr("data-text"),
															cloudiness.get(3).childNode(0).attr("data-text"),
															cloudiness.get(5).childNode(0).attr("data-text"),
															cloudiness.get(7).childNode(0).attr("data-text")});
				
				Elements airpressure=document.select(".widget__row_pressure .unit_pressure_mm_hg_atm");
				currentWeather.setPressure(new String[] {airpressure.get(1).text(),
														airpressure.get(3).text(),
														airpressure.get(5).text(),
														airpressure.get(7).text()});
				
				Elements humidity=document.select(".widget__row_humidity .w-humidity");
				currentWeather.setHumidity(new String[] {humidity.get(1).text(),
														humidity.get(3).text(),
														humidity.get(5).text(),
														humidity.get(7).text()});
				
				Elements wind=document.select(".widget__row_wind .unit_wind_m_s");
				Elements wind_direction=document.select(".widget__row_wind .w_wind__direction");
				currentWeather.setWind(new String[] {wind.get(1).text()+" / "+wind_direction.get(1).text(),
														wind.get(3).text()+" / "+wind_direction.get(3).text(),
														wind.get(5).text()+" / "+wind_direction.get(5).text(),
														wind.get(7).text()+" / "+wind_direction.get(7).text()});	
			
			return currentWeather;
		}
	return null;
}
}

