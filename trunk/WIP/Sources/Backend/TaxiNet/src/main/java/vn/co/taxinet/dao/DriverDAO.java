package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.dto.DriverDTO;
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
	 * @author Ecchi
	 * @param companyID
	 * @return driver and current status
	 */
	public List<DriverDTO> findDriverByCompanyID(String companyID, int pageIndex,
			int pageSize);
	
	/**
	 * @author Ecchi
	 * @param companyID
	 * @return number of driver
	 */
	public List<Driver> countDriverByCompanyID(String companyID);
	
	
	/**
	 * @author Ecchi
	 * @category driver list
	 * @param companyID
	 * @param pageIndex
	 * @param pageSize
	 * @return driver list of company
	 */
	public List<DriverDTO> getAllDriver(String companyID, int pageIndex, int pageSize);
	
	/**
	 * @author Ecchi
	 * @category driver list
	 * @param companyID
	 * @return number of company's driver
	 */
	public int countAllDriver(String companyID);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @return
	 */
	public Driver findDriverById(String id);

}
