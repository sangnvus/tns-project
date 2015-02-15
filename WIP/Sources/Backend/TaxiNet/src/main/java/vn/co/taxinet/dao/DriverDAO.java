package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.Driver;;
public interface DriverDAO extends BaseDAO {
	List<Driver> getListDriver();
	String createTrip(String riderId, String driverId);
	public Driver findDriverById(java.lang.String id);

	MessageDTO updateCurrentStatus(String addressId, double _longitude,
			double _latitude, String status);
	
	
}
