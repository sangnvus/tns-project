package vn.co.taxinet.bean.rider;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.orm.Rider;

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
	private RiderBO riderBo;
	//private TaxiNetUserDAO taxiNetUserDAO;
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
	 * 
	 */
	public void doRegister() {
		// TODO validate information
		// TODO send information through webservice and receive user's role
		// TODO redirect to main.xhtml with user's role
		// TODO validate information
				// TODO send information through webservice and receive user's role
				// TODO redirect to main.xhtml with user's role
				int cvvNumber;
				int zipCodeNumber = 0;
				if (!emailAddress.matches(EMAIL_PATTERN)) {
					// thông báo lỗi đến người dùng
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Error", "Email không hợp lệ"));
				}
				try {
					//kiểm tra thông tin CVV và zip code
					cvvNumber = Integer.parseInt(cvv);
					zipCodeNumber = Integer.parseInt(zipCode);
				} catch (Exception ex) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Error", "CVV không hợp lệ"));
				}
				Rider newRider = new Rider();
				newRider.getTaxinetusers().setEmail(emailAddress);
				newRider.getTaxinetusers().setPassword(password);
				newRider.getTaxinetusers().setUsername(emailAddress);
				newRider.getTaxinetusers().setPostalCode(zipCodeNumber);
				
				Rider registerRider;
				try {
					registerRider = riderBo.register(newRider);
					if(registerRider != null ){
						//success và redirect qua trang khác
					} else {
						//thông báo lỗi
					}
					
				} catch (TNSException e) {
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

}
