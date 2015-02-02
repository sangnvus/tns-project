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
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

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
	
	private AuthenticationBO authenticationBO;
	
	@ManagedProperty(value="#{riderBO}")
	private RiderBO riderBO;
	// private TaxiNetUserDAO taxiNetUserDAO;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
		}
	}

	/**
	 * @author Ecchi. event for Register button if success, redirect to login
	 *         page
	 */
	public void doRegister() {
		// TODO validate information
		// TODO send information through webservice and receive user's role
		// TODO redirect to main.xhtml with user's role
		// TODO validate information
		// TODO send information through webservice and receive user's role
		// TODO redirect to main.xhtml with user's role
		int cvvNumber = 0;
		int zipCodeNumber = 0;
		int phoneNumber = 0;
		if (!emailAddress.matches(EMAIL_PATTERN)) {
			// thÃ´ng bÃ¡o lá»—i Ä‘áº¿n ngÆ°á»�i dÃ¹ng
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Email khÃ´ng há»£p lá»‡"));
		}
		// kiá»ƒm tra tÃ­nh há»£p lá»‡ cá»§a CVV, Sá»‘ Ä‘iá»‡n thoáº¡i vÃ  Zip Code
		try {
			// kiá»ƒm tra thÃ´ng tin CVV
			cvvNumber = Integer.parseInt(cvv);
		} catch (NumberFormatException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "CVV khÃ´ng há»£p lá»‡"));
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "CVV khÃ´ng há»£p lá»‡"));
		}

		try {
			// kiá»ƒm tra thÃ´ng tin zipCode
			zipCodeNumber = Integer.parseInt(zipCode);
		} catch (NumberFormatException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Zip Code khÃ´ng há»£p lá»‡"));
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Zip Code khÃ´ng há»£p lá»‡"));
		}

		try {
			// kiá»ƒm tra thÃ´ng tin cá»§a sá»‘ Ä‘iá»‡n thoáº¡i
			//phoneNumber = Integer.parseInt(mobileNo);
		} catch (NumberFormatException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Sá»‘ Ä‘iá»‡n thoáº¡i khÃ´ng há»£p lá»‡"));
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Sá»‘ Ä‘iá»‡n thoáº¡i khÃ´ng há»£p lá»‡"));
		}
		// set thÃ´ng tin cho Ä‘á»‘i tÆ°á»£ng Ä‘Æ°Æ¡c truyá»�n xuá»‘ng
		Rider newRider = new Rider();
		
//		TaxiNetUsers users = newRider.getTaxinetusers();
//		users.setEmail(emailAddress);
//		users.setPassword(password);
//		users.setUsername(emailAddress);
//		users.setPostalCode(zipCodeNumber);
		newRider.setTaxinetusers(new TaxiNetUsers());
		newRider.getTaxinetusers().setEmail(emailAddress);
		newRider.getTaxinetusers().setPassword(password);
		newRider.getTaxinetusers().setUsername(emailAddress);
		newRider.getTaxinetusers().setPostalCode(zipCode);
		newRider.setMobileNo(mobileNo);
		newRider.setFirstName(userName);
		newRider.setLastName(userSurName);
		// userGroup aka loáº¡i ngÆ°á»�i dÃ¹ng chÆ°a biáº¿t
		try {
			//riderBo.test(newRider);
			riderBO.test(new Rider());
			//riderBo.register(newRider);
			//náº¿u thÃ nh cÃ´ng thÃ¬ chuyá»ƒn qua trang login
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("username", emailAddress);
			session.setAttribute("password", password);
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// getter/setter
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
	
	
}
