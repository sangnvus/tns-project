package vn.co.taxinet.bean.driver;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi
 * @category add new driver's controller
 */
@ManagedBean(name = "addNewDriverBean", eager = true)
@SessionScoped
public class AddNewDriverBean implements Serializable {
	private static final long serialVersionUID = 1506603768350639642L;

	String UserID;
	String username;
	String password;

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

	Driver driver;

	@PostConstruct
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
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
