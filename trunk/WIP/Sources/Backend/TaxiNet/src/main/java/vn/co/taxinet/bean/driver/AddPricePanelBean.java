package vn.co.taxinet.bean.driver;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.orm.Company;
import vn.co.taxinet.orm.PricePanel;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

@ManagedBean(name = "addPricePanelController", eager = true)
@SessionScoped
public class AddPricePanelBean implements Serializable {
	private static final long serialVersionUID = -758129923740231814L;

	public String UserID;
	public String Username;
	public String Password;
	public int companyID;

	public String priceTitle;
	public double openPrice;
	public double openKM;
	public double firstPrice;
	public double firstKM;
	public double nextPrice;
	public double waitingPrice;
	public double returnPrice;
	public Date startDate;

	public PricePanel pricePanel;
	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;

	@ManagedProperty(value = "#{taxiNetUserBO}")
	private TaxiNetUserBO taxiNetUserBO;

	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			try {
				// TODO get value from session
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession session = request.getSession();
				UserID = session.getAttribute("UserID").toString();
				Password = session.getAttribute("Password").toString();
				Username = session.getAttribute("Username").toString();

				TaxiNetUsers user = taxiNetUserBO.getUserInfo(UserID);
				companyID = user.getCompany().getCompanyId();

				cancelButton();

			} catch (Exception ex) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/Login.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * cancel button
	 * 
	 * @return clear fields
	 */
	public void cancelButton() {
		priceTitle = "";
		openKM = 0;
		openPrice = 0;
		firstPrice = 0;
		nextPrice = 0;
		firstKM = 0;
		waitingPrice = 0;
		returnPrice = 0;
		startDate = Utility.getCurrentDate();
	}

	/**
	 * add button
	 * 
	 * @return result
	 */
	public String addPricePanelButton() {
		// TODO validate
		if (startDate.before(Utility.getCurrentDate())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(Constants.Message.ERROR,
							"Start Date invalid"));
			return null;
		} else {
			pricePanel = new PricePanel();
			pricePanel.setCompany(new Company());
			pricePanel.getCompany().setCompanyId(companyID);
			pricePanel.setTittle(priceTitle);
			pricePanel.setOpenPrice(openPrice);
			pricePanel.setOpenKm(openKM);
			pricePanel.setFirstKm(firstKM);
			pricePanel.setFirstKmprice(firstPrice);
			pricePanel.setNextKmprice(nextPrice);
			pricePanel.setNextKm(firstKM);
			pricePanel.setStartDate(startDate);
			pricePanel.setWaitingPrice(waitingPrice);
			pricePanel.setReturnTripPrice(returnPrice);

			pricePanel.setCreatedBy(Username);
			pricePanel.setLastModifiedBy(Username);
			pricePanel.setCreatedDate(Utility.getCurrentDate());
			pricePanel.setLastModifiedDate(Utility.getCurrentDate());

			int result = driverBO.addPricePanel(pricePanel);
			if (result == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								Constants.Message.ERROR,
								Constants.Message.ERROR));
				return null;
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								Constants.Message.SUCCESS,
								Constants.Message.SUCCESS));
				cancelButton();
				return null;
			}
		}
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

	public String getPriceTitle() {
		return priceTitle;
	}

	public void setPriceTitle(String priceTitle) {
		this.priceTitle = priceTitle;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getOpenKM() {
		return openKM;
	}

	public void setOpenKM(double openKM) {
		this.openKM = openKM;
	}

	public double getNextPrice() {
		return nextPrice;
	}

	public void setNextPrice(double nextPrice) {
		this.nextPrice = nextPrice;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getFirstKM() {
		return firstKM;
	}

	public void setFirstKM(double firstKM) {
		this.firstKM = firstKM;
	}

	public double getWaitingPrice() {
		return waitingPrice;
	}

	public void setWaitingPrice(double waitingPrice) {
		this.waitingPrice = waitingPrice;
	}

	public double getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(double returnPrice) {
		this.returnPrice = returnPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public PricePanel getPricePanel() {
		return pricePanel;
	}

	public void setPricePanel(PricePanel pricePanel) {
		this.pricePanel = pricePanel;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	public void setTaxiNetUserBO(TaxiNetUserBO taxiNetUserBO) {
		this.taxiNetUserBO = taxiNetUserBO;
	}
}
