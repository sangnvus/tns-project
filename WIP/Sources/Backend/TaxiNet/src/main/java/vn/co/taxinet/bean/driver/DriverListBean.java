package vn.co.taxinet.bean.driver;

import java.io.Serializable;

import org.primefaces.model.LazyDataModel;

import vn.co.taxinet.orm.Driver;

public class DriverListBean implements Serializable{
	private static final long serialVersionUID = -2980047468030176768L;
	
	//params for user informations (Session/cookies using0
	public String UserID;
	public String Password;
	public String Username;
	
	public LazyDataModel<Driver> driverList;

}
