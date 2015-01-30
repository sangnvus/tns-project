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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bean.TNUser;
import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi
 *
 */
@Transactional
public class AuthenticationBOImpl implements AuthenticationBO {
	private static final Logger logger = LogManager
			.getLogger(AuthenticationBOImpl.class);
	private TaxiNetUserDAO taxiNetUserDAO;

	/**
	 * @param taxiNetUserDAO
	 *            the taxiNetUserDAO to set
	 */
	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.co.taxinet.bo.AuthenticationBO#authenticate(vn.co.taxinet.bean.TNUser)
	 */
	@Transactional(readOnly = true)
	public boolean authenticate(TNUser tnUser) {
		final String begin = " BEGIN: params ({}, {}) ";
		logger.info(begin, tnUser.getUserName(), tnUser.getPassword());
		System.out.println("Authenticate():");
		try {
			TaxiNetUsers user = taxiNetUserDAO.select(tnUser.getUserName());
			if (user != null && user.getPassword().equals(tnUser.getPassword())) {
				return true;
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		logger.info(" END: params ({}, {} )", tnUser.getUserName(),
				tnUser.getPassword());
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.co.taxinet.bo.AuthenticationBO#riderRegistration(vn.co.taxinet.orm
	 * .Rider)
	 */
	public boolean userRegistration(TaxiNetUsers user) {
		boolean registered = false;
		try {
			Rider rider = new Rider();
			// set các thông tin của rider bằng thông tin của user
			if (taxiNetUserDAO.registerTaxiNetUser(user) != false
					&& taxiNetUserDAO.registerRider(rider) != false) {
				registered = true;
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		return registered;
	}

}
