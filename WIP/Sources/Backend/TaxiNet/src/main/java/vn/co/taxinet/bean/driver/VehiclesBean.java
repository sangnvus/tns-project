package vn.co.taxinet.bean.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarType;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi
 *
 */
@ManagedBean(name = "vehiclesController", eager = true)
@SessionScoped
public class VehiclesBean implements Serializable {
	private static final long serialVersionUID = -1670554779803748502L;

	public String carMaker;
	public CarMaker selectedCarMaker;
	public List<CarMaker> carMakerList;
	public List<CarType> carTypeList;
	public Country selectedCountry;
	public List<Country> countryList;
	public List<CityName> cityList;
	public String carModel;
	public String yearOfProduct;
	public String plate;
	public String inColor;
	public String exColor;
	public String countryLicense;
	public String stateLicense;

	String userID;
	String username;
	String password;
	int companyID;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@ManagedProperty(value = "#{taxiNetUserBO}")
	private TaxiNetUserBO taxiNetUserBO;

	/**
	 * init data when load page
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			// TODO get value from session
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			userID = session.getAttribute("UserID").toString();
			username = session.getAttribute("Username").toString();
			password = session.getAttribute("Password").toString();
			// get user detail from userID

			TaxiNetUsers user = taxiNetUserBO.getUserInfo(userID);
			companyID = user.getCompany().getCompanyId();
			
			setCountryLicense("");
			setCarMaker("");
			setCarModel("");
			setPlate("");
			setStateLicense("");
			setInColor("");
			setExColor("");
			carMakerList = new ArrayList<CarMaker>();
			countryList = new ArrayList<Country>();
			carTypeList = new ArrayList<CarType>();
			cityList = new ArrayList<CityName>();
			carMakerList = driverBO.getCarMakerList();
			countryList = driverBO.getCountryList();
		}
	}

	/**
	 * add year of the product
	 * 
	 * @return year
	 */
	public List<SelectItem> addValueForYearOfProduct() {
		List<SelectItem> result = new ArrayList<SelectItem>();
		result.add(new SelectItem("", "Year of Product"));
		result.add(new SelectItem("2009", "2009"));
		result.add(new SelectItem("2010", "2010"));
		result.add(new SelectItem("2011", "2011"));
		result.add(new SelectItem("2012", "2012"));
		result.add(new SelectItem("2013", "2013"));
		result.add(new SelectItem("2014", "2014"));
		result.add(new SelectItem("2015", "2015"));
		return result;
	}

	/**
	 * add color for internal
	 * 
	 * @return
	 */
	public List<SelectItem> addInternalColor() {
		List<SelectItem> result = new ArrayList<SelectItem>();
		result.add(new SelectItem("", "Internal Color"));
		result.add(new SelectItem("green", "Green"));
		result.add(new SelectItem("red", "Red"));
		result.add(new SelectItem("brown", "Brown"));
		result.add(new SelectItem("yellow", "Yellow"));
		result.add(new SelectItem("orange", "Orange"));
		return result;
	}

	/**
	 * add color for external
	 * 
	 * @return
	 */
	public List<SelectItem> addExternalColor() {
		List<SelectItem> result = new ArrayList<SelectItem>();
		result.add(new SelectItem("", "External Color"));
		result.add(new SelectItem("green", "Green"));
		result.add(new SelectItem("red", "Red"));
		result.add(new SelectItem("brown", "Brown"));
		result.add(new SelectItem("yellow", "Yellow"));
		result.add(new SelectItem("orange", "Orange"));
		return result;
	}

	/**
	 * update Car Model value when select Car Maker
	 */
	public void handleSelectCarMaker() {
		if (carMaker != null) {
			carTypeList = driverBO.getCarModelList(carMaker);
		}
	}

	/**
	 * update State value when select Country
	 */
	public void handlSelectCountry() {
		if (countryLicense != null) {
			cityList = driverBO.getCityNameList(countryLicense);
		}
	}

	/**
	 * action for Add Vehicles BUtton
	 */
	public String addVehiclesButton() {
		// TODO validate informations
		if (carMaker == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn hãng sản xuất xe"));
			return null;
		} else if (carModel == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn loại xe"));
			return null;
		} else if (yearOfProduct == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn năm sản xuất"));
			return null;
		} else if (inColor == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn màu nội thất"));
			return null;
		} else if (exColor == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn màu sơn"));
			return null;
		} else if (plate == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy nhập biển số xe"));
			return null;
		} else if (countryLicense == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn quốc gia cấp phép"));
			return null;
		} else if (stateLicense == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
							"Hãy chọn thành phố cấp phép"));
			return null;
		} else {
			String result = driverBO.persistVehicles(carMaker, carModel,
					yearOfProduct, inColor, exColor, plate, countryLicense,
					stateLicense, userID);
			if (result != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Success",
								"Thêm phương tiện thành công"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error", "Thêm phương tiện thất bại"));
			}
			return null;
		}
	}

	// getter & setter
	public String getCarMaker() {
		return carMaker;
	}

	public void setCarMaker(String carMaker) {
		this.carMaker = carMaker;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getYearOfProduct() {
		return yearOfProduct;
	}

	public void setYearOfProduct(String yearOfProduct) {
		this.yearOfProduct = yearOfProduct;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getInColor() {
		return inColor;
	}

	public void setInColor(String inColor) {
		this.inColor = inColor;
	}

	public String getExColor() {
		return exColor;
	}

	public void setExColor(String exColor) {
		this.exColor = exColor;
	}

	public String getCountryLicense() {
		return countryLicense;
	}

	public void setCountryLicense(String countryLicense) {
		this.countryLicense = countryLicense;
	}

	public String getStateLicense() {
		return stateLicense;
	}

	public void setStateLicense(String stateLicense) {
		this.stateLicense = stateLicense;
	}

	public CarMaker getSelectedCarMaker() {
		return selectedCarMaker;
	}

	public void setSelectedCarMaker(CarMaker selectedCarMaker) {
		this.selectedCarMaker = selectedCarMaker;
	}

	public List<CarMaker> getCarMakerList() {
		return carMakerList;
	}

	public void setCarMakerList(List<CarMaker> carMakerList) {
		this.carMakerList = carMakerList;
	}

	public List<CarType> getCarTypeList() {
		return carTypeList;
	}

	public void setCarTypeList(List<CarType> carTypeList) {
		this.carTypeList = carTypeList;
	}

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<CityName> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityName> cityList) {
		this.cityList = cityList;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

	public void setTaxiNetUserBO(TaxiNetUserBO taxiNetUserBO) {
		this.taxiNetUserBO = taxiNetUserBO;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

}
