package vn.co.taxinet.dao;

import vn.co.taxinet.orm.CurrentStatus;
import vn.co.taxinet.dto.MessageDTO;


public interface CurrentStatusDAO extends BaseDAO {

	public CurrentStatus findById(java.lang.String id);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @param longitude
	 * @param latitude
	 * @param status
	 * @return
	 */
	MessageDTO updateCurrentStatus(String id, double longitude,
			double latitude, String status);

}
