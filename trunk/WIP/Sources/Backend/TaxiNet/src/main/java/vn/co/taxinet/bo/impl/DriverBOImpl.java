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
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.City;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.TaxiNetUsers;

@Service(value = "driverBO")
@Transactional
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager
			.getLogger(DriverBOImpl.class);
	@Autowired
	private DriverDAO driverDAO;
	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public Driver register(Driver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverDTO> listDriver() {

		List<Driver> listDriver = driverDAO.listDriver();
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
	@Transactional

	public String createTrip(String riderId, String driverId) {
		return driverDAO.createTrip(riderId, driverId);
	}
	@Transactional
	public DriverDTO login(String username, String password) {
		// TODO Auto-generated method stub
		DriverDTO driverDTO = new DriverDTO();
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.select(username);
		if(taxiNetUser.getPassword().equals(password)
		&& taxiNetUser.getDriver()!=null){
			Driver driver = driverDAO.findById(taxiNetUser.getUserId());
			driverDTO.setDriverId(driver.getDriverId());
			driverDTO.setLongitude(driver.getCurrentstatus().getCurrentLatitude());
			driverDTO.setLatitude(driver.getCurrentstatus().getCurrentLongtitude());
			driverDTO.setDriverName((driver.getFirstName()+ " " +driver.getLastName()));
			driverDTO.setDriverImage(driver.getTaxinetusers().getImage());
			driverDTO.setOpenKm(driver.getVehicle().getPricepanel().getOpenKm());
			driverDTO.setOpenPrice(driver.getVehicle().getPricepanel().getOpenPrice());
			driverDTO.setFirstKm(driver.getVehicle().getPricepanel().getFirstKm());
			driverDTO.setFirstKmPrice(driver.getVehicle().getPricepanel().getFirstKmprice());
			driverDTO.setNextKm(driver.getVehicle().getPricepanel().getNextKm());
			driverDTO.setNextKmPrice(driver.getVehicle().getPricepanel().getNextKmprice());
			driverDTO.setWaitingPrice(driver.getVehicle().getPricepanel().getWaitingPrice());
			driverDTO.setReturnPrice(driver.getVehicle().getPricepanel().getReturnTripPrice());
			driverDTO.setType(driver.getVehicle().getPricepanel().getCarmodel().getCarType());
		}
		return driverDTO;
	}
	

	public MessageDTO updateCurrentStatus(String driverId, String longitude,
			String latitude, String status) {
		try {
			double _longitude = Double.parseDouble(longitude);
			double _latitude = Double.parseDouble(latitude);
			String _status = status.toUpperCase();
			if (_status == null || _status.equalsIgnoreCase("")) {
				return new MessageDTO(Constants.Message.EMTPY_STATUS);
			}
			// check id of driver before update position
			Driver driver = driverDAO.findById(driverId);
			if (driver != null) {
				return driverDAO.updateCurrentStatus(driver.getDriverId(),
						_longitude, _latitude, _status);
			} else {
				return new MessageDTO(Constants.Message.FAIL);
			}

		} catch (NumberFormatException e) {
			return new MessageDTO(Constants.Message.NUMBER_FORMAT_EXCEPTION);
		}
	}

	public List<CarMaker> selectCarMaker() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CarModel> selectCarModel(String carMakerID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Country> selectCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<City> selectCity(String countryID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void persistVehicle() {
		// TODO Auto-generated method stub

	}

}
