package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.Driver;

;
public interface DriverDAO extends BaseDAO {
	/**
	 * @author Hieu-Gie
	 * 
	 * @return
	 */
	List<Driver> getNearListDriver();

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @return
	 */
	public Driver findDriverById(String id);

}
