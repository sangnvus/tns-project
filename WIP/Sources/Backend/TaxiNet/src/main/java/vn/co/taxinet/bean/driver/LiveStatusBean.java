package vn.co.taxinet.bean.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;

import vn.co.taxinet.bean.BaseBean;
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.CurrentStatus;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.TaxiNetUsers;

@ManagedBean(name = "liveStatusBean", eager = true)
@SessionScoped
public class LiveStatusBean extends BaseBean {
	private static final long serialVersionUID = 5900995349430519062L;

	public String UserID;
	public String password;
	public String username;

	public List<Driver> driverList;

	public String carParkLongtitude;
	public String carParkLattitude;

	private MapModel simpleModel;

	private Driver driver;

	private LazyDataModel<Driver> lazyDriverList;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@Inject
	private TaxiNetUserDAO taxiNetUserDAO;

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		// UserID = session.getAttribute("UserID").toString();
		// username = session.getAttribute("Username").toString();
		// password = session.getAttribute("Password").toString();
		// TODO : need usergroup value for validate permission to access this
		// page
		driverList = new ArrayList<Driver>();
		simpleModel = new DefaultMapModel();
		// TaxiNetUsers user = taxiNetUserDAO.findByID(UserID);
		// final int companyID = user.getCompany().getCompanyId();
		driverList = driverBO.countAllDriverByCompanyID("1");
		// driverList = driverBO.countAllDriverByCompanyID(String.valueOf(1));
		lazyDriverList = new LazyDataModel<Driver>() {
			private static final long serialVersionUID = -8351117462011564508L;

			@Override
			public List<Driver> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				List<Driver> listDrivers = new ArrayList<Driver>();
				int pageIndex = first;
				listDrivers = driverBO.findDriverByCompanyID(String.valueOf(1),
						pageIndex, pageSize);
				return listDrivers;
			}
		};
		int count = driverList.size();
		lazyDriverList.setRowCount(count);

		for (int i = 0; i < count; i++) {
			Driver driver = (Driver) driverList.get(i);
			CurrentStatus currentLocation = driver.getCurrentstatus();
			if (currentLocation.getCurrentLatitude() != null
					&& currentLocation.getCurrentLongtitude() != null) {
				LatLng location = new LatLng(
						currentLocation.getCurrentLatitude(),
						currentLocation.getCurrentLongtitude());
				simpleModel.addOverlay(new Marker(location, driver
						.getFirstName() + " " + driver.getLastName()));
			}
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Driver> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}

	public String getCarParkLongtitude() {
		return carParkLongtitude;
	}

	public void setCarParkLongtitude(String carParkLongtitude) {
		this.carParkLongtitude = carParkLongtitude;
	}

	public String getCarParkLattitude() {
		return carParkLattitude;
	}

	public void setCarParkLattitude(String carParkLattitude) {
		this.carParkLattitude = carParkLattitude;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	public LazyDataModel<Driver> getLazyDriverList() {
		return lazyDriverList;
	}
}
