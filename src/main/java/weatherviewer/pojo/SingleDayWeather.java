package weatherviewer.pojo;

import java.util.GregorianCalendar;

//One day weather display class

public class SingleDayWeather {
	//day of observation
	private GregorianCalendar date;
	//selected city
	private City city;
	//4 values for morning, day, evening and night
	private String temperature[] = new String[4];
	private String humidity[] = new String[4];
	private String pressure[] = new String[4];
	private String wind[] = new String[4];
	private String cloudiness[] = new String[4];
	private String weatherprovider;
	
	public SingleDayWeather(City city, GregorianCalendar date, String weatherprovider) {
		this.city = city;
		this.date = date;
		this.weatherprovider = weatherprovider;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String[] getTemperature() {
		return temperature;
	}

	public void setTemperature(String[] temp) {
		this.temperature = temp;
	}

	public String[] getHumidity() {
		return humidity;
	}

	public void setHumidity(String[] humidity) {
		this.humidity = humidity;
	}

	public String[] getPressure() {
		return pressure;
	}

	public void setPressure(String[] pressure) {
		this.pressure = pressure;
	}

	public String[] getWind() {
		return wind;
	}

	public void setWind(String[] wind) {
		this.wind = wind;
	}

	public String[] getCloudiness() {
		return cloudiness;
	}

	public void setCloudiness(String[] cloudiness) {
		this.cloudiness = cloudiness;
	}

	public String getWeatherprovider() {
		return weatherprovider;
	}

	public void setWeatherprovider(String weatherprovider) {
		this.weatherprovider = weatherprovider;
	}

}
