package vn.co.taxinet.bean.driver;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.map.MapModel;
import org.springframework.context.annotation.Scope;

import vn.co.taxinet.bean.BaseBean;
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.orm.Driver;

@Named
@Scope(value = "session")
public class LiveStatusBean extends BaseBean {
	private static final long serialVersionUID = 5900995349430519062L;

	public String UserID;
	public String password;
	public String username;

	public List<Driver> driverList;

	public String carParkLongtitude;
	public String carParkLattitude;

	private MapModel simpleModel;

	@Inject
	private DriverBO driverBO;

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		UserID = session.getAttribute("UserID").toString();
		username = session.getAttribute("Username").toString();
		password = session.getAttribute("Password").toString();
		// TODO : need usergroup value for validate permission to access this
		// page
		
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
}
