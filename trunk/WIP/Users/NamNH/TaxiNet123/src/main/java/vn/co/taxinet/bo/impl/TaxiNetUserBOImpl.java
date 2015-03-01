package vn.co.taxinet.bo.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dao.CountryDAO;
import vn.co.taxinet.dao.LanguageDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dao.UserGroupDAO;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;
@Service
public class TaxiNetUserBOImpl implements TaxiNetUserBO{
	@Autowired
	TaxiNetUserDAO taxiNetUserDAO;
	@Autowired
	UserGroupDAO userGroupDAO;
	@Autowired
	LanguageDAO languageDAO;
	@Autowired
	CountryDAO countryDAO;
	
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

	public String register(String username, String password, String email,
			String userGroup, String languageCode, String countryCode) {
		if(username == null){
			return "";
		}
		if(password == null){
			return "";
		}
		if(email==null){
			return "";
		}
		if(userGroup==null){
			return "";
		}
		if(languageCode==null){
			return "";
		}
		if(countryCode==null){
			return "";
		}
		TaxiNetUsers check = taxiNetUserDAO.select(username);
		if(check.getUserId() != null){
			return "username is already exist";
		}
		TaxiNetUsers taxiNetUser = new TaxiNetUsers();
		String uid = UUID.randomUUID().toString();
		taxiNetUser.setUserId(uid);
		taxiNetUser.setUsername(username);
		taxiNetUser.setPassword(password);
		taxiNetUser.setEmail(email);
		taxiNetUser.setUserGroup(userGroupDAO.findById(userGroup));
		taxiNetUser.setLanguage(languageDAO.findById(languageCode));
		taxiNetUser.setCountry(countryDAO.findById(countryCode));
		taxiNetUser.setStatus("Active");
		taxiNetUser.setCreatedBy(uid);
		taxiNetUser.setCreatedDate(Utility.getCurrentDateTime());
		taxiNetUser.setLastModifiedBy(uid);
		taxiNetUser.setLastModifiedDate(Utility.getCurrentDateTime());
		taxiNetUserDAO.insert(taxiNetUser);
		return uid;
		
	}

	/* (non-Javadoc)
	 * @see vn.co.taxinet.bo.TaxiNetUserBO#changePass(java.lang.String, java.lang.String)
	 */
	public String changePass(String userID, String newPassword) {
		String result = taxiNetUserDAO.changePassword(userID, newPassword);
		return result;
	}
	public TaxiNetUsers findUserbyEmail(String email){
		return taxiNetUserDAO.findUserbyEmail(email);
	}
	

}
