package vn.co.taxinet.bean.driver;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.orm.PricePanel;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

@ManagedBean(name = "pricePanelListBean", eager = true)
@SessionScoped
public class PricePanelListBean implements Serializable {
	private static final long serialVersionUID = 3641531913563331417L;

	public String UserID;
	public String Username;
	public String Password;
	public int companyID;
	public String currentDateTime;
	public boolean isDisabled;

	public LazyDataModel<PricePanel> pricePanelList;

	public PricePanel selectedPricePanel;

	public PricePanel pricePanel;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@ManagedProperty(value = "#{taxiNetUserBO}")
	private TaxiNetUserBO taxiNetUserBO;

	// function

	/**
	 * functions init load
	 */
	@PostConstruct
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {

			// TODO set default value
			selectedPricePanel = new PricePanel();
			pricePanel = new PricePanel();
			Date currentDate = Utility.getCurrentDateTime();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			currentDateTime = format.format(currentDate);
			isDisabled = true;

			// TODO get user's stored value from session
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			UserID = session.getAttribute("UserID").toString();
			Username = session.getAttribute("Username").toString();
			Password = session.getAttribute("Password").toString();

			// TODO get user's information from UserID
			TaxiNetUsers user = taxiNetUserBO.getUserInfo(UserID);
			companyID = user.getCompany().getCompanyId();

			// TODO get pricepanel list detail
			listAllPricePanel();

		}
	}

	/**
	 * action list all price panel
	 */
	public void listAllPricePanel() {

		pricePanelList = new LazyDataModel<PricePanel>() {
			private static final long serialVersionUID = 7404703261687282180L;

			@Override
			public List<PricePanel> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				List<PricePanel> pricePanelList = new ArrayList<PricePanel>();
				pageSize = Constants.PAGE_SIZE;
				int pageIndex = first;
				pricePanelList = driverBO.getAllPricePanel(companyID,
						currentDateTime, pageIndex, pageSize);
				if (pricePanelList == null) {
					pricePanelList = new ArrayList<PricePanel>();
				}
				return pricePanelList;
			}
		};

	}

	/**
	 * action of Detail Button
	 * 
	 * @param price
	 * @return
	 */
	public String getPricePanelDetail(PricePanel price) {
		if (null == price) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.FacesMess.FATAL,
							Constants.Message.REQUEST_NOT_FOUND));
			return null;
		} else {
			setDisabled(true);
			setPricePanel(price);
			return null;
		}
	}

	/**
	 * action for Edit button
	 * 
	 * @param price
	 * @return
	 */
	public String editPricePanelDetail(PricePanel price) {
		if (null == price) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.FacesMess.FATAL,
							Constants.Message.REQUEST_NOT_FOUND));
			return null;
		} else {
			setDisabled(false);
			setPricePanel(price);
			return null;
		}
	}

	/**
	 * action for Save button
	 * 
	 * @return
	 */
	public String savePricePanel() {
		//TODO validate input value
		return null;
	}

	// getter&setter

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	public void setTaxiNetUserBO(TaxiNetUserBO taxiNetUserBO) {
		this.taxiNetUserBO = taxiNetUserBO;
	}

	public PricePanel getSelectedPricePanel() {
		return selectedPricePanel;
	}

	public void setSelectedPricePanel(PricePanel selectedPricePanel) {
		this.selectedPricePanel = selectedPricePanel;
	}

	public PricePanel getPricePanel() {
		return pricePanel;
	}

	public void setPricePanel(PricePanel pricePanel) {
		this.pricePanel = pricePanel;
	}

	public LazyDataModel<PricePanel> getPricePanelList() {
		return pricePanelList;
	}

	public String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

}
