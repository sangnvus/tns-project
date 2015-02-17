package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;

public interface DriverBO {

	public Driver register(Driver driver);
	public String register(String driverId, String firstName, String lastName, String mobileNo);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public List<DriverDTO> getNearListDriver(String longitude, String latitude);

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
	public String persistVehicles(String carMaker, String carModel,
			String yearOfProduct, String inColor, String exColor, String plate,
			String countryCode, String cityCode, String userID);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param driverId
	 * @param longitude
	 * @param latitude
	 * @param status
	 * @return
	 * @throws TNException
	 */
	public MessageDTO updateCurrentStatus(String driverId, String longitude,
			String latitude, String status) throws TNException;

	/**
	 * @author Hieu-Gie
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws TNException
	 */
	public DriverDTO login(String username, String password) throws TNException;

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param phone
	 * @param email
	 * @param password
	 * @return
	 * @throws TNException
	 */
	public MessageDTO updateDriver(String id, String firstname,
			String lastname, String phone, String email, String password)
			throws TNException;

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @param regId
	 * @return
	 * @throws TNException
	 */
	public MessageDTO updateRegId(String id, String regId) throws TNException;

}
