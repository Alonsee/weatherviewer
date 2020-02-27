package weatherviewer.pojo;

public class City {

	private int id;
	private String cityName;
	private String yandCityDescribe;
	private String goCityDescribe;
	
	public City(int id, String name, String yandDescribe,String goDescribe) {
		this.setId(id);
		cityName=name;
		yandCityDescribe=yandDescribe;
		goCityDescribe=goDescribe;
	}
	
	public City(String name, String yandDescribe,String goDescribe) {
		cityName=name;
		yandCityDescribe=yandDescribe;
		goCityDescribe=goDescribe;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityDescribeYa() {
		return yandCityDescribe;
	}

	public void setCityDescribeYa(String cityDescribe) {
		this.yandCityDescribe = cityDescribe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityDescribeGo() {
		return goCityDescribe;
	}

	public void setCityDescribeGo(String cityDescribeGo) {
		this.goCityDescribe = cityDescribeGo;
	}
	
	
}
