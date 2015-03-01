package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.Driver;

;
public interface DriverDAO extends BaseDAO {
	String createTrip(String riderId, String driverId);

	/**
	 * @author Hieu-Gie
	 * 
	 * @return
	 */
	List<Driver> getNearListDriver();

	/**
	 * @author Ecchi
	 * @param companyID
	 * @return driver and current status
	 */
	public List<Driver> findDriverByCompanyID(String companyID, int pageIndex,
			int pageSize);
	
	/**
	 * @author Ecchi
	 * @param companyID
	 * @return number of driver
	 */
	public List<Driver> countDriverByCompanyID(String companyID);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @return
	 */
	public Driver findDriverById(String id);

	List<Driver> getDriverList();

}
