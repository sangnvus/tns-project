package vn.co.taxinet.bo.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.FunctionalException;
import vn.co.taxinet.common.exception.SystemException;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.UserGroupDAO;
import vn.co.taxinet.dto.RiderDTO;
import vn.co.taxinet.orm.Address;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

@Service
public class RiderBOImpl implements RiderBO {
	private final static String THIS = "RiderBOImpl";
	private static final Logger logger = LogManager 
			.getLogger(RiderBOImpl.class);
	// Refer to taxiNetUserDAO declared in ApplicationContext of Spring
	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;
	@Autowired
	private RiderDAO riderDAO;
	@Autowired
	private UserGroupDAO userGroupDAO;
	

	public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	/**
	 * @return the riderDAO
	 */
	public RiderDAO getRiderDAO() {
		return riderDAO;
	}

	/**
	 * @param riderDAO
	 *            the riderDAO to set
	 */
	public void setRiderDAO(RiderDAO riderDAO) {
		this.riderDAO = riderDAO;
	}

	/**
	 * @return the taxiNetUserDAO
	 */
	public TaxiNetUserDAO getTaxiNetUserDAO() {
		return taxiNetUserDAO;
	}

	/**
	 * @param taxiNetUserDAO
	 *            the taxiNetUserDAO to set
	 */
	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void register(Rider rider) throws TNSException {
		final String begin = " BEGIN: params ({0}, {1}) ";
		logger.info(begin, rider.getFirstName(), rider.getLastName());
		try {
			// Insert parent tables first, then insert child tables
			// 1. Insert user table
			// Check if user name exist in DB
			TaxiNetUsers user = rider.getTaxinetusers();
			TaxiNetUsers oldUser = taxiNetUserDAO.select(user.getUsername());
			if (oldUser.getUserId() != null) {
				if (user.getPassword().equals(oldUser.getPassword())) {
					throw new FunctionalException(THIS,
							"Rider User is existing",
							Constants.Errors.DUPLICATED_ERROR);
				}
			}
			UUID id = UUID.randomUUID();
			user.setUserId(id.toString());
			// User Name always is in lower case
			user.setUsername(user.getUsername().toLowerCase());
			user.setCreatedDate(Utility.getCurrentDateTime());
			user.setCreatedBy(user.getUsername());
			user.setLastModifiedDate(Utility.getCurrentDateTime());
			user.setLastModifiedBy(user.getUsername());
			user.setUsergroup(userGroupDAO.findById(Constants.GroupUser.RIDER));
			taxiNetUserDAO.insert(user);
			

			// 2. Insert Home Address if not null
			if (rider.getAddressByHomeAddressId() != null) {
				UUID homeAddressId = UUID.randomUUID();
				Address homeAddress = rider.getAddressByHomeAddressId();
				homeAddress.setAddressId(homeAddressId.toString());
				homeAddress.setCreatedDate(Utility.getCurrentDateTime());
				homeAddress.setLastModifiedDate(Utility.getCurrentDateTime());
				homeAddress.setCreatedBy(user.getUsername());
				homeAddress.setLastModifiedBy(user.getUsername());
				taxiNetUserDAO.insert(homeAddress);

			}

			// 3. Insert Office Address if not null ? fix ORM for this
			if (rider.getAddressByOfficeAddressId() != null) {
				UUID officeAddressId = UUID.randomUUID();
				Address offAddress = rider.getAddressByHomeAddressId();
				offAddress.setAddressId(officeAddressId.toString());
				offAddress.setCreatedDate(Utility.getCurrentDateTime());
				offAddress.setLastModifiedDate(Utility.getCurrentDateTime());
				offAddress.setCreatedBy(user.getUsername());
				offAddress.setLastModifiedBy(user.getUsername());
				taxiNetUserDAO.insert(offAddress);
			}

			// 4.Insert data into Payment table
			// TODO insert vào bảng payment

			// 5. Insert data into Rider table
			rider.setRiderId(id.toString());
			rider.setCreatedDate(Utility.getCurrentDateTime());
			rider.setLastModifiedDate(Utility.getCurrentDateTime());
			rider.setCreatedBy(user.getUsername());
			rider.setLastModifiedBy(user.getUsername());
			taxiNetUserDAO.insert(rider);
		} catch (TNSException tnex) {
			throw tnex;
		} catch (Throwable t) {
			throw new SystemException(THIS, t);
		}
		// 5. Return
		logger.info(" END: params ({0}, {1} )", rider.getFirstName(),
				rider.getLastName());
	}
	
	@Transactional
	public String register(String riderId, String firstName, String lastName, String mobileNo){
		if(riderId ==null){
			return "";
		}
		if(firstName==null){
			return "";
		}
		if(lastName == null){
			return "";
		}
		if(mobileNo==null){
			return "";
		}
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.findById(riderId);
		Rider rider = new Rider();
		rider.setTaxinetusers(taxiNetUser);
		rider.setFirstName(firstName);
		rider.setLastName(lastName);
		rider.setRiderId(riderId);
		rider.setMobileNo(mobileNo);
		rider.setCreatedBy(riderId);
		rider.setCreatedDate(Utility.getCurrentDateTime());
		rider.setLastModifiedBy(riderId);
		rider.setLastModifiedDate(Utility.getCurrentDateTime());
		try {
			riderDAO.insert(taxiNetUser.getRider());
			return riderId;
		} catch (Exception e) {
			return "";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.RiderBO#test(vn.co.taxinet.orm.Rider)
	 */
	@Transactional
	public void test(Rider rider) {
		if (rider == null) {
			System.out.println("Nothing");
		} else {
			System.out.println("Got it");
		}

	}

	@Transactional
	public Rider findByID(String uid) {
		Rider rider = riderDAO.select(uid.trim());
		if (rider != null) {
			return rider;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.RiderBO#updatePassword(java.lang.String,
	 * java.lang.String)
	 */
	@Transactional
	public void updatePassword(String uid, String password) {
		if (uid != null && password != null) {
			riderDAO.updatePassword(uid, password);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.RiderBO#updateProfile(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Transactional
	public void updateProfile(String uid, String surName, String name,
			String countryCode, String phoneNo, String languageCode,
			String zipCode) {
		// TODO Auto-generated method stub
		Rider rider = findByID(uid);
		String username = rider.getTaxinetusers().getUsername();
		Date date = Utility.getCurrentDate();
		riderDAO.updateProfile(uid, surName, name, countryCode, phoneNo,
				languageCode, zipCode, date, username);
	}

	public RiderDTO login(String username, String password) {
		// TODO Auto-generated method stub
		RiderDTO riderDTO = new RiderDTO();
		if(username == null){
			return riderDTO;
		}
		if(password == null){
			return riderDTO;
		}
		TaxiNetUsers taxiNetUser = taxiNetUserDAO.select(username);
		if (taxiNetUser.getUsername() != null) {
			if (taxiNetUser.getPassword().equals(password)
					&& taxiNetUser.getRider() != null && taxiNetUser != null) {
				Rider rider = taxiNetUser.getRider();
				riderDTO.setRiderId(rider.getRiderId());
				riderDTO.setRiderName(rider.getFirstName() + " "
						+ rider.getLastName());
				riderDTO.setMessage("Success");
				riderDTO.setImage(taxiNetUser.getImage());
				riderDTO.setEmail(taxiNetUser.getEmail());
				if (taxiNetUser.getRider().getAddressByHomeAddressId() != null) {
					riderDTO.setHomeAddress(taxiNetUser.getRider()
							.getAddressByHomeAddressId().getAddressDetail());
				}
				if (taxiNetUser.getRider().getAddressByOfficeAddressId() != null) {
					riderDTO.setOfficeAddress(taxiNetUser.getRider()
							.getAddressByOfficeAddressId().getAddressDetail());
				}

			} else {
				riderDTO.setMessage("invalid password");
			}
		}else{
			riderDTO.setMessage("invalid username");
		}
		return riderDTO;
	}

}
