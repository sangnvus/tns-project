package vn.co.taxinet.bo;

import vn.co.taxinet.common.exception.TNException;

public interface TripBO {
	public String createTrip(String riderId, String driverId, String longitude,
			String latitude) throws TNException;

	public String updateTrip(String requestId, String userId, String status) throws TNException;
}
