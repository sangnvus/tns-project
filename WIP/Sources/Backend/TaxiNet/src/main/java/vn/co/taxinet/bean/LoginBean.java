package vn.co.taxinet.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.common.ResourceKeys;

/**
 * 
 * @author DEV
 * 
 */
public class LoginBean extends BaseBean implements ResourceKeys {
	private final static Logger log = LogManager.getLogger(LoginBean.class);
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
			if (authenticationBO.authenticate(userBean)) {
				return ("home");
			} else {
				addMessage(LoginForm.MSG, userName, password);
				return "";
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