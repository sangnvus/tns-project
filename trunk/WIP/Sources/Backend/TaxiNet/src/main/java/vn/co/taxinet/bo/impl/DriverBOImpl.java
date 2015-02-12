package vn.co.taxinet.bo.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bean.TripBean;
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.orm.Driver;

@Service(value="driverBO")
@Transactional
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager.getLogger(DriverBOImpl.class);
	private DriverDAO driverDAO;

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Driver register(Driver driver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Driver> listDriver() {
		
		return driverDAO.listDriver();
	}
	public String createTrip(String riderId, String driverId){
		return driverDAO.createTrip(riderId, driverId);
	}
	
}
