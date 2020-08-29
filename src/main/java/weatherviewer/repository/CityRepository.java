package weatherviewer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import weatherviewer.entity.City;

public interface CityRepository extends CrudRepository<City,Long>{
	public List<City> findByCityname(String cityname);
}
