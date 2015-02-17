package vn.co.taxinet.dao;

import vn.co.taxinet.dto.MessageDTO;

public interface CurrentStatusDAO extends BaseDAO {
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
