package vn.co.taxinet.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.City;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.utils.Const;

@Service(value = "driverBO")
@Transactional
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager
			.getLogger(DriverBOImpl.class);
	private DriverDAO driverDAO;

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
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

	public String createTrip(String riderId, String driverId) {
		return driverDAO.createTrip(riderId, driverId);
	}

	public MessageDTO updateCurrentStatus(String driverId, String longitude,
			String latitude, String status) {
		try {
			double _longitude = Double.parseDouble(longitude);
			double _latitude = Double.parseDouble(latitude);
			String _status = status.toUpperCase();
			if (_status == null || _status.equalsIgnoreCase("")) {
				return new MessageDTO(Const.EMTPY_STATUS);
			}
			// check id of driver before update position
			Driver driver = driverDAO.findById(driverId);
			if (driver != null) {
				return driverDAO.updateCurrentStatus(driver.getDriverId(),
						_longitude, _latitude, _status);
			} else {
				return new MessageDTO(Const.FAIL);
			}

		} catch (NumberFormatException e) {
			return new MessageDTO(Const.NUMBER_FORMAT_EXCEPTION);
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
