package vn.co.taxinet.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.Constants.DriverStatus;
import vn.co.taxinet.common.Constants.Message;
import vn.co.taxinet.common.exception.FunctionalException;
import vn.co.taxinet.common.exception.SystemException;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dao.CarMakerDAO;
import vn.co.taxinet.dao.CarTypeDAO;
import vn.co.taxinet.dao.CityNameDAO;
import vn.co.taxinet.dao.CountryDAO;
import vn.co.taxinet.dao.CurrentStatusDAO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.PricePanelDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.VehicleDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarType;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Company;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.CurrentStatus;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.PricePanel;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.Vehicle;
import vn.co.taxinet.utils.Utility;

@Service
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager
			.getLogger(DriverBOImpl.class);
	private final static String THIS = "DriverBOImpl";
	@Autowired
	private DriverDAO driverDAO;

	@Autowired
	private CarMakerDAO carMakerDAO;

	@Autowired
	private CarTypeDAO carTypeDAO;

	@Autowired
	private CountryDAO countryDAO;

	@Autowired
	private CityNameDAO cityNameDAO;

	@Autowired
	private VehicleDAO vehicleDAO;

	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;

	@Autowired
	private PricePanelDAO pricePanelDAO;

	@Autowired
	private CurrentStatusDAO currentStatusDAO;

	public void setCurrentStatusDAO(CurrentStatusDAO currentStatusDAO) {
		this.currentStatusDAO = currentStatusDAO;
	}

	public void setPricePanelDAO(PricePanelDAO pricePanelDAO) {
		this.pricePanelDAO = pricePanelDAO;
	}

	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	public void setVehicleDAO(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void register(Driver Driver) throws TNSException {
		final String begin = " BEGIN: params ({0}, {1}) ";
		logger.info(begin, Driver.getFirstName(), Driver.getLastName());
		try {
			// Insert parent tables first, then insert child tables
			// 1. Insert user table
			// Check if user name exist in DB
			TaxiNetUsers user = Driver.getTaxinetusers();
			TaxiNetUsers oldUser = taxiNetUserDAO.select(user.getEmail());
			if (oldUser != null) {
				if (user.getPassword().equals(oldUser.getPassword())) {
					throw new FunctionalException(THIS,
							"Driver User is existing",
							Constants.Errors.DUPLICATED_ERROR);
				}
			}
			UUID id = UUID.randomUUID();
			user.setUserId(id.toString());
			// User Name always is in lower case
			user.setUsername(user.getUsername().toLowerCase());
			user.setCreatedDate(Utility.getCurrentDateTime());
			user.setCreatedBy(user.getUsername());
			user.setLastModifiedDate(Utility.getCurrentDateTime());
			user.setLastModifiedBy(user.getUsername());
			taxiNetUserDAO.insert(user);

			// 5. Insert data into Driver table
			Driver.setDriverId(id.toString());
			Driver.setCreatedDate(Utility.getCurrentDateTime());
			Driver.setLastModifiedDate(Utility.getCurrentDateTime());
			Driver.setCreatedBy(user.getUsername());
			Driver.setLastModifiedBy(user.getUsername());
			driverDAO.insert(Driver);
		} catch (TNSException tnex) {
			throw tnex;
		} catch (Throwable t) {
			throw new SystemException(THIS, t);
		}
		logger.info(" END: params ({0}, {1} )", Driver.getFirstName(),
				Driver.getLastName());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverDTO> getNearListDriver(String longitude, String latitude) {

		List<Driver> listDriver = driverDAO.getNearListDriver();
		List<DriverDTO> listDriverDTO = new ArrayList<DriverDTO>();
		for (int i = 0; i < listDriver.size(); i++) {
			Driver driver = listDriver.get(i);
			// parse log lat
			double log1, lat1, log2, lat2;
			try {
				log1 = Double.parseDouble(longitude);
				lat1 = Double.parseDouble(latitude);
				log2 = driver.getCurrentstatus().getCurrentLongtitude();
				lat2 = driver.getCurrentstatus().getCurrentLatitude();
			} catch (NumberFormatException e) {
				return null;
			}

			// calculate distance
			double theta = log1 - log2;
			double dist = Math.sin(Utility.deg2rad(lat1))
					* Math.sin(Utility.deg2rad(lat2))
					+ Math.cos(Utility.deg2rad(lat1))
					* Math.cos(Utility.deg2rad(lat2))
					* Math.cos(Utility.deg2rad(theta));

			dist = Math.acos(dist);
			dist = Utility.rad2deg(dist);
			dist = dist * 60 * 1.1515 * 1.609344;
			System.out.println(dist);
			if (dist < 5) {

				DriverDTO driverDTO = new DriverDTO();
				driverDTO.setId(driver.getDriverId());
				driverDTO.setLongitude(log2);
				driverDTO.setLatitude(lat2);
				driverDTO.setFirstName(driver.getFirstName());
				driverDTO.setLastName(driver.getLastName());
				driverDTO.setImage(driver.getTaxinetusers().getImage());
				listDriverDTO.add(driverDTO);
			}
		}
		return listDriverDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getCarMakerList()
	 */
	@Transactional
	public List<CarMaker> getCarMakerList() {
		logger.debug("---- Select Car Maker ----");
		return carMakerDAO.selectAllCarMaker();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getCarModelList(java.lang.String)
	 */
	@Transactional
	public List<CarType> getCarModelList(String carMakerID) {
		logger.debug("---- Select Car Model ----");
		return carTypeDAO.getAllCarTypeByCarMaker(carMakerID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getCountryList()
	 */
	@Transactional
	public List<Country> getCountryList() {
		logger.debug("---- Select Country ----");
		return countryDAO.selectAllCountry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getCityNameList()
	 */
	@Transactional
	public List<CityName> getCityNameList(String countryCode) {
		logger.debug("---- Select City Name ----");
		return cityNameDAO.selectAllCityName(countryCode);
	}

	public void setCarMakerDAO(CarMakerDAO carMakerDAO) {
		this.carMakerDAO = carMakerDAO;
	}

	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setCityNameDAO(CityNameDAO cityNameDAO) {
		this.cityNameDAO = cityNameDAO;
	}

	public void setCarTypeDAO(CarTypeDAO carTypeDAO) {
		this.carTypeDAO = carTypeDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#persistVehicles(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public String persistVehicles(String carMaker, String carModel,
			String yearOfProduct, String inColor, String exColor, String plate,
			String countryCode, String cityCode, String userID) {
		Vehicle vehicles = new Vehicle();
		// get RANDOM UUID for vehicles ID
		UUID id = UUID.randomUUID();
		// check ID exist or not
		Vehicle oldVehicle = vehicleDAO.getVehicleFromID(id.toString());
		if (oldVehicle != null) {
			id = UUID.randomUUID();
		}
		// set value for new Object
		vehicles.setPlate(plate);
		vehicles.setExteriorColor(exColor);
		vehicles.setInteriorColor(inColor);
		vehicles.setProduceYear(yearOfProduct);
		vehicles.setCreatedBy(userID);
		vehicles.setLastModifiedBy(userID);
		vehicles.setCreatedDate(Utility.getCurrentDate());
		vehicles.setLastModifiedDate(Utility.getCurrentDate());
		vehicles.setCartype(new CarType());
		vehicles.getCartype().setCarTypeId(Integer.valueOf(carModel));
		// TODO hardcode Level
		vehicles.setLevel("4");
		// 1. select companyID from User ID
		TaxiNetUsers user = new TaxiNetUsers();
		user = taxiNetUserDAO.findByID(userID);
		if (user == null) {
			return null;
		} else {
			int companyId = user.getCompany().getCompanyId();
			vehicles.setCompany(new Company());
			vehicles.getCompany().setCompanyId(companyId);
			// 2. select PricePanelID from car model ID and company ID
			PricePanel pricePanel = new PricePanel();
			pricePanel = pricePanelDAO.selectPricePanel(carModel,
					String.valueOf(companyId));
			if (pricePanel == null) {
				return null;
			} else {
				String pricePanelID = pricePanel.getPricePanelId();
				vehicles.setPricepanel(new PricePanel());
				vehicles.getPricepanel().setPricePanelId(pricePanelID);
				vehicleDAO.insert(vehicles);
				return "OKAY";
			}
		}
	}

	@Transactional
	public DriverDTO login(String username, String password) throws TNException {
		// TODO Auto-generated method stub
		DriverDTO driverDTO = new DriverDTO();
		if (username == null || password == null
				|| username.equalsIgnoreCase("")
				|| password.equalsIgnoreCase("")) {
			throw new TNException("data it null");
		}
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.select(username);
		if (taxiNetUser == null) {
			return null;
		}
		if (taxiNetUser.getPassword().equals(password)) {
			Driver driver = driverDAO.findDriverById(taxiNetUser.getUserId());
			if (driver == null) {
				return null;
			}
			driverDTO.setId(driver.getDriverId());
			driverDTO.setImage(driver.getTaxinetusers().getImage());
			driverDTO.setFirstName(driver.getFirstName());
			driverDTO.setLastName(driver.getLastName());
			driverDTO.setEmail(driver.getTaxinetusers().getEmail());
			driverDTO.setPhoneNumber(driver.getMobileNo());
			driverDTO.setPassword(driver.getTaxinetusers().getPassword());
		}
		return driverDTO;
	}

	public MessageDTO updateDriver(String id, String firstname,
			String lastname, String phone, String email, String password)
			throws TNException {
		if (id == null || firstname == null || lastname == null
				|| phone == null || email == null || password == null
				|| id.equalsIgnoreCase("") || firstname.equalsIgnoreCase("")
				|| lastname.equalsIgnoreCase("") || phone.equalsIgnoreCase("")
				|| email.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			throw new TNException("data it null");
		}
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.findByID(id);
		if (taxiNetUser == null) {
			return null;
		}
		if (taxiNetUser.getDriver() == null) {
			return null;
		}

		taxiNetUser.setEmail(email);
		taxiNetUser.setPassword(password);
		taxiNetUser.getDriver().setFirstName(firstname);
		taxiNetUser.getDriver().setLastName(lastname);
		taxiNetUser.getDriver().setMobileNo(phone);
		taxiNetUser.getDriver().setLastModifiedBy(taxiNetUser.getUserId());
		taxiNetUser.setLastModifiedBy(taxiNetUser.getUserId());
		taxiNetUser.setLastModifiedDate(Utility.getCurrentDate());
		taxiNetUser.getDriver().setLastModifiedDate(Utility.getCurrentDate());
		taxiNetUserDAO.update(taxiNetUser);

		return new MessageDTO(Constants.Message.SUCCESS);
	}

	public MessageDTO updateRegId(String id, String regId) throws TNException {
		if (id == null || regId == null || regId.equalsIgnoreCase("")
				|| id.equalsIgnoreCase("")) {
			throw new TNException("data it null");
		}

		Driver driver = driverDAO.findDriverById(id);
		if (driver == null) {
			return null;
		}
		driver.setRegId(regId);
		driverDAO.update(driver);

		return new MessageDTO(Constants.Message.SUCCESS);
	}

	public String register(String driverId, String firstName, String lastName,
			String mobileNo) {
		// TODO Auto-generated method stub
		if (driverId == null) {
			return "";
		}
		if (firstName == null) {
			return "";
		}
		if (lastName == null) {
			return "";
		}
		if (mobileNo == null) {
			return "";
		}
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.findById(driverId);
		Driver driver = new Driver();
		driver.setTaxinetusers(taxiNetUser);
		driver.setFirstName(firstName);
		driver.setLastName(lastName);
		driver.setDriverId(driverId);
		driver.setMobileNo(mobileNo);
		driver.setCreatedBy(driverId);
		driver.setCreatedDate(Utility.getCurrentDateTime());
		driver.setLastModifiedBy(driverId);
		driver.setLastModifiedDate(Utility.getCurrentDateTime());

		driverDAO.insert(driver);
		// set current status
		CurrentStatus currentStatus = new CurrentStatus();
		currentStatus.setDriver(driver);
		currentStatus.setDriverId(driver.getDriverId());
		currentStatus.setCurrentStatus(Constants.DriverStatus.NEW);
		currentStatusDAO.insert(currentStatus);
		return driverId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#findDriverByCompanyID(java.lang.String,
	 * int, int)
	 */
	@Transactional
	public List<DriverDTO> findDriverByCompanyID(String companyID,
			int pageIndex, int pageSize) {
		return driverDAO.findDriverByCompanyID(companyID, pageIndex, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.co.taxinet.bo.DriverBO#countAllDriverByCompanyID(java.lang.String)
	 */
	@Transactional
	public List<Driver> countAllDriverByCompanyID(String companyID) {
		return driverDAO.countDriverByCompanyID(companyID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#updateCurrentStatus(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public MessageDTO updateCurrentStatus(String driverId, String longitude,
			String latitude, String status, String location) throws TNException {
		try {
			double _longitude = Double.parseDouble(longitude);
			double _latitude = Double.parseDouble(latitude);
			String _status = status.toUpperCase();
			if (_status == null || _status.equalsIgnoreCase("")) {
				throw new TNException(Constants.Message.EMTPY_STATUS);
			}
			// check id of driver before update position
			Driver driver = driverDAO.findDriverById(driverId);
			if (driver != null) {
				CurrentStatus currentStatus = driver.getCurrentstatus();
				currentStatus.setCurrentLatitude(_latitude);
				currentStatus.setCurrentLocation(location);
				currentStatus.setCurrentLongtitude(_longitude);
				currentStatus.setCurrentStatus(_status);
				currentStatusDAO.update(currentStatus);
				return currentStatusDAO.updateCurrentStatus(
						driver.getDriverId(), _longitude, _latitude, _status);
			} else {
				return new MessageDTO(Constants.Message.FAIL);
			}

		} catch (NumberFormatException e) {
			throw new TNException(Constants.Message.NUMBER_FORMAT_EXCEPTION);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getVehicleFromVehicleID(java.lang.String)
	 */
	public Vehicle getVehicleFromVehicleID(String vehicleID) {
		return vehicleDAO.getVehicleFromID(vehicleID);
	}

	public Driver findDriverByUsername(String Username) throws TNSException {
		try {
			// Check if user name not exist in DB
			TaxiNetUsers user = taxiNetUserDAO.select(Username);
			if (user == null) {
				if (user.getPassword().equals(user.getPassword())) {
					throw new FunctionalException(THIS,
							"Driver User is not existing",
							Constants.Errors.DUPLICATED_ERROR);
				}
			}
			Driver driver = driverDAO.findDriverById(user.getUserId());
			if (driver == null) {
				throw new FunctionalException(THIS,
						"Driver User is not existing",
						Constants.Errors.DUPLICATED_ERROR);

			}
			return driver;
		} catch (TNSException tnex) {
			throw tnex;
		} catch (Throwable t) {
			throw new SystemException(THIS, t);
		}
	}

	public void update(Driver updateDriver) throws TNSException {
		// TODO Auto-generated method stub
		try {
			// Insert parent tables first, then insert child tables
			// 1. Insert user table
			// Check if user name not exist in DB
			TaxiNetUsers updateUser = updateDriver.getTaxinetusers();
			TaxiNetUsers oldUser = taxiNetUserDAO.select(updateUser
					.getUsername());
			if (oldUser == null) {
				if (updateUser.getPassword().equals(oldUser.getPassword())) {
					throw new FunctionalException(THIS,
							"Driver User is not existing",
							Constants.Errors.DUPLICATED_ERROR);
				}
			}
			// User Name always is in lower case
			oldUser.setEmail(updateUser.getEmail());
			oldUser.setLastModifiedDate(Utility.getCurrentDateTime());
			oldUser.setLastModifiedBy(updateUser.getUsername());
			taxiNetUserDAO.update(oldUser);
			Driver oldDriver = driverDAO.findDriverById(oldUser.getUserId());
			// 5. Update data into Driver table
			oldDriver.setFirstName(updateDriver.getFirstName());
			oldDriver.setLastName(updateDriver.getLastName());
			oldDriver.setLastModifiedDate(Utility.getCurrentDateTime());
			oldDriver.setLastModifiedBy(updateUser.getUsername());
			driverDAO.update(oldDriver);
		} catch (TNSException tnex) {
			throw tnex;
		} catch (Throwable t) {
			throw new SystemException(THIS, t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getAllDriverOfCompany(java.lang.String,
	 * int, int)
	 */
	@Transactional
	public List<DriverDTO> getAllDriverOfCompany(String companyID,
			int pageIndex, int pageSize) {
		return driverDAO.getAllDriver(companyID, pageIndex, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#countAllDriverOfCompany(java.lang.String)
	 */
	@Transactional
	public int countAllDriverOfCompany(String companyID) {
		return driverDAO.countAllDriver(companyID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#addNewDriver(vn.co.taxinet.orm.Driver)
	 */
	@Transactional
	public String addNewDriver(Driver driver) {
		if (driver != null) {
			UUID id = UUID.randomUUID();
			Driver oldDriver = driverDAO.findDriverById(String.valueOf(id));
			if (oldDriver == null) {
				String email = driver.getTaxinetusers().getEmail();
				List<TaxiNetUsers> userList = taxiNetUserDAO
						.listUsersByEmail(email);
				if (userList.size() > 0) {
					return Constants.FAILED;
				} else {
					driver.getTaxinetusers().setUserId(id.toString());
					driver.setDriverId(id.toString());
					taxiNetUserDAO.insert(driver.getTaxinetusers());
					driverDAO.insert(driver);
					return Constants.SUCCESS;
				}
			} else {
				id = UUID.randomUUID();
				String email = driver.getTaxinetusers().getEmail();
				List<TaxiNetUsers> userList = taxiNetUserDAO
						.listUsersByEmail(email);
				if (userList.size() > 0) {
					return Constants.FAILED;
				} else {
					driver.getTaxinetusers().setUserId(id.toString());
					driver.setDriverId(id.toString());
					taxiNetUserDAO.insert(driver.getTaxinetusers());
					driverDAO.insert(driver);
					return Constants.SUCCESS;
				}
			}
		} else {
			return Constants.FAILED;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.co.taxinet.bo.DriverBO#editDriverInfo(vn.co.taxinet.dto.DriverDTO)
	 */
	@Transactional
	public String editDriverInfo(DriverDTO driverDTO) {
		return driverDAO.editDriverInfo(driverDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#removeDriver(java.lang.String)
	 */
	@Transactional
	public String removeDriver(String driverID) {
		return driverDAO.removeDriver(driverID);
	}

	@Transactional
	public MessageDTO changePassword(String id, String oldpassword,
			String newpassword) throws TNException {
		if (id == null || id.equalsIgnoreCase("") || oldpassword == null
				|| oldpassword.equalsIgnoreCase("") || newpassword == null
				|| newpassword.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}

		if (newpassword.length() < 6) {
			throw new TNException(Message.PASSWORD_LENGTH);
		}

		Driver driver = driverDAO.findDriverById(id);
		if (driver == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}
		if (driver.getTaxinetusers().getPassword().equals(oldpassword)) {
			driver.getTaxinetusers().setPassword(newpassword);
			driverDAO.update(driver);
			return new MessageDTO(Message.SUCCESS);
		}

		return new MessageDTO(Message.PASSWORD_ERROR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#listAllCarType()
	 */
	@Transactional
	public List<CarType> listAllCarType() {
		return carTypeDAO.getAllCarType();
	}

	public MessageDTO logout(String id) throws TNException {
		if (id == null || id.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		Driver driver = driverDAO.findDriverById(id);
		if (driver == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}
		CurrentStatus currentStatus = driver.getCurrentstatus();
		currentStatus.setCurrentStatus(DriverStatus.NOT_AVAIABLE);
		currentStatusDAO.update(currentStatus);
		return new MessageDTO(Message.SUCCESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#countPricePanel(int, java.lang.String)
	 */
	@Transactional
	public int countPricePanel(int companyID, String currentDateTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.DriverBO#getAllPricePanel(int, java.lang.String,
	 * int, int)
	 */
	@Transactional
	public List<PricePanel> getAllPricePanel(int companyID,
			String currentDateTime, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
