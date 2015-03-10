package vn.co.taxinet.bean.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.orm.CarMaker;
import vn.co.taxinet.orm.CarType;
import vn.co.taxinet.orm.Language;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi
 * @category bean controller for Driver.xhtml
 */
@ManagedBean(name = "driverListBean", eager = true)
@SessionScoped
public class DriverListBean implements Serializable {
	private static final long serialVersionUID = -2980047468030176768L;

	// params for user informations (Session/cookies using0
	public String UserID;
	public String Password;
	public String Username;
	public String companyID;
	public LazyDataModel<DriverDTO> driverList;
	public DriverDTO selectedDriver;
	public DriverDTO driverDTO;

	public List<Language> langList;
	public List<CarMaker> carMakerList;
	public List<CarType> carTypeList;

	private boolean isReadOnly;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@ManagedProperty(value = "#{taxiNetUserBO}")
	private TaxiNetUserBO taxiNetUserBO;

	@PostConstruct
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			// TODO get value from sessions
			HttpSession session = request.getSession();
			UserID = session.getAttribute("UserID").toString();
			Username = session.getAttribute("Username").toString();
			Password = session.getAttribute("Password").toString();

			TaxiNetUsers user = taxiNetUserBO.getUserInfo(UserID);
			companyID = String.valueOf(user.getCompany().getCompanyId());
			// end of getting value
			findAllDriver();
			getDefaultValue();
			setReadOnly(true);
			// set new value to constructor
			driverDTO = new DriverDTO();
			selectedDriver = new DriverDTO();
		}
	}

	/**
	 * get Default value for nation, language, carmaker
	 */
	public void getDefaultValue() {
		// carMakerList = driverBO.getCarMakerList();
		langList = taxiNetUserBO.listAllLanguage();
		carTypeList = driverBO.listAllCarType();
	}

	/**
	 * action find all driver
	 */
	public void findAllDriver() {
		companyID = "1";
		driverList = new LazyDataModel<DriverDTO>() {
			private static final long serialVersionUID = -8351117462011564508L;

			@Override
			public List<DriverDTO> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				List<DriverDTO> listDrivers = new ArrayList<DriverDTO>();
				int pageIndex = first;
				pageSize = Constants.PAGE_SIZE;
				// TODO hardcode for testing
				listDrivers = driverBO.getAllDriverOfCompany(companyID,
						pageIndex, pageSize);
				return listDrivers;
			}
		};
		int count = driverBO.countAllDriverOfCompany(companyID);
		driverList.setRowCount(count);
		DataTable d = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot().findComponent("driver-form:driver-list");
		d.setFirst(0);
	}

	/**
	 * action event for table's pagination
	 * 
	 * @param event
	 */
	public void onPageAction(PageEvent event) {
		findAllDriver();
	}

	/**
	 * redirect to add new driver button
	 */
	public void redirectToAddNewDriver() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/TN/faces/xhtml/driver/AddNewDrivers.xhtml");
		} catch (Exception ex) {
			ex.getMessage();
		}
	}

	/**
	 * action control open dialog command link
	 * 
	 * @param driver
	 */
	public void showChosenDriverInfo(DriverDTO driver) {
		if (driver != null) {
			// TODO set value for driverDTO and readOnly
			setReadOnly(true);
			setDriverDTO(driver);
		} else {
			// TODO facesmessage for notify error
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.FacesMess.FATAL,
							Constants.Errors.CANT_GET_REQUEST));
		}
	}

	/**
	 * action event for button edit
	 * 
	 * @param driver
	 */
	public void editSelectedDriverInfo(DriverDTO driver) {
		if (driver != null) {
			// TODO set value for readonly and driverDTO
			setReadOnly(false);
			setDriverDTO(driver);
		} else {
			// TODO facesmessage for notify error
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.FacesMess.FATAL,
							Constants.Errors.CANT_GET_REQUEST));
		}
	}

	/**
	 * action of remove button
	 * 
	 * @param driver
	 */
	public void removeDriver(DriverDTO driver) {
		if (driver != null) {
			// TODO remove driver
			String id = driver.getId();
			String result = driverBO.removeDriver(id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, result,
							Constants.SUCCESS));
			findAllDriver();
		} else {
			// TODO facesmessage to notify error
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.FacesMess.FATAL,
							Constants.Errors.CANT_GET_REQUEST));
		}
	}

	/**
	 * action of Save button on Driver Information dialog
	 * 
	 * @return
	 */
	public String editButton() {
		// validate infomation
		if (!driverDTO.getPhoneNumber()
				.matches(Constants.PHONENO_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							Constants.Message.ERROR,
							Constants.Message.PHONE_ERROR));
			return null;
		} else if (!driverDTO.getEmail().matches(Constants.EMAIL_PATTERN_REGEX)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							Constants.Message.ERROR,
							Constants.Message.EMAIL_ERROR));
			return null;
		} else {
			driverBO.editDriverInfo(driverDTO);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.Message.SUCCESS,
							Constants.Message.SUCCESS));
			return null;
		}
	}

	// getter&setter

	public String getUserID() {
		return UserID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public DriverDTO getSelectedDriver() {
		return selectedDriver;
	}

	public void setSelectedDriver(DriverDTO selectedDriver) {
		this.selectedDriver = selectedDriver;
	}

	public DriverDTO getDriverDTO() {
		return driverDTO;
	}

	public void setDriverDTO(DriverDTO driverDTO) {
		this.driverDTO = driverDTO;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public LazyDataModel<DriverDTO> getDriverList() {
		return driverList;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public List<Language> getLangList() {
		return langList;
	}

	public void setLangList(List<Language> langList) {
		this.langList = langList;
	}

	public List<CarMaker> getCarMakerList() {
		return carMakerList;
	}

	public void setCarMakerList(List<CarMaker> carMakerList) {
		this.carMakerList = carMakerList;
	}

	public void setTaxiNetUserBO(TaxiNetUserBO taxiNetUserBO) {
		this.taxiNetUserBO = taxiNetUserBO;
	}

	public List<CarType> getCarTypeList() {
		return carTypeList;
	}

	public void setCarTypeList(List<CarType> carTypeList) {
		this.carTypeList = carTypeList;
	}

}
