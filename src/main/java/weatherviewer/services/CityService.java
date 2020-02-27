package weatherviewer.services;

import java.io.IOException;
import java.util.ArrayList;

import weatherviewer.exceptions.CityNotFoundException;
import weatherviewer.exceptions.CreateCityException;
import weatherviewer.pojo.City;

public interface CityService {
	
	//getting all cities
	public ArrayList<City> getAllCity();
	
	//searching for a city by name
	public City searchCity(String cityname) throws IOException, CityNotFoundException, CreateCityException;
	
	//searching new city on services
	public City searchNewCity(String cityname) throws IOException, CityNotFoundException, CreateCityException;
}
