package vn.co.taxinet.ws;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.MessageDTO;

@RestController
@RequestMapping("/TripController")
public class TripController {
	@Autowired
	private TripBO tripBO;

	public TripBO getTripBO() {
		return tripBO;
	}

	public void setTripBO(TripBO tripBO) {
		this.tripBO = tripBO;
	}

	@RequestMapping(value = "/CreateTrip", method = RequestMethod.GET)
	public MessageDTO createTrip(@RequestParam Map<String, String> requestParams)
			throws TNException {
		String riderId = requestParams.get("riderId");
		String driverId = requestParams.get("driverId");
		String longitude = requestParams.get("longitude");
		String latitude = requestParams.get("latitude");

		return tripBO.createTrip(riderId, driverId, longitude, latitude);
	}

	@RequestMapping(value = "/UpdateTrip", method = RequestMethod.GET)
	public MessageDTO updateTrip(@RequestParam Map<String, String> requestParams)
			throws TNException {
		String requestId = requestParams.get("requestId");
		String userId = requestParams.get("userId");
		String status = requestParams.get("status");

		return tripBO.updateTrip(requestId, userId, status);
	}

}
