package vn.co.taxinet.bean.rider;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import vn.co.taxinet.bean.BaseBean;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.dto.TaxiNetUserDTO;
import vn.co.taxinet.dto.TripDTO;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

/**
 bean controller for RiderFreeTrip.xhtml
 */
@ManagedBean(name = "riderMyTripsController", eager = true)
@SessionScoped
public class RiderMyTripsBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -774991307566016525L;

	public String searchCity;
	public Date searchFromDate;
	public Date searchToDate;
	public String searchPaymentType;
	
	public String riderName;
	public String riderId;
	public String Role;
	public String fromAddress;
	public String toAddress;
	public Date timeStart;
	public String plate;
	public String paymentType;
	public String fee;	
	public List<TaxiNetUsers> listUser;
	public List<TaxiNetUserDTO> listUserDTO;
	public List<TripDTO> listTripDTO;
	public TaxiNetUserDTO selectedUser;
	public LazyDataModel<TaxiNetUserDTO> lazyListUser;
	public String userId;
	@ManagedProperty(value = "#{riderBO}")
	private RiderBO riderBO;

	/**
	 * khá»Ÿi táº¡o ngay khi load trang
	 */
	@PostConstruct
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			// lazyListUser.setRowCount(agentBO.countDriverCompany(accountSearchName,
			// userSearchName));
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			riderId =  session.getAttribute("UserID").toString();
			riderName = session.getAttribute("Username").toString();
			Role = session.getAttribute("Role").toString();
			searchCity = "";
			searchPaymentType = "";
			doSearchAllUser();
		}
	}

	/**
	 * event cho button Search
	 */

	public void doSearchAllUser() {
		lazyListUser = new LazyDataModel<TaxiNetUserDTO>() {
			private static final long serialVersionUID = -3455481894912786794L;
			@Override
			public List<TaxiNetUserDTO> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				int pageIndex = first;
				pageSize = 3;
				listUserDTO = riderBO.paginationList(searchCity,
						searchFromDate, pageIndex, pageSize);
				return listUserDTO;
			}
		};
		lazyListUser.setRowCount(4);
		// lazyListUser.setRowCount(3);
	}

	
	public void selectTrip(TaxiNetUserDTO user) {
		if (user != null) {
			selectedUser = user;
			userId = "";
			timeStart = null;
			fromAddress = null;
			toAddress = null;
			plate = "";
			fee = "";
			paymentType = "";
			userId = user.getUserId();
			getDate();
		}
	}

	public void getDate() {

		timeStart = Utility.getCurrentDateTime();
	}

	
	

	public String getSearchCity() {
		return searchCity;
	}

	public void setSearchCity(String searchCity) {
		this.searchCity = searchCity;
	}

	public Date getSearchFromDate() {
		return searchFromDate;
	}

	public void setSearchFromDate(Date searchFromDate) {
		this.searchFromDate = searchFromDate;
	}

	public Date getSearchToDate() {
		return searchToDate;
	}

	public void setSearchToDate(Date searchToDate) {
		this.searchToDate = searchToDate;
	}

	public String getSearchPaymentType() {
		return searchPaymentType;
	}

	public void setSearchPaymentType(String searchPaymentType) {
		this.searchPaymentType = searchPaymentType;
	}

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	public String getRiderId() {
		return riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Date getStartDate() {
		return timeStart;
	}

	public void setStartDate(Date startDate) {
		this.timeStart = startDate;
	}

	public List<TaxiNetUsers> getListUser() {
		return listUser;
	}

	public void setListUser(List<TaxiNetUsers> listUser) {
		this.listUser = listUser;
	}

	public List<TaxiNetUserDTO> getListUserDTO() {
		return listUserDTO;
	}

	public void setListUserDTO(List<TaxiNetUserDTO> listUserDTO) {
		this.listUserDTO = listUserDTO;
	}

	public List<TripDTO> getListTripDTO() {
		return listTripDTO;
	}

	public void setListTripDTO(List<TripDTO> listTripDTO) {
		this.listTripDTO = listTripDTO;
	}

	public TaxiNetUserDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(TaxiNetUserDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public LazyDataModel<TaxiNetUserDTO> getLazyListUser() {
		return lazyListUser;
	}

	public void setLazyListUser(LazyDataModel<TaxiNetUserDTO> lazyListUser) {
		this.lazyListUser = lazyListUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public RiderBO getRiderBO() {
		return riderBO;
	}

	public void setRiderBO(RiderBO riderBO) {
		this.riderBO = riderBO;
	}


	// getter&setter

}
