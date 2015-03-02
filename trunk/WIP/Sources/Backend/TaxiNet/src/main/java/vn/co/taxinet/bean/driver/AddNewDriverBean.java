package vn.co.taxinet.bean.driver;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import vn.co.taxinet.bo.DriverBO;

/**
 * @author Ecchi
 * @category add new driver's controller
 */
@ManagedBean(name = "addNewDriverBean", eager = true)
@SessionScoped
public class AddNewDriverBean implements Serializable {
	private static final long serialVersionUID = 1506603768350639642L;

	String UserID;
	String username;
	String password;

	@ManagedProperty(value = "#{driverBO}")
	private DriverBO driverBO;
	
	
}
