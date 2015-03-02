package vn.co.taxinet.bean.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dto.DriverDTO;

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
	public String companyID = "1";
	public LazyDataModel<DriverDTO> driverList;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@PostConstruct
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			// TODO get value from sessions

			// end of getting value
			findAllDriver();
		}
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

	// getter&setter

	public String getUserID() {
		return UserID;
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
}
