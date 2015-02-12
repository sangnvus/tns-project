package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.orm.Driver;

public interface DriverBO {
	public Driver register(Driver obj);
	public List<DriverDTO> listDriver();
	public String createTrip(String riderId, String driverId);
	
}
