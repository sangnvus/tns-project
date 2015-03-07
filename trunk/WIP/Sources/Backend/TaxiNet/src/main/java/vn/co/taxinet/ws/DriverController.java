package vn.co.taxinet.ws;

import java.io.UnsupportedEncodingException;
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

	public DriverBO getDriverBO() {
		return driverBO;
	}

	public void setDriverBO(DriverBO driverBO) {
		this.driverBO = driverBO;
	}

	@RequestMapping(value = "/getNearDriver", method = RequestMethod.POST)
	public List<DriverDTO> getListDriver(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String longitude = requestParams.get("longitude");
		String latitude = requestParams.get("latitude");
		List<DriverDTO> listDriverDTO = driverBO.getNearListDriver(longitude,
				latitude);

		return listDriverDTO;
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public DriverDTO login(@RequestParam Map<String, String> requestParams)
			throws TNException {
		String username = requestParams.get("username");
		String password = requestParams.get("password");
		return driverBO.login(username, password);
	}

	@RequestMapping(value = "/UpdateCurrentStatus", method = RequestMethod.POST, produces={"application/json"})
	@ResponseBody
	public MessageDTO updateCurrentStatus(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String driverId = requestParams.get("driverId");
		String longitude = requestParams.get("longitude");
		String latitude = requestParams.get("latitude");
		String status = requestParams.get("status");
		String location = requestParams.get("location");
		System.out.println(location);
		
		MessageDTO result = driverBO.updateCurrentStatus(driverId, longitude,
				latitude, status, location);
		return result;

	}

	@RequestMapping(value = "/UpdateDriver", method = RequestMethod.POST)
	@ResponseBody
	public MessageDTO updateDriver(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String id = requestParams.get("id");
		String firstname = requestParams.get("firstname");
		String lastname = requestParams.get("lastname");
		String phone = requestParams.get("phoneNumber");
		String email = requestParams.get("email");
		String password = requestParams.get("password");

		MessageDTO result = driverBO.updateDriver(id, firstname, lastname,
				phone, email, password);
		return result;

	}
	
	@RequestMapping(value = "/ChangePassword", method = RequestMethod.GET)
	@ResponseBody
	public MessageDTO changePassword(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String id = requestParams.get("id");
		String oldPassword = requestParams.get("oldpassword");
		String newPassword = requestParams.get("newpassword");

		MessageDTO result = driverBO.changePassword(id, oldPassword, newPassword);
		return result;

	}
}
