package weatherviewer.services;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import weatherviewer.exceptions.CityNotFoundException;
import weatherviewer.exceptions.CreateCityException;
import weatherviewer.pojo.City;

@Service
public class CityServiceImpl implements CityService{
	
	private static ArrayList<City> allcity=new ArrayList<City>();
	private static int citycount=1;
	
	//creating a list of some displayed cities
	public CityServiceImpl(){
		allcity.add( new City(citycount++,"Екатеринбург","yekaterinburg","-yekaterinburg-4517/"));
		allcity.add(new City(citycount++,"Верхняя Пышма","verhnyaya-pyshma","-verkhnyaya-pyshma-12758/"));
		allcity.add(new City(citycount++,"Сочи","sochi","-sochi-5233/"));
	}

	@Override
	public ArrayList<City> getAllCity() {
		return allcity;
	}
	
	@Override
	public City searchCity(String cityname) 
			throws IOException, CityNotFoundException, CreateCityException {
		//search city in saved list
		for (City c:allcity) {
			if (c.getCityName()==cityname) return c;
		}
		//try to create new city object if not found in saved list
		return searchNewCity(cityname);
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
