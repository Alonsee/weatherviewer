package weatherviewer.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import weatherviewer.exceptions.CityNotFoundException;
import weatherviewer.exceptions.CreateCityException;
import weatherviewer.pojo.City;
import weatherviewer.repository.CityRepository;

@Service
@RequestScope
public class CityServiceImpl implements CityService{
	
	CityRepository cityRepository;
	
	@Autowired
	public void setCityRepository(CityRepository cityRepository){
		this.cityRepository=cityRepository;
	}
	
	public CityServiceImpl() {}

	@Override
	public ArrayList<City> getAllCity() {
		return (ArrayList<City>) cityRepository.findAll();
	}
	
	@Override
	public City searchCity(String cityname) 
			throws IOException, CityNotFoundException, CreateCityException {
		//search city in saved list
		List<City> cities=cityRepository.findByCityname(cityname);
		if(cities.size()!=0) return cities.get(0);
		//try to create new city object if not found in saved list
		else return searchNewCity(cityname);
	}

	@Override
	public City searchNewCity(String cityname) 
			throws IOException, CityNotFoundException, CreateCityException {
		
		//create a gismeteo page document
		Document gismeteo_document=Jsoup.connect("https://www.gismeteo.ru/search/"+cityname).get();

		Elements gismeteo_cities=gismeteo_document.select(".catalog_block");
		
		//crate link to gismeteo
		String gismeteo_link="";
		if(gismeteo_cities.size()==1) gismeteo_link=gismeteo_cities.get(0).select(".catalog_item").get(0)
										.select("a").get(0).attr("href").split("weather")[1];
		if(gismeteo_cities.size()==2) gismeteo_link=gismeteo_cities.get(1).select(".catalog_item").get(0)
										.select("a").get(0).attr("href").split("weather")[1];
		if (gismeteo_link=="")throw new CityNotFoundException("Cyty not found on gismeteo");
		
		//create a yandex page document
		Document yandex_document=Jsoup.connect("https://yandex.ru/pogoda/search?request="+cityname).get();

		Elements yandex_cities=yandex_document.select(".place-list__item-name");
		
		//create link to yandex
		String yandex_link="";
		if(yandex_cities.size()>0)yandex_link=yandex_cities.get(0).select("a").get(0)
									.attr("href").split("/pogoda/")[1].split("\\?")[0];
		
		if (yandex_link=="")throw new CityNotFoundException("Cyty not found on yandex");
		
		//if the links are found create a new city
		return new City(yandex_cities.get(0).text().split(",")[0],yandex_link,gismeteo_link);

	}

}
