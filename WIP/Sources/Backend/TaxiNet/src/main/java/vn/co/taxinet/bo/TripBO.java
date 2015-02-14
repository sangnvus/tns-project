package vn.co.taxinet.bo;

import vn.co.taxinet.orm.Trip;

public interface TripBO {
	public Trip createTrip(String riderId, String driverId, String longitude,
			String latitude);

	public String updateTrip(String requestId, String userId, String status);
}
