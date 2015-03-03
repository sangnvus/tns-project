package vn.co.taxinet.bo.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dao.CountryDAO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.LanguageDAO;
import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.UserGroupDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.TaxiNetUserDTO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

@Service
public class TaxiNetUserBOImpl implements TaxiNetUserBO {
	@Autowired
	TaxiNetUserDAO taxiNetUserDAO;
	@Autowired
	UserGroupDAO userGroupDAO;
	@Autowired
	LanguageDAO languageDAO;
	@Autowired
	CountryDAO countryDAO;
	@Autowired
	DriverDAO driverDAO;
	@Autowired
	RiderDAO riderDAO;

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	public void setRiderDAO(RiderDAO riderDAO) {
		this.riderDAO = riderDAO;
	}

	public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}
	@Transactional
	public String register(TaxiNetUserDTO user) throws TNException{
		if (user.getUserName() == null) {
			return "";
		}
		if (user.getPassword() == null) {
			return "";
		}
		if (user.getEmail() == null || !Utility.isEmail(user.getEmail())) {
			return "Invalid email";
		}
		if (user.getUserGroup() == null) {
			return "";
		}
		if (user.getLanguage() == null) {
			return "";
		}
		if (user.getCountryCode() == null) {
			return "";
		}
		TaxiNetUsers check = taxiNetUserDAO.select(user.getUserName());
		if (check.getUserId() != null) {
			return "username is already exist";
		}
		TaxiNetUsers taxiNetUser = new TaxiNetUsers();
		// check
		if(userGroupDAO.findById(user.getUserGroup())!=null){
			taxiNetUser.setUserGroup(userGroupDAO.findById(user.getUserGroup()));
		}else {
			return "user group not found";
		}
		if(languageDAO.findById(user.getLanguage())!= null){
			taxiNetUser.setLanguage(languageDAO.findById(user.getLanguage()));
		}else{
			return "language code not found";
		}
		if(countryDAO.findById(user.getCountryCode())!=null){
			taxiNetUser.setCountry(countryDAO.findById(user.getCountryCode()));
		}else{
			return "counntry code not found";
		}
		

		String uid = UUID.randomUUID().toString();
		taxiNetUser.setUserId(uid);
		taxiNetUser.setUsername(user.getUserName());
		taxiNetUser.setPassword(user.getPassword());
		taxiNetUser.setEmail(user.getEmail());
		taxiNetUser.setStatus("Active");
		taxiNetUser.setCreatedBy(uid);
		taxiNetUser.setCreatedDate(Utility.getCurrentDateTime());
		taxiNetUser.setLastModifiedBy(uid);
		taxiNetUser.setLastModifiedDate(Utility.getCurrentDateTime());
		taxiNetUserDAO.insert(taxiNetUser);
		if (taxiNetUser.getUserGroup().getGroupCode()
				.equalsIgnoreCase(Constants.GroupUser.DRIVER)) {
			Driver driver = new Driver();
			driver.setDriverId(taxiNetUser.getUserId());
			driver.setFirstName(user.getFirstName());
			driver.setLastName(user.getLastName());
			driver.setMobileNo(user.getPhone());
			driver.setTaxinetusers(taxiNetUser);
			
			driver.setCreatedBy(taxiNetUser.getUserId());
			driver.setCreatedDate(Utility.getCurrentDateTime());
			driver.setLastModifiedBy(taxiNetUser.getUserId());
			driver.setLastModifiedDate(Utility.getCurrentDateTime());
			driverDAO.insert(driver);
		} else if (taxiNetUser.getUserGroup().getGroupCode()
				.equalsIgnoreCase(Constants.GroupUser.RIDER)) {
			Rider rider = new Rider();
			rider.setRiderId(taxiNetUser.getUserId());
			rider.setFirstName(user.getFirstName());
			rider.setLastName(user.getLastName());
			rider.setMobileNo(user.getPhone());
			rider.setTaxinetusers(taxiNetUser);
			
			rider.setCreatedBy(taxiNetUser.getUserId());
			rider.setCreatedDate(Utility.getCurrentDateTime());
			rider.setLastModifiedBy(taxiNetUser.getUserId());
			rider.setLastModifiedDate(Utility.getCurrentDateTime());
			riderDAO.insert(rider);
		} else {
		}

		return uid;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.bo.TaxiNetUserBO#changePass(java.lang.String,
	 * java.lang.String)
	 */
	public String changePass(String userID, String newPassword) {
		String result = taxiNetUserDAO.changePassword(userID, newPassword);
		return result;
	}

}