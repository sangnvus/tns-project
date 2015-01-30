package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.orm.Rider;

@Transactional
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager.getLogger(AuthenticationBOImpl.class);

	public Rider register(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
