package vn.co.taxinet.bean.rider;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Language;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.UserGroup;

/**
 * @author Ecchi controller for register.xhtml
 */
@ManagedBean(name = "riderRegisterController", eager = true)
@SessionScoped
public class RiderRegisterBean implements Serializable {
	private static final long serialVersionUID = 4300149713167673107L;
	// properties for register page
	private String emailAddress;
	private String password;
	private String userName;
	private String userSurName;
	private String mobileNo;
	private String language;
	private String creditCard;
	private String cvv;
	private Date expiredDate;
	private String zipCode;
	private Date currentTime;
	private AuthenticationBO authenticationBO;

	@ManagedProperty(value = "#{riderBO}")
	private RiderBO riderBO;

	// private TaxiNetUserDAO taxiNetUserDAO;
	/**
	 * init load
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			emailAddress = "";
			password = "";
			userName = "";
			userSurName = "";
			mobileNo = "";
			language = "en";
			creditCard = "";
			cvv = "";
			Calendar date = Calendar.getInstance();
			expiredDate = date.getTime();
			cvv = "";
			zipCode = "";
			currentTime = date.getTime();
		}
	}

	/**
	 * @author Ecchi. event for Register button if success, redirect to login
	 *         page
	 */
	public String doRegister() {
		// TODO validate information
		if (!emailAddress.matches(Constants.EMAIL_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid Email"));
			return null;
		} else if (!password.matches(Constants.PASSWORD_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid password"));
			return null;
		} else if (!userName.matches(Constants.USERNAME_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid name"));
			return null;
		} else if (!userSurName.matches(Constants.USERNAME_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid surname"));
			return null;
		} else if (!cvv.matches(Constants.CVV_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid CVV"));
			return null;
		} else if (!zipCode.matches(Constants.ZIPCODE_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid ZipCode"));
			return null;
		} else if (!mobileNo.matches(Constants.PHONENO_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid ZipCode"));
			return null;
		} else if (!creditCard.matches(Constants.VISACARD_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid Credit Card"));
			return null;
		} else if (expiredDate.before(currentTime)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid Expired Date"));
			return null;
		} else {
			// TODO set value to Rider/TaxiNetUsers/Payment Object
			Rider newRider = new Rider();

			newRider.setTaxinetusers(new TaxiNetUsers());
			newRider.getTaxinetusers().setEmail(emailAddress);
			newRider.getTaxinetusers().setPassword(password);
			newRider.getTaxinetusers().setUsername(emailAddress);
			newRider.getTaxinetusers().setPostalCode(zipCode);
			newRider.getTaxinetusers().setStatus(Constants.UserStatus.ACTIVE);
			newRider.getTaxinetusers().setCountry(new Country());
			// TODO change hardcode of country
			newRider.getTaxinetusers().getCountry().setCode("1");

			newRider.getTaxinetusers().setUsergroup(new UserGroup());
			newRider.getTaxinetusers().getUsergroup()
					.setGroupCode(Constants.GroupUser.RIDER);

			newRider.getTaxinetusers().setLanguage(new Language());
			newRider.getTaxinetusers().getLanguage()
					.setLanguageCode(Constants.Language.LANG_EN_CODE);

			// TODO set value CreditCard,CVV, ExpiredDate for Payment

			newRider.setMobileNo(mobileNo);
			newRider.setFirstName(userName);
			newRider.setLastName(userSurName);

			try {
				riderBO.register(newRider);
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession session = request.getSession();
				// set session attribute
				session.setAttribute("username", emailAddress);
				session.setAttribute("password", password);
				// redirect to login page
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/TN/faces/Login.xhtml");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (TNSException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	// getter & setter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurName() {
		return userSurName;
	}

	public void setUserSurName(String userSurName) {
		this.userSurName = userSurName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationBO getAuthenticationBO() {
		return authenticationBO;
	}

	public void setAuthenticationBO(AuthenticationBO authenticationBO) {
		this.authenticationBO = authenticationBO;
	}

	public RiderBO getRiderBO() {
		return riderBO;
	}

	public void setRiderBO(RiderBO riderBO) {
		this.riderBO = riderBO;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
}
