package vn.co.taxinet.bean.rider;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.component.password.Password;

import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.orm.Rider;

@ManagedBean(name = "riderProfileController", eager = true)
@SessionScoped
public class RiderProfileBean implements Serializable {
	private static final long serialVersionUID = -6838938203076847082L;

	private String UserID;
	private String password;
	private String Email;
	private Rider userAccount;

	@ManagedProperty(value = "#{riderBO}")
	private RiderBO riderBO;

	private String surName;
	private String name;
	private String rePassword;
	private String country;
	private String zipCode;
	private String phoneHeader;
	private String phoneNo;
	private String languageCode;
	private String invatationCode;

	/**
	 * init load when load page
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			userAccount = new Rider();
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			UserID = session.getAttribute("UserID").toString();
			Email = session.getAttribute("Email").toString();
			password = session.getAttribute("Password").toString();
			// If UserID doesnt exist, redirect to login page
			if (null == UserID || null == Email) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/Login.xhtml");
				} catch (IOException e) {
					e.getMessage();
					e.printStackTrace();
				}
			} else {
				// if UserID exist, find Rider by UserID
				userAccount = riderBO.findByID(UserID);
				// if cant find Rider. redirect to login page
				if (userAccount == null) {
					try {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/TN/faces/Login.xhtml");
					} catch (IOException e) {
						e.getMessage();
						e.printStackTrace();
					}
				} else {
					surName = userAccount.getFirstName();
					name = userAccount.getLastName();
					languageCode = userAccount.getTaxinetusers().getLanguage()
							.getLanguageCode();
					country = userAccount.getTaxinetusers().getCountry()
							.getName();
					zipCode = userAccount.getTaxinetusers().getPostalCode();
					setRePassword(password);
					phoneNo = userAccount.getMobileNo();
				}
			}

		}
	}

	/**
	 * action change password
	 */
	public void changePassButton() {
		if (rePassword == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Password is null"));
		} else {
			if (rePassword.length() >= Constants.MAX_PASSWORD_LENGTH
					&& rePassword.length() <= Constants.MIN_PASSWORD_LENGTH) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Error",
								"Password must be from 6 to 10 characters"));
			} else {
				riderBO.updatePassword(UserID, rePassword);
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("Password", rePassword);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Success",
								"Update password succesfully"));
			}
		}
	}

	/**
	 * update profile button
	 * 
	 * @return
	 */
	public String updateProfileButton() {
		// check null
		if (surName == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Surname must not null"));
			return null;
		} else if (name == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Name must not null"));
			return null;
		} else if (languageCode == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Language must not null"));
			return null;
		} else if (zipCode == null
				&& !zipCode.matches(Constants.ZIPCODE_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Error",
							"Zip Code is a number with 5 digits"));
			return null;
		} else if (phoneNo == null
				&& !phoneNo.matches(Constants.PHONENO_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Phone number invalid"));
			return null;
		} else {
			// update value in DB
			riderBO.updateProfile(UserID, surName, name, country, phoneNo,
					languageCode, zipCode);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", "Update succesfully"));
			return null;
		}

	}

	public RiderBO getRiderBO() {
		return riderBO;
	}

	public void setRiderBO(RiderBO riderBO) {
		this.riderBO = riderBO;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneHeader() {
		return phoneHeader;
	}

	public void setPhoneHeader(String phoneHeader) {
		this.phoneHeader = phoneHeader;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getInvatationCode() {
		return invatationCode;
	}

	public void setInvatationCode(String invatationCode) {
		this.invatationCode = invatationCode;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Rider getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Rider userAccount) {
		this.userAccount = userAccount;
	}

}
