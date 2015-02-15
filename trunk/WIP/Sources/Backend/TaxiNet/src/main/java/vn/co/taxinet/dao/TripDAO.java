package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Trip;

public interface TripDAO  extends BaseDAO {
	public Trip findTripById(java.lang.String id);
	public String updateTripStatus(String requestId, String userId, String status);
}
