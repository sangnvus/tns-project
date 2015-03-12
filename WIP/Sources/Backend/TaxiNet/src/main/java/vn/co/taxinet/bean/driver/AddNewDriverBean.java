package vn.co.taxinet.bean.driver;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.orm.Company;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Language;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.UserGroup;
import vn.co.taxinet.utils.Utility;

/**
 * @author Ecchi
 * @category add new driver's controller
 */
@ManagedBean(name = "addNewDriverBean", eager = true)
@SessionScoped
public class AddNewDriverBean implements Serializable {
	private static final long serialVersionUID = 1506603768350639642L;
	// session value
	String UserID;
	String username;
	String password;
	String companyID;
	// UI value
	public String newUsername;
	public String newCountry;
	public String newEmail;
	public String newPassword;
	public String newConfirmPassword;
	public String newLangCode;
	public String newMobileNo;
	public String newFirstname;
	public String newLastname;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@ManagedProperty(value = "#{taxiNetUserBO}")
	private TaxiNetUserBO taxiNetUserBO;

	private List<Country> countryList;

	private List<Language> languageList;

	Driver driver;

	@PostConstruct
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			try {
				// TODO refresh all field
				refreshField();
				// TODO get value from session
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession session = request.getSession();
				UserID = session.getAttribute("UserID").toString();
				username = session.getAttribute("Username").toString();
				password = session.getAttribute("Password").toString();
				// TODO get user informations from UserID
				TaxiNetUsers user = taxiNetUserBO.getUserInfo(UserID);
				companyID = String.valueOf(user.getCompany().getCompanyId());
				// TODO get default value for drop down list
				languageList = new ArrayList<Language>();
				countryList = new ArrayList<Country>();
				languageList = taxiNetUserBO.listAllLanguage();
				countryList = taxiNetUserBO.listAllCountry();
			} catch (Exception ex) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/Login.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * action for add new driver button
	 * 
	 * @return null
	 */
	public String addNewDriver() {
		// TODO validate values from view
		if (!(newEmail).matches(Constants.EMAIL_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"Invalid Email Address"));
			return null;
		} else if (!(newUsername).matches(Constants.USERNAME_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"Invalid Username"));
			return null;
		} else if (!(newPassword).matches(Constants.PASSWORD_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"Invalid Password"));
			return null;
		} else if (!(newPassword).equals(newConfirmPassword)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"Password and Confirm Password are different"));
			return null;
		} else if (!(newMobileNo).matches(Constants.PHONENO_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"Invalid Phone numnber"));
			return null;
		} else {
			// TODO add new driver to DB
			Driver driver = new Driver();

			// set value for driver class
			driver.setFirstName(newFirstname);
			driver.setLastName(newLastname);
			driver.setMobileNo(newMobileNo);
			driver.setCreatedBy(username);
			driver.setLastModifiedBy(username);

			driver.setCreatedDate(Utility.getCurrentDateTime());
			driver.setLastModifiedDate(Utility.getCurrentDateTime());

			// set value for taxinetuser class
			driver.setTaxinetusers(new TaxiNetUsers());
			driver.getTaxinetusers().setUsername(newUsername);
			driver.getTaxinetusers().setPassword(newPassword);
			driver.getTaxinetusers().setEmail(newEmail);
			driver.getTaxinetusers().setUserGroup(new UserGroup());
			driver.getTaxinetusers().getUsergroup().setGroupCode("2");
			driver.getTaxinetusers().setCompany(new Company());
			driver.getTaxinetusers().getCompany()
					.setCompanyId(Integer.parseInt(companyID));
			driver.getTaxinetusers().setLanguage(new Language());
			driver.getTaxinetusers().setStatus(Constants.DriverStatus.AVAIABLE);
			driver.getTaxinetusers().getLanguage().setLanguageCode(newLangCode);
			driver.getTaxinetusers().setCountry(new Country());
			driver.getTaxinetusers().getCountry().setCode(newCountry);
			driver.getTaxinetusers().setCreatedBy(username);
			driver.getTaxinetusers().setLastModifiedBy(username);
			driver.getTaxinetusers().setCreatedDate(
					Utility.getCurrentDateTime());
			driver.getTaxinetusers().setLastModifiedDate(
					Utility.getCurrentDateTime());

			// add new driver to DB by addNewDriver function
			String result = driverBO.addNewDriver(driver);
			if ((result).equalsIgnoreCase(Constants.SUCCESS)) {
				// TODO push message to UI
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Successful", "Add new driver successful"));
				// refresh all value
				refreshField();
				return null;
			} else {
				// TODO push message to UI
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"Duplicated  Email"));
				return null;
			}
		}
	}

	/**
	 * refresh input field
	 */
	public void refreshField() {
		newConfirmPassword = "";
		newPassword = "";
		newFirstname = "";
		newLastname = "";
		newEmail = "";
		newMobileNo = "";
		newLangCode = "";
		newUsername = "";
		newCountry = "";
	}

	// getter&setter
	public String getCompanyID() {
		return companyID;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<Language> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

	public void setTaxiNetUserBO(TaxiNetUserBO taxiNetUserBO) {
		this.taxiNetUserBO = taxiNetUserBO;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public String getNewCountry() {
		return newCountry;
	}

	public void setNewCountry(String newCountry) {
		this.newCountry = newCountry;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}

	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
	}

	public String getNewLangCode() {
		return newLangCode;
	}

	public void setNewLangCode(String newLangCode) {
		this.newLangCode = newLangCode;
	}

	public String getNewMobileNo() {
		return newMobileNo;
	}

	public void setNewMobileNo(String newMobileNo) {
		this.newMobileNo = newMobileNo;
	}

	public String getNewFirstname() {
		return newFirstname;
	}

	public void setNewFirstname(String newFirstname) {
		this.newFirstname = newFirstname;
	}

	public String getNewLastname() {
		return newLastname;
	}

	public void setNewLastname(String newLastname) {
		this.newLastname = newLastname;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}
}
