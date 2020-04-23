package weatherviewer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import weatherviewer.pojo.SingleDayWeather;
import weatherviewer.services.*;

@Controller
public class IndexController {
	
	private WeatherService WeatherServiceImpl;
	
	private CityService CityServiceImpl;
	
	@Autowired
	public void setWeatherServiceImpl(WeatherService WeatherServiceImpl) {
		this.WeatherServiceImpl = WeatherServiceImpl;
	}
	
	@Autowired
	public void setCityServiceImpl(CityService CityServiceImpl) {
		this.CityServiceImpl = CityServiceImpl;
	}

	@GetMapping({"/", "/index"})
	public String searchweather(@RequestParam(name = "cityname", required = false, defaultValue = "") String selectedCity,
								@RequestParam(name = "service", required = false, defaultValue = "") String selectedProvider,
								@CookieValue(value = "city", defaultValue = "") String cookieCity,
		    					@CookieValue(value = "weatherprovider", defaultValue = "yandex") String cookieProvider,
								HttpServletResponse response, 
								Model model)
								throws Exception{ 	
		
		//add list of cities for the selection field
		model.addAttribute("allCity", CityServiceImpl.getAllCity());
		String provider;
		String city;

		//check the choice of city or service
		//if not selected check the cookies
		//if there is no cookies select default values from the cookies
		if (selectedCity.equals("") && cookieCity.equals("")) {
			return "index.html";
		}
		else if (selectedCity.equals("") && !cookieCity.equals("")) {
			city = cookieCity.replace("|", " ");
		}
		else {
			city = selectedCity;
		}

		if (selectedProvider.equals("")) {
			provider = cookieProvider;
		}
		else {
			provider = selectedProvider;
		}

		//if provider not found throws exception
		if (!(provider.equalsIgnoreCase("yandex") | provider.equalsIgnoreCase("gismeteo"))) {
			throw new Exception("Service not found");
		}
		
		//displays the current weather when selected city or service are changed
		SingleDayWeather currentWeather = WeatherServiceImpl.getCurrentWeather(city, provider);
		model.addAttribute("currentweather", currentWeather );	
		SingleDayWeather tomorrowWeather = WeatherServiceImpl.getTomorrowWeather(city, provider);
		model.addAttribute("tomorrowweather", tomorrowWeather );
				
		//save cookie with selected weather
		//spaces are replased, because
		//it is an invalid character in the Cookie value
		Cookie cityCookie = new Cookie("city", city.replace(" ","|"));
		Cookie providerCookie = new Cookie("weatherprovider", provider);
		cityCookie.setMaxAge(180000);
		providerCookie.setMaxAge(180000);
		response.addCookie(cityCookie);
		response.addCookie(providerCookie);
		return "index.html";		
    }


		
}
