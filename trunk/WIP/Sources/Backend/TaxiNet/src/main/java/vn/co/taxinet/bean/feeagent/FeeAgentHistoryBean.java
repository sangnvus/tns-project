package vn.co.taxinet.bean.feeagent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author Ecchi bean controller for DaiLy-LichSuGiaoDich.xhtml
 */
@ManagedBean(name="feeAgentHistoryController",eager=true)
@SessionScoped
public class FeeAgentHistoryBean implements Serializable {
	private static final long serialVersionUID = 9080884222504215941L;

	public String accountSearchName;
	public String userSearchName;
	public Date fromDate;
	public Date toDate;

	public String username;
	public String userID;

	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			accountSearchName = "";
			userSearchName = "";
			fromDate = new Date();
			Calendar cal = Calendar.getInstance();
			toDate = cal.getTime();
		}
	}

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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
