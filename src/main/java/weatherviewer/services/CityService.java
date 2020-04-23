package weatherviewer.services;

import java.util.ArrayList;

import weatherviewer.pojo.City;

public interface CityService {
	
	//getting all cities
	public ArrayList<City> getAllCity();
	
	//searching for a city by name
	public City searchCity(String cityname) 
			throws Exception;
	
	//searching new city on services
	public City searchNewCity(String cityname) 
			throws Exception;
}
