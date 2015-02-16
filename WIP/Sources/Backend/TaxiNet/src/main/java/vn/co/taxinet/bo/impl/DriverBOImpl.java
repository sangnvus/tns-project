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
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dao.CarMakerDAO;
import vn.co.taxinet.dao.CarModelDAO;
import vn.co.taxinet.dao.CityNameDAO;
import vn.co.taxinet.dao.CountryDAO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.PricePanelDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.VehicleDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.PricePanel;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.Vehicle;
import vn.co.taxinet.utils.Utility;

@Service
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager
			.getLogger(DriverBOImpl.class);

	@Autowired
	private DriverDAO driverDAO;

	@Autowired
	private CarMakerDAO carMakerDAO;

	@Autowired
	private CarModelDAO carModelDAO;

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
	public Driver register(Driver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverDTO> getListDriver(String longitude, String latitude) {

		List<Driver> listDriver = driverDAO.getListDriver();
		List<DriverDTO> listDriverDTO = new ArrayList<DriverDTO>();
		for (int i = 0; i < listDriver.size(); i++) {
			DriverDTO driverDTO = new DriverDTO();
			listDriverDTO.add(driverDTO);
		}
		return listDriverDTO;
	}

	public String createTrip(String riderId, String driverId) {
		return driverDAO.createTrip(riderId, driverId);
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
	public List<CarModel> getCarModelList(String carMakerID) {
		logger.debug("---- Select Car Model ----");
		return carModelDAO.selectCarModelByCarMakerID(carMakerID);
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

	public void setCarModelDAO(CarModelDAO carModelDAO) {
		this.carModelDAO = carModelDAO;
	}

	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setCityNameDAO(CityNameDAO cityNameDAO) {
		this.cityNameDAO = cityNameDAO;
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

		// set value for new Object
		vehicles.setPlate(plate);
		vehicles.setExteriorColor(exColor);
		vehicles.setInteriorColor(inColor);
		vehicles.setProduceYear(yearOfProduct);
		vehicles.setCreatedBy(userID);
		vehicles.setLastModifiedBy(userID);
		vehicles.setCreatedDate(Utility.getCurrentDate());
		vehicles.setLastModifiedDate(Utility.getCurrentDate());
		// TODO hardcode Level
		vehicles.setLevel("4");
		// 1. select companyID from User ID
		TaxiNetUsers user = new TaxiNetUsers();
		user = taxiNetUserDAO.findByID(userID);
		if (user == null) {
			return null;
		} else {
			int companyId = user.getCompany().getCompanyId();
			// 2. select PricePanelID from car model ID and company ID
			PricePanel pricePanel = new PricePanel();
			pricePanel = pricePanelDAO.selectPricePanel(carModel,
					String.valueOf(companyId));
			if (pricePanel == null) {
				return null;
			} else {
				int pricePanelID = pricePanel.getPricePanelId();
				vehicles.setPricepanel(new PricePanel());
				vehicles.getPricepanel().setPricePanelId(pricePanelID);
				vehicleDAO.insert(vehicles);
				return "OKAY";
			}
		}
	}

	@Transactional
	public MessageDTO updateCurrentStatus(String driverId, String longitude,
			String latitude, String status) throws TNException {
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
				return driverDAO.updateCurrentStatus(driver.getDriverId(),
						_longitude, _latitude, _status);
			} else {
				return new MessageDTO(Constants.Message.FAIL);
			}

		} catch (NumberFormatException e) {
			throw new TNException(Constants.Message.NUMBER_FORMAT_EXCEPTION);
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
}
