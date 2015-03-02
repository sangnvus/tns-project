package vn.co.taxinet.bean.driver;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Language;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.UserGroup;

@ManagedBean(name = "driverCIController", eager = true)
@SessionScoped
public class DriverContactInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5287158223429547337L;
	private String FirstName;
	private String LastName;
	private String VATNumber;
	private String MobieNo;
	private String UserName;
	private String Email;
	private String Password;
	private String CompanyName;
	private String AddressNumber;
	private String Street;
	private String District;
	private String City;
	private String Zipcode;
	private String Languages;
	private String Coupon;
	private String Language;
	private TaxiNetUserDAO taxiNetUserDAO;
	private TaxiNetUserBO taxiNetUserBO;
	private DriverDAO driverDAO;
	private TaxiNetUsers user;
	private Driver driver;
	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	public TaxiNetUserBO getTaxiNetUserBO() {
		return taxiNetUserBO;
	}

	public void setTaxiNetUserBO(TaxiNetUserBO taxiNetUserBO) {
		this.taxiNetUserBO = taxiNetUserBO;
	}

	public TaxiNetUsers getUser() {
		return user;
	}

	public void setUser(TaxiNetUsers user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public DriverBO getDriverBO() {
		return driverBO;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	public TaxiNetUserDAO getTaxiNetUserDAO() {
		return taxiNetUserDAO;
	}

	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	public DriverDAO getDriverDAO() {
		return driverDAO;
	}

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
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

	public String getVATNumber() {
		return VATNumber;
	}

	public void setVATNumber(String vATNumber) {
		VATNumber = vATNumber;
	}

	public String getMobieNo() {
		return MobieNo;
	}

	public void setMobieNo(String mobieNo) {
		MobieNo = mobieNo;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
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

	public String getLanguages() {
		return Languages;
	}

	public void setLanguages(String languages) {
		Languages = languages;
	}

	public String getCoupon() {
		return Coupon;
	}

	public void setCoupon(String coupon) {
		Coupon = coupon;
	}
	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public void init() throws TNSException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			driver = new Driver();
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			UserName = (String) session.getAttribute("username");
			Password = (String) session.getAttribute("password");
			System.out.println(UserName);
			System.out.println(Password);
			if (null == UserName || null == Password) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/Login.xhtml");
				} catch (IOException e) {
					e.getMessage();
					e.printStackTrace();
				}
			} else {
				driver = driverBO.findDriverByUsername(UserName);
				if (driver == null) {
					try {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/TN/faces/Login.xhtml");
					} catch (IOException e) {
						e.getMessage();
						e.printStackTrace();
					}
				}
				System.out.print(1);
				FirstName = driver.getFirstName();
				LastName = driver.getLastName();
				MobieNo = driver.getMobileNo();
				Email = driver.getTaxinetusers().getEmail();
				AddressNumber = "";
				City = "";
				Coupon ="";
				VATNumber ="";
				CompanyName = "";
				Street = "";
				District = "";
				City = "";
				Zipcode = driver.getTaxinetusers().getPostalCode();
				Language = driver.getTaxinetusers().getLanguage().getLanguageCode();
				System.out.print(Language);
			}
		}
	}

	public Driver driverFindByID(int id) {
		Driver driver = new Driver();
		return driver;
	}

	public String doCheck() throws TNSException, IOException {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		// set session attribute
		session.setAttribute("username", UserName);
		session.setAttribute("password", Password);
		// redirect to login page
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/TN/faces/xhtml/driver/ContactInfo.xhtml");
		return null;
	}
	
	public String doEdit(){
		return "EditContactInfo.xhtml";
	}
	
	public String doUpdate() throws IOException, TNSException{
		
		Driver updateDriver = new Driver();

		updateDriver.setFirstName(getFirstName());
		updateDriver.setLastName(getLastName());
		updateDriver.setMobileNo(getMobieNo());
		updateDriver.setTaxinetusers(new TaxiNetUsers());
		updateDriver.getTaxinetusers().setEmail(Email);
		updateDriver.getTaxinetusers().setUsername(UserName);
		updateDriver.getTaxinetusers().setPostalCode(Zipcode);
		// TODO change hardcode of country
		updateDriver.getTaxinetusers().setCountry(new Country());
		updateDriver.getTaxinetusers().getCountry().setCode("VN");

		updateDriver.getTaxinetusers().setUsergroup(new UserGroup());
		updateDriver.getTaxinetusers().getUsergroup()
				.setGroupCode(Constants.GroupUser.DRIVER);
		updateDriver.getTaxinetusers().setStatus(Constants.UserStatus.ACTIVE);
		updateDriver.getTaxinetusers().setLanguage(new Language());
		updateDriver.getTaxinetusers().getLanguage()
				.setLanguageCode(Constants.Language.LANG_EN_CODE);

		updateDriver.setMobileNo(MobieNo);
		updateDriver.setFirstName(FirstName);
		updateDriver.setLastName(LastName);
		driverBO.update(updateDriver);
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		// set session attribute
		session.setAttribute("username", UserName);
		session.setAttribute("password", Password);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/TN/faces/xhtml/driver/ContactInfo.xhtml");
		return null;
	}
}
