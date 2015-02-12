package vn.co.taxinet.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Trip;
@RestController
@RequestMapping("/DriverController")
public class DriverController {
    @Autowired
    private DriverBO driverBO;
    @Autowired
    private TripBO tripBO;
    
	public DriverBO getDriverBO() {
		return driverBO;
	}
	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}
	public TripBO getTripBO() {
		return tripBO;
	}
	public void setTripBO(TripBO tripBO) {
		this.tripBO = tripBO;
	}
	@RequestMapping("/getNearDriver")
	public List<DriverDTO> getListDriverInfo(@RequestParam Map<String,String> requestParams) {	
		List<DriverDTO> listDriverDTO = driverBO.listDriver();

		return listDriverDTO;
	}
	@RequestMapping("/requestDriver")
	public String requestDriver(@RequestParam Map<String,String> requestParams){
		String riderId = requestParams.get("riderId");
		String driverId = requestParams.get("driverId");
		
		
		Trip trip = tripBO.createTrip(riderId, driverId);
		
		return trip.getRequestId();
	}
	
	@RequestMapping("/updateTrip")
	public String updateTrip(@RequestParam Map<String,String> requestParams){
		String requestId = requestParams.get("requestId");
		String userId = requestParams.get("userId");
		String status = requestParams.get("status");
		
		
		String result = tripBO.updateTrip(requestId, userId, status);
		
		return result;
	}
	
}
