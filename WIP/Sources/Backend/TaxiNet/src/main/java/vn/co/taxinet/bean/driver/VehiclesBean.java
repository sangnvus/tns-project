package vn.co.taxinet.bean.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarModel;
import vn.co.taxinet.orm.City;
import vn.co.taxinet.orm.Country;

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
	public List<CarModel> carModelList;
	public Country selectedCountry;
	public List<Country> countryList;
	public List<City> cityList;
	public String carModel;
	public String yearOfProduct;
	public String plate;
	public String inColor;
	public String exColor;
	public String countryLicense;
	public String stateLicense;

	@Inject
	private DriverBO driverBO;

	/**
	 * init data when load page
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {

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
		result.add(new SelectItem("", "Internal Color"));
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
		// carModelList =
	}

	/**
	 * update State value when select Country
	 */
	public void handlSelectCountry() {
		// cityList =
	}

	/**
	 * action for Add Vehicles BUtton
	 */
	public void addVehiclesButton() {

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

	public List<CarModel> getCarModelList() {
		return carModelList;
	}

	public void setCarModelList(List<CarModel> carModelList) {
		this.carModelList = carModelList;
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

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public DriverBO getDriverBO() {
		return driverBO;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

}
