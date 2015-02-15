package vn.co.taxinet.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;

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

	@RequestMapping(value = "/getNearDriver", method = RequestMethod.GET)
	public List<DriverDTO> getListDriver(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String longitude = requestParams.get("longitude");
		String latitude = requestParams.get("latitude");
		List<DriverDTO> listDriverDTO = driverBO.getListDriver(longitude,
				latitude);

		return listDriverDTO;
	}

	@RequestMapping(value = "/CreateTrip", method = RequestMethod.GET)
	public String createTrip(@RequestParam Map<String, String> requestParams)
			throws TNException {
		String riderId = requestParams.get("riderId");
		String driverId = requestParams.get("driverId");
		String longitude = requestParams.get("longitude");
		String latitude = requestParams.get("latitude");

		return tripBO.createTrip(riderId, driverId, longitude, latitude);
	}

	@RequestMapping(value = "/UpdateTrip", method = RequestMethod.GET)
	public String updateTrip(@RequestParam Map<String, String> requestParams)
			throws TNException {
		String requestId = requestParams.get("requestId");
		String userId = requestParams.get("userId");
		String status = requestParams.get("status");

		return tripBO.updateTrip(requestId, userId, status);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public DriverDTO login(@RequestParam Map<String, String> requestParams) {
		String username = requestParams.get("username");
		String password = requestParams.get("password");
		return driverBO.login(username, password);
	}

	@RequestMapping(value = "/UpdateCurrentStatus", method = RequestMethod.GET)
	@ResponseBody
	public MessageDTO updateCurrentStatus(
			@RequestParam Map<String, String> requestParams) {
		String driverId = requestParams.get("driverId");
		String longitude = requestParams.get("longitude");
		String latitude = requestParams.get("latitude");
		String status = requestParams.get("status");
		MessageDTO result = driverBO.updateCurrentStatus(driverId, longitude,
				latitude, status);
		return result;

	}

}
