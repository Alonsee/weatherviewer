package weatherviewer.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String cityname;
	private String yandcitydescribe;
	private String gocitydescribe;
	
	public City() {}
	
	public City(String name, String yandDescribe,String goDescribe) {
		cityname=name;
		yandcitydescribe=yandDescribe;
		gocitydescribe=goDescribe;
	}

	public String getCityName() {
		return cityname;
	}

	public void setCityName(String cityName) {
		this.cityname = cityName;
	}

	public String getCityDescribeYa() {
		return yandcitydescribe;
	}

	public void setCityDescribeYa(String cityDescribe) {
		this.yandcitydescribe = cityDescribe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityDescribeGo() {
		return gocitydescribe;
	}

	public void setCityDescribeGo(String cityDescribeGo) {
		this.gocitydescribe = cityDescribeGo;
	}
		
}
