package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Trip;

public interface TripDAO extends BaseDAO {
	public Trip findTripById(java.lang.String id);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param requestId
	 * @param userId
	 * @param status
	 * @return
	 */
	public String updateTripStatus(String requestId, String userId,
			String status);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param riderId
	 * @param driverId
	 * @return
	 */
	public String createTrip(String riderId, String driverId);

}
