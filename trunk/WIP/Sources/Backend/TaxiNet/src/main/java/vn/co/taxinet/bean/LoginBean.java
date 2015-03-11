package vn.co.taxinet.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.ResourceKeys;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * 
 * @author DEV
 * 
 */
@ManagedBean(name = "loginForm", eager = true)
@SessionScoped
public class LoginBean extends BaseBean implements ResourceKeys {
	private final static Logger log = LogManager.getLogger(LoginBean.class);
	// @ManagedProperty(value = "#{authenticationService}")
	private AuthenticationBO authenticationBO;
	@ManagedProperty(value = "#{param.userName}")
	private String userName;

	@ManagedProperty(value = "#{param.password}")
	private String password;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String logon() {
		TNUser userBean = new TNUser();
		userBean.setUserName(userName);
		userBean.setPassword(password);
		try {
			TaxiNetUsers user = authenticationBO.login(userName,password);
			if (user != null) {
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("UserID", user.getUserId());
				session.setAttribute("Username", user.getUsername());
				session.setAttribute("Password", user.getPassword());
				session.setAttribute("Role", user.getUserGroup().getGroupCode());
				if ((Constants.GroupUser.RIDER)
						.equalsIgnoreCase(user.getUserGroup().getGroupCode())) {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/xhtml/rider/MyTrips.xhtml");
					return null;
				} else if ((Constants.GroupUser.DRIVER).equalsIgnoreCase(user
						.getUserGroup().getGroupCode())) {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/TN/faces/xhtml/driver/Dashboard.xhtml");
					return null;
				} else if ((Constants.GroupUser.AGENT).equalsIgnoreCase(user.getUserGroup().getGroupCode())) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/TN/faces/xhtml/feeagent/AgentHome.xhtml");
					return null;
				} 
				return null;
			} else {
				addMessage(LoginForm.MSG, userName, password);
				return null;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return ("error");
		}
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the authenticateBO
	 */
	public AuthenticationBO getAuthenticationBO() {
		return authenticationBO;
	}

	/**
	 * @param authenticateBO
	 *            the authenticateBO to set
	 */
	public void setAuthenticationBO(AuthenticationBO authenticateBO) {
		this.authenticationBO = authenticateBO;
	}
}
