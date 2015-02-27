package vn.co.taxinet.bean.driver;

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
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Document;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Vehicle;
import vn.co.taxinet.orm.Language;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.UserGroup;
import vn.co.taxinet.utils.Utility;

/**
 * @author namnh_000
 *
 */
@ManagedBean(name = "driverRegisterController", eager = true)
@SessionScoped
public class DriverRegisterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6293900223741966108L;

	public String FirstName;
	public String LastName;
	public String PhoneHeader;
	public String PhoneNumber;
	public String MobieNo;
	public String Email;
	public String PassWord;
	public String CompanyName;
	public String AddressNumber;
	public String Street;
	public String District;
	public String City;
	public String Zipcode;
	public String Language;
	public Document DocumentByDriverLisense;
	public String Vehicle;
	public Document DocumentByDriveExperience;
	private Date CurrentTime;
	private AuthenticationBO authenticationBO;

	public Document getDocumentByDriverLisense() {
		return DocumentByDriverLisense;
	}

	public void setDocumentByDriverLisense(Document documentByDriverLisense) {
		DocumentByDriverLisense = documentByDriverLisense;
	}

	public String getVehicle() {
		return Vehicle;
	}

	public void setVehicle(String vehicle) {
		Vehicle = vehicle;
	}

	public Document getDocumentByDriveExperience() {
		return DocumentByDriveExperience;
	}

	public void setDocumentByDriveExperience(Document documentByDriveExperience) {
		DocumentByDriveExperience = documentByDriveExperience;
	}

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			FirstName = "";
			LastName = "";
			PhoneHeader = "";
			PhoneNumber = "";
			MobieNo = "";
			Email = "";
			PassWord = "";
			CompanyName = "";
			AddressNumber = "";
			Street = "";
			District = "";
			City = "";
			Zipcode = "";
			Language = "en";
			/*
			 * Vehicle=null; DocumentByDriverLisense=null;
			 * DocumentByDriveExperience=null;
			 */
			Calendar date = Calendar.getInstance();
			CurrentTime = date.getTime();
		}
	}

	public String getMobieNo() {
		return MobieNo;
	}

	public void setMobieNo(String mobieNo) {
		MobieNo = mobieNo;
	}

	public String doRegister1() throws TNSException {
		return "RegisterDriver2.xhtml";
	}

	public String doRegister2() throws TNSException, IOException {
		// TODO validate information
		System.out.print(2);
		MobieNo = PhoneHeader + PhoneNumber;
		if (!Email.matches(Constants.EMAIL_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid Email"));
			return null;
		} else if (!Zipcode.matches(Constants.ZIPCODE_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid ZipCode"));
			return null;
		} else if (!MobieNo.matches(Constants.PHONENO_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "Invalid PhoneNumber"));
			return null;
		} else {

			Driver newDriver = new Driver();

			newDriver.setFirstName(getFirstName());
			newDriver.setLastName(getLastName());
			newDriver.setMobileNo(getPhoneNumber());
			newDriver.setTaxinetusers(new TaxiNetUsers());
			newDriver.getTaxinetusers().setEmail(Email);
			newDriver.getTaxinetusers().setPassword(PassWord);
			newDriver.getTaxinetusers().setUsername(Email);
			newDriver.getTaxinetusers().setPostalCode(Zipcode);
			// TODO change hardcode of country
			newDriver.getTaxinetusers().setCountry(new Country());
			newDriver.getTaxinetusers().getCountry().setCode("VN");

			newDriver.getTaxinetusers().setUsergroup(new UserGroup());
			newDriver.getTaxinetusers().getUsergroup()
					.setGroupCode(Constants.GroupUser.DRIVER);
			newDriver.getTaxinetusers().setStatus(Constants.UserStatus.ACTIVE);
			newDriver.getTaxinetusers().setLanguage(new Language());
			newDriver.getTaxinetusers().getLanguage()
					.setLanguageCode(Constants.Language.LANG_EN_CODE);

			newDriver.setMobileNo(MobieNo);
			newDriver.setFirstName(FirstName);
			newDriver.setLastName(LastName);
			driverBO.register(newDriver);
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			// set session attribute
			session.setAttribute("username", Email);
			session.setAttribute("password", PassWord);
			// redirect to login page
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/TN/faces/xhtml/driver/ContactInfo.xhtml");
			return null;
		}
	}

	public String getPhoneHeader() {
		return PhoneHeader;
	}

	public void setPhoneHeader(String phoneHeader) {
		PhoneHeader = phoneHeader;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getAddressNumber() {
		return AddressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		AddressNumber = addressNumber;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getZipcode() {
		return Zipcode;
	}

	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public Date getCurrentTime() {
		return CurrentTime;
	}

	public void setCurrentTime(Date currentTime) {
		CurrentTime = currentTime;
	}

	public AuthenticationBO getAuthenticationBO() {
		return authenticationBO;
	}

	public void setAuthenticationBO(AuthenticationBO authenticationBO) {
		this.authenticationBO = authenticationBO;
	}

	public DriverBO getDriverBO() {
		return driverBO;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

}
