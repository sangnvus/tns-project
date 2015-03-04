package vn.co.taxinet.dao;

import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.Trip;

public interface TripDAO extends BaseDAO {
	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @return trip
	 */
	public Trip findTripById(String id);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param requestId
	 * @param userId
	 * @param status
	 * @return status
	 */
	public MessageDTO updateTripStatus(String requestId, String userId,
			String status);

}
