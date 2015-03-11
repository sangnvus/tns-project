package vn.co.taxinet.bean.feeagent;

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
import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.dto.TaxiNetUserDTO;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

/**
 * @author Ecchi bean controller for DaiLy-Home.xhtml
 */
@ManagedBean(name = "feeAgentHomeController", eager = true)
@SessionScoped
public class FeeAgentHomeBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -774991307566016525L;

	public String accountSearchName;// tÃªn Ä‘Äƒng nháº­p cáº§n tÃ¬m
	public String userSearchName;// tÃªn ngÆ°á»�i sá»­ dá»¥ng

	// nhÃ¢n viÃªn thu ngÃ¢n
	public String agentname;
	public String agentId;
	public String agentRole;
	public Date chargeDate;
	public String agentCode;
	public String invoiceNumber;
	public double balance;
	public double chargeAmount;
	public double chargedAmount;
	public List<TaxiNetUsers> listUser;
	public List<TaxiNetUserDTO> listUserDTO;
	public TaxiNetUserDTO selectedUser;
	public LazyDataModel<TaxiNetUserDTO> lazyListUser;
	public String userId;
	@ManagedProperty(value = "#{agentBO}")
	private AgentBO agentBO;

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
			agentId =  session.getAttribute("UserID").toString();
			agentname = session.getAttribute("Username").toString();
			agentRole = session.getAttribute("Role").toString();
			accountSearchName = "";
			userSearchName = "";
			doSearchAllUser();
		}
	}

	/**
	 * event cho button TÃ¬m kiáº¿m
	 */

	public void doSearchAllUser() {
		lazyListUser = new LazyDataModel<TaxiNetUserDTO>() {
			private static final long serialVersionUID = -3455481894912786794L;
			@Override
			public List<TaxiNetUserDTO> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				int pageIndex = first;
				pageSize = 3;
				listUserDTO = agentBO.paginationList(accountSearchName,
						userSearchName, pageIndex, pageSize);
				return listUserDTO;
			}
		};
		lazyListUser.setRowCount(agentBO.countDriverCompany(accountSearchName,
				userSearchName));
		// lazyListUser.setRowCount(3);
	}

	/**
	 * event cho button Náº¡p tiá»�n
	 */
	public void doChargeFeeForSelectedUser() {
		// hardcode
		agentId = "6";
		agentBO.chargeMoney(invoiceNumber, userId, agentId, chargeDate,
				chargeAmount);
	}

	public void createInvoiceNumber() {
		String year = "" + (Utility.getCurrentDateTime().getYear() + 1900);
		String month;
		String day;
		String hour;
		String min;
		String sec;
		if (Utility.getCurrentDateTime().getMonth() < 10) {
			month = "0" + Utility.getCurrentDateTime().getMonth();
		} else {
			month = "" + Utility.getCurrentDateTime().getMonth();
		}
		if (Utility.getCurrentDateTime().getDay() < 10) {
			day = "0" + Utility.getCurrentDateTime().getDay();
		} else {
			day = "" + Utility.getCurrentDateTime().getDay();
		}
		if (Utility.getCurrentDateTime().getHours() < 10) {
			hour = "0" + Utility.getCurrentDateTime().getHours();
		} else {
			hour = "" + Utility.getCurrentDateTime().getHours();
		}
		if (Utility.getCurrentDateTime().getMinutes() < 10) {
			min = "0" + Utility.getCurrentDateTime().getMinutes();
		} else {
			min = "" + Utility.getCurrentDateTime().getMinutes();
			;
		}
		if (Utility.getCurrentDateTime().getSeconds() < 10) {
			sec = "0" + Utility.getCurrentDateTime().getSeconds();
		} else {
			sec = "" + Utility.getCurrentDateTime().getSeconds();
		}
		invoiceNumber = agentname + Utility.dateToString(Utility.getCurrentDateTime(), "yyyyMMddHHmmssSSS");
	}

	public void selectUser(TaxiNetUserDTO user) {
		if (user != null) {
			selectedUser = user;
			userId = "";
			balance = 0;
			chargeAmount = 0;
			chargedAmount = 0;
			chargeDate = null;
			balance = user.getBalance();
			userId = user.getUserId();
			createInvoiceNumber();
			canculateAmount();
			getDate();
		}
	}

	public void getDate() {

		chargeDate = Utility.getCurrentDateTime();
	}

	public void canculateAmount() {
		chargedAmount = chargeAmount + balance;
	}

	// getter&setter

	public String getAccountSearchName() {
		return accountSearchName;
	}

	public void setAccountSearchName(String accountSearchName) {
		this.accountSearchName = accountSearchName;
	}

	public String getUserSearchName() {
		return userSearchName;
	}

	public void setUserSearchName(String userSearchName) {
		this.userSearchName = userSearchName;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public List<TaxiNetUsers> getListUser() {
		return listUser;
	}

	public void setListUser(List<TaxiNetUsers> listUser) {
		this.listUser = listUser;
	}

	public TaxiNetUserDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(TaxiNetUserDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public AgentBO getAgentBO() {
		return agentBO;
	}

	public void setAgentBO(AgentBO agentBO) {
		this.agentBO = agentBO;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public List<TaxiNetUserDTO> getListUserDTO() {
		return listUserDTO;
	}

	public void setListUserDTO(List<TaxiNetUserDTO> listUserDTO) {
		this.listUserDTO = listUserDTO;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public LazyDataModel<TaxiNetUserDTO> getLazyListUser() {
		return lazyListUser;
	}

	public double getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public double getChargedAmount() {
		return chargedAmount;
	}

	public void setChargedAmount(double chargedAmount) {
		this.chargedAmount = chargedAmount;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentRole() {
		return agentRole;
	}

	public void setAgentRole(String agentRole) {
		this.agentRole = agentRole;
	}

}
