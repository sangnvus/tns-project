package vn.co.taxinet.bean.driver;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.TaxiNetUsers;

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
	private String Email;
	private String PassWord;
	private String CompanyName;
	private String AddressNumber;
	private String Street;
	private String District;
	private String City;
	private String Zipcode;
	private String Languages;
	private String Coupon;
	private TaxiNetUserDAO taxiNetUserDAO;
	private DriverDAO driverDAO;
	private TaxiNetUsers user;
	private Driver driver;
	public TaxiNetUsers getUser() {
		return user;
	}

	public void setUser(TaxiNetUsers user) {
		this.user = user;
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

	public void init()  {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			driver = new Driver();
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			Email = (String) session.getAttribute("username");
			PassWord = (String) session.getAttribute("password");
			System.out.print(Email);
			System.out.print(getEmail());
			if (null == Email || null == PassWord) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/Login.xhtml");
				} catch (IOException e) {
					e.getMessage();
					e.printStackTrace();
				}
			} else {
				driver = driverBO.findDriverByEmail(Email);
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
			}

		}
	}

	public Driver driverFindByID(int id) {
		Driver driver = new Driver();
		return driver;
	}

	public String updateForm() {
		return null;
	}
}
