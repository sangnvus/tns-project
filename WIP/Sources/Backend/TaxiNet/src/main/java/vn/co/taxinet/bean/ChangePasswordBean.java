package vn.co.taxinet.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi
 * @category change password for all user
 *
 */
@ManagedBean(name = "changePassController", eager = true)
@SessionScoped
public class ChangePasswordBean implements Serializable {
	private static final long serialVersionUID = 7459395432108574345L;

	String Password;
	String UserID;
	String Username;
	String newPass;
	String renewPass;

	@ManagedProperty(value = "#{taxiNetUserBO}")
	private TaxiNetUserBO taxiNetUserBO;

	@PostConstruct
	public void initData() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			UserID = session.getAttribute("UserID").toString();
			Username = session.getAttribute("Username").toString();
			Password = session.getAttribute("Password").toString();
			newPass = "";
			renewPass = "";
		} catch (Exception ex) {
			try {
				// TODO if UserID/ Username / Password null -> redirect to login
				// page
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/xhtml/Login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return null
	 * @author Ecchi change password
	 */
	public String changePassButton() {
		if (newPass == null || ("").equalsIgnoreCase(newPass)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"You must enter new pasword"));
			return null;
		} else if (renewPass == null || ("").equalsIgnoreCase(renewPass)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"You must confirm new password"));
			return null;
		} else if (!(renewPass).equalsIgnoreCase(newPass)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
							"New password and confirm password are different"));
			return null;
		} else {
			String result = taxiNetUserBO.changePass(UserID, newPass);
			if (result != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage( "Success",
								"Your password was updated"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"Something was crushed"));
			} 
			return null;
		}
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

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

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getRenewPass() {
		return renewPass;
	}

	public void setRenewPass(String renewPass) {
		this.renewPass = renewPass;
	}
}
