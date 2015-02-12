package vn.co.taxinet.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bean.DriverBean;
import vn.co.taxinet.bean.TripBean;
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Trip;
@RestController
@RequestMapping("/DriverController")
public class DriverController {
    @Autowired
    private DriverBO driverBO;
    @Autowired
    private TripBO tripBO;
    
	public DriverBean getDriverInfo(String uid) {		
		return null;
	}
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
	public List<DriverBean> getListDriverInfo(@RequestParam Map<String,String> requestParams) {	
		List<Driver> listDriver = driverBO.listDriver();
		List<DriverBean> listDriverBean = new ArrayList<DriverBean>();
		for(int i = 0; i<listDriver.size();i++){
			DriverBean driverBean = new DriverBean();
			driverBean.setDriverID(listDriver.get(i).getDriverId());
			driverBean.setLattitide(listDriver.get(i).getCurrentstatus().getCurrentLatitude());
			driverBean.setLongtitude(listDriver.get(i).getCurrentstatus().getCurrentLongtitude());
			listDriverBean.add(driverBean);
		}
		return listDriverBean;
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
		String requestId = requestParams.get("riderId");
		String userId = requestParams.get("driverId");
		String status = requestParams.get("status");
		
		
		String result = tripBO.updateTrip(requestId, userId, status);
		
		return result;
	}
	
}
