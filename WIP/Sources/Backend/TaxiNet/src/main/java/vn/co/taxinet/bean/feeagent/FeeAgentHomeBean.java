package vn.co.taxinet.bean.feeagent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author Ecchi 
 * bean controller for DaiLy-Home.xhtml
 */
@ManagedBean(name = "feeAgentHomeController", eager = true)
@SessionScoped
public class FeeAgentHomeBean implements Serializable {
	private static final long serialVersionUID = -774991307566016525L;

	public String accountSearchName;// tên đăng nhập cần tìm
	public String userSearchName;// tên người sử dụng

	// nhân viên thu ngân
	public String username;
	public String userID;

	/**
	 * khởi tạo ngay khi load trang
	 */
	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			setAccountSearchName("");
			setUsername("");
		}
	}

	/**
	 * event cho button Tìm kiếm
	 */
	public void doSearchAllUser() {

	}

	/**
	 * event cho button Nạp tiền
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

}
