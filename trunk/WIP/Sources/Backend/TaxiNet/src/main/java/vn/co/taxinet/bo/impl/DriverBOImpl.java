package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.orm.Driver;

@Service(value="driverBO")
@Transactional
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager.getLogger(DriverBOImpl.class);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Driver register(Driver driver) {
		// TODO Auto-generated method stub
		return null;
	}
}
