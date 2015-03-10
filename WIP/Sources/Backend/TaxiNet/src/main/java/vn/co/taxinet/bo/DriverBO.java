package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarType;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Vehicle;

/**
 * 
 * @author Ecchi
 *
 */
public interface DriverBO {
	
	public void register(Driver driver) throws TNSException;

	public String register(String driverId, String firstName, String lastName,
			String mobileNo);

	public MessageDTO changePassword(String id, String oldpassword, String newpassword) throws TNException;
	
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
	public List<CarType> getCarModelList(String carMakerID);

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
			String latitude, String status, String location) throws TNException;

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

	/**
	 * @author Ecchi
	 * @param companyID
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<DriverDTO> findDriverByCompanyID(String companyID, int pageIndex,
			int pageSize);
	
	/**
	 * @author Ecchi
	 * @param companyID
	 * @return
	 */
	public List<Driver> countAllDriverByCompanyID(String companyID);
	
	/**
	 * @author Ecchi
	 * @category dummy functions
	 * @param vehicleID
	 * @return vehicle
	 */
	public Vehicle getVehicleFromVehicleID(String vehicleID);
	
	
	/**
	 * @author Ecchi
	 * function to list all of driver
	 * @param companyID
	 * @return
	 */
	public List<DriverDTO> getAllDriverOfCompany(String companyID, int pageIndex, int pageSize);
	
	/**
	 * @author Ecchi
	 * function to count the number of company's driver
	 * @param companyID
	 * @return number of the company's driver
	 */
	public int countAllDriverOfCompany(String companyID);

	public Driver findDriverByUsername(String Username) throws TNSException;

	public void update(Driver updateDriver) throws TNSException;
	
	/**
	 * @author Ecchi
	 * @category Add New Driver
	 * @param driver
	 * @return result
	 */
	public String addNewDriver(Driver driver);
	
	
	/**
	 * @author Ecchi
	 * @param driverDTO
	 * @return
	 */
	public String editDriverInfo(DriverDTO driverDTO);
	
	/**
	 * @author Ecchi
	 * @param driverID
	 * @return
	 */
	public String removeDriver(String driverID);
	
	public List<CarType> listAllCarType();
}



