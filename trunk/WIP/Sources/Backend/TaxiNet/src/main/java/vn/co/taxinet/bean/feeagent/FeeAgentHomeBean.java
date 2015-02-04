package vn.co.taxinet.bean.feeagent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi 
 * bean controller for DaiLy-Home.xhtml
 */
@ManagedBean(name = "feeAgentHomeController", eager = true)
@SessionScoped
public class FeeAgentHomeBean implements Serializable {
	private static final long serialVersionUID = -774991307566016525L;

	public String accountSearchName;// tÃªn Ä‘Äƒng nháº­p cáº§n tÃ¬m
	public String userSearchName;// tÃªn ngÆ°á»�i sá»­ dá»¥ng

	// nhÃ¢n viÃªn thu ngÃ¢n
	public String username;
	public String userID;
	
	public List<TaxiNetUsers> listUser;
	
	public TaxiNetUsers selectedUser;
	@ManagedProperty(value="#{riderBO}")
	private AgentBO agentBO;
	
	/**
	 * khá»Ÿi táº¡o ngay khi load trang
	 */
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			setAccountSearchName("");
			setUsername("");
			listUser = new ArrayList<TaxiNetUsers>();
			selectedUser = new TaxiNetUsers();
		}
	}

	/**
	 * event cho button TÃ¬m kiáº¿m
	 */
	public void doSearchAllUser() {
		//tráº£ vá»� 1 danh sÃ¡ch ngÆ°á»�i dÃ¹ng theo Ä‘iá»�u kiá»‡n tÃ¬m kiáº¿m
		listUser = agentBO.listAllUsers(userSearchName, accountSearchName);
		if (listUser == null ) {
			listUser = new ArrayList<TaxiNetUsers>();
		}
	}

	/**
	 * event cho button Náº¡p tiá»�n
	 */
	public void doChargeFeeForSelectedUser() {

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<TaxiNetUsers> getListUser() {
		return listUser;
	}

	public void setListUser(List<TaxiNetUsers> listUser) {
		this.listUser = listUser;
	}

	public TaxiNetUsers getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(TaxiNetUsers selectedUser) {
		this.selectedUser = selectedUser;
	}

	public AgentBO getAgentBO() {
		return agentBO;
	}

	public void setAgentBO(AgentBO agentBO) {
		this.agentBO = agentBO;
	}
	
}
