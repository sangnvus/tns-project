package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.City;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;

public interface DriverBO {
	public Driver register(Driver obj);
	
	public List<CarMaker> selectCarMaker();
	
	public List<CarModel> selectCarModel(String carMakerID);
	
	public List<Country> selectCountry();
	
	public List<City> selectCity(String countryID);
	
	public void persistVehicle();
	
	public List<DriverDTO> listDriver();
	
	public String createTrip(String riderId, String driverId);
	
}
