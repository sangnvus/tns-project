package vn.co.taxinet.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="TNUser",eager=true)
@RequestScoped
public class TNUser implements Serializable {
	private static final long serialVersionUID = 6467633664262930828L;
	private String userName;
	 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
}
