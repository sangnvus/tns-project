package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.DriverBO;

@Transactional
public class DriverBOImpl implements DriverBO {
	private static final Logger logger = LogManager.getLogger(AuthenticationBOImpl.class);
}
