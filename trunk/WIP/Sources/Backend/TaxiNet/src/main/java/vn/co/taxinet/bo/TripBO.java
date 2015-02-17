package vn.co.taxinet.bo;

import vn.co.taxinet.common.exception.TNException;

public interface TripBO {
	/**
	 * @author Hieu-Gie
	 * 
	 * @param riderId
	 * @param driverId
	 * @param longitude
	 * @param latitude
	 * @return
	 * @throws TNException
	 */
	public String createTrip(String riderId, String driverId, String longitude,
			String latitude) throws TNException;

	/**
	 * @author Hieu-Gie
	 * 
	 * @param requestId
	 * @param userId
	 * @param status
	 * @return
	 * @throws TNException
	 */
	public String updateTrip(String requestId, String userId, String status) throws TNException;
}
