package vn.co.taxinet.bo;

import vn.co.taxinet.orm.Trip;

public interface TripBO {
	Trip createTrip(String riderId, String driverId);
	public String updateTrip(String requestId, String userId, String status);
}
