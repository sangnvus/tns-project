package vn.co.taxinet.bo;

import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.dto.TripDTO;

public interface TripBO {
	/**
	 * @author Hieu-Gie
	 * 
	 * @param riderId
	 * @param driverId
	 * @param paymentMethod
	 * @param paymentMethod
	 * @param paymentMethod2 
	 * @param longitude
	 * @param latitude
	 * @return MessageDTO
	 * @throws TNException
	 */
	public TripDTO createTrip(String riderId, String driverId,
			String startLongitude, String startLlatitude, String stopLongitude,
			String stopLatitude, String estimateCode,String estimateDistance, String paymentMethod)
			throws TNException;

	/**
	 * @author Hieu-Gie
	 * 
	 * @param requestId
	 * @param userId
	 * @param status
	 * @return MessageDTO
	 * @throws TNException
	 */
	public MessageDTO updateTrip(String requestId, String userId, String status)
			throws TNException;

	public MessageDTO completeTrip(String requestId, String driverId,
			String cost) throws TNException;
}
