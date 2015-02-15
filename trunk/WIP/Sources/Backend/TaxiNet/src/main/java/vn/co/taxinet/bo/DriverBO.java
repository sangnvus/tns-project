package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;

public interface DriverBO {
	public Driver register(Driver obj);

	public List<DriverDTO> listDriver();

	public String createTrip(String riderId, String driverId);

	/**
	 * @author Ecchi
	 * @return list of car maker
	 */
	public List<CarMaker> getCarMakerList();

	/**
	 * @author Ecchi
	 * @param carMakerID
	 * @return car model of car maker
	 */
	public List<CarModel> getCarModelList(String carMakerID);

	/**
	 * @author Ecchi
	 * @return list of country
	 */
	public List<Country> getCountryList();

	/**
	 * @author Ecchi
	 * @return list of city name
	 */
	public List<CityName> getCityNameList(String countryCode);

	/**
	 * @author Ecchi
	 * @param carMaker
	 * @param carModel
	 * @param yearOfProduct
	 * @param inColor
	 * @param exColor
	 * @param plate
	 * @param countryCode
	 * @param cityCode
	 * @param userID
	 */
	public void persistVehicles(String carMaker, String carModel,
			String yearOfProduct, String inColor, String exColor, String plate,
			String countryCode, String cityCode,String userID);

	/**
	 * @param driverId
	 * @param longitude
	 * @param latitude
	 * @param status
	 * @return
	 */
	public MessageDTO updateCurrentStatus(String driverId, String longitude,
			String latitude, String status);

}
