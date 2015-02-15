package vn.co.taxinet.bo.impl;

import java.util.ArrayList;
import java.util.List;

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
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.VehicleDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.TaxiNetUsers;

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
			driverDTO.setDriverId(listDriver.get(i).getDriverId());
			driverDTO.setLongitude(listDriver.get(i).getCurrentstatus()
					.getCurrentLatitude());
			driverDTO.setLatitude(listDriver.get(i).getCurrentstatus()
					.getCurrentLongtitude());
			driverDTO
					.setDriverName((listDriver.get(i).getFirstName() + " " + listDriver
							.get(i).getLastName()));
			driverDTO.setDriverImage(listDriver.get(i).getTaxinetusers()
					.getImage());
			driverDTO.setOpenKm(listDriver.get(i).getVehicle().getPricepanel()
					.getOpenKm());
			driverDTO.setOpenPrice(listDriver.get(i).getVehicle()
					.getPricepanel().getOpenPrice());
			driverDTO.setFirstKm(listDriver.get(i).getVehicle().getPricepanel()
					.getFirstKm());
			driverDTO.setFirstKmPrice(listDriver.get(i).getVehicle()
					.getPricepanel().getFirstKmprice());
			driverDTO.setNextKm(listDriver.get(i).getVehicle().getPricepanel()
					.getNextKm());
			driverDTO.setNextKmPrice(listDriver.get(i).getVehicle()
					.getPricepanel().getNextKmprice());
			driverDTO.setWaitingPrice(listDriver.get(i).getVehicle()
					.getPricepanel().getWaitingPrice());
			driverDTO.setReturnPrice(listDriver.get(i).getVehicle()
					.getPricepanel().getReturnTripPrice());
			driverDTO.setType(listDriver.get(i).getVehicle().getPricepanel()
					.getCarmodel().getCarType());
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
	public void persistVehicles(String carMaker, String carModel,
			String yearOfProduct, String inColor, String exColor, String plate,
			String countryCode, String cityCode, String userID) {

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
				throw new TNException(Constants.Message.FAIL);
			}

		} catch (NumberFormatException e) {
			throw new TNException(Constants.Message.NUMBER_FORMAT_EXCEPTION);
		}
	}

	@Transactional
	public DriverDTO login(String username, String password) throws TNException {
		// TODO Auto-generated method stub
		DriverDTO driverDTO = new DriverDTO();
		if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			throw new TNException("data it null");
		}
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.select(username);
		if (taxiNetUser == null) {
			throw new TNException("User not found");
		}
		if (taxiNetUser.getPassword().equals(password)
				&& taxiNetUser.getDriver() != null) {
			Driver driver = driverDAO.findDriverById(taxiNetUser.getUserId());
			driverDTO.setDriverId(driver.getDriverId());
			driverDTO.setLongitude(driver.getCurrentstatus()
					.getCurrentLatitude());
			driverDTO.setLatitude(driver.getCurrentstatus()
					.getCurrentLongtitude());
			driverDTO.setDriverName((driver.getFirstName() + " " + driver
					.getLastName()));
			driverDTO.setDriverImage(driver.getTaxinetusers().getImage());
			driverDTO
					.setOpenKm(driver.getVehicle().getPricepanel().getOpenKm());
			driverDTO.setOpenPrice(driver.getVehicle().getPricepanel()
					.getOpenPrice());
			driverDTO.setFirstKm(driver.getVehicle().getPricepanel()
					.getFirstKm());
			driverDTO.setFirstKmPrice(driver.getVehicle().getPricepanel()
					.getFirstKmprice());
			driverDTO
					.setNextKm(driver.getVehicle().getPricepanel().getNextKm());
			driverDTO.setNextKmPrice(driver.getVehicle().getPricepanel()
					.getNextKmprice());
			driverDTO.setWaitingPrice(driver.getVehicle().getPricepanel()
					.getWaitingPrice());
			driverDTO.setReturnPrice(driver.getVehicle().getPricepanel()
					.getReturnTripPrice());
			driverDTO.setType(driver.getVehicle().getPricepanel().getCarmodel()
					.getCarType());
		}
		return driverDTO;
	}
}
