/**
 * Copyright(C) 2014, Trading and Service Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 * 
 */

package vn.co.taxinet.bo.impl;


import vn.co.taxinet.bean.TNUser;
import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.TaxiNetUsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.*;

@Transactional
public class AuthenticationBOImpl implements AuthenticationBO {
	private static final Logger logger = LogManager.getLogger(AuthenticationBOImpl.class);
	private TaxiNetUserDAO taxiNetUserDAO;
	/** 
	 * @param taxiNetUserDAO the taxiNetUserDAO to set
	 */
	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}
	@Transactional(readOnly=true)
	public boolean authenticate(TNUser tnUser) {
		final String begin = " BEGIN: params ({}, {}) ";
		logger.info(begin, tnUser.getUserName(), tnUser.getPassword());	
		System.out.println("Authenticate():" );
		try {
			TaxiNetUsers user = taxiNetUserDAO.select(tnUser.getUserName());
			if(user != null && user.getPassword().equals(tnUser.getPassword())) {
				return true;
			}
		}catch(Exception ex) {
			logger.error(ex);
		}
		logger.info( " END: params ({}, {} )", tnUser.getUserName(), tnUser.getPassword());
		return false;
	}
	
}
