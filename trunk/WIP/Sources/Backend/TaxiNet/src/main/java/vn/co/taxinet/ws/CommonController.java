package vn.co.taxinet.ws;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bean.TNUser;
import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.RiderDTO;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

@RestController
@RequestMapping("/CommonController")

public class CommonController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	RiderDTO riderDTO;
	@Autowired
	private AuthenticationBO authenticationBO;
	@Autowired
	private RiderBO riderBO;
	@Autowired
	private TaxiNetUserBO taxiNetUserBO;
	@Autowired
	private DriverBO driverBO;
	

	@RequestMapping("/register")
	public String register(@RequestParam Map<String, String> requestParams) {
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		String firstName = requestParams.get("firstname");
		String lastName = requestParams.get("lastname");
		String phone = requestParams.get("phone");
		String language = requestParams.get("language");
		String userGroup = requestParams.get("usergroup");
		String countryCode = requestParams.get("countrycode");
		if (userGroup == null) {
			return "";
		}
		String result = "1234";
		String userId = taxiNetUserBO.register(email, password, email,
				userGroup, language, countryCode);
		if (userGroup.equalsIgnoreCase(Constants.GroupUser.RIDER) ) {
			result = riderBO.register(userId, firstName, lastName, phone);
		}
		if (userGroup.equalsIgnoreCase(Constants.GroupUser.DRIVER)) {
			result = driverBO.register(userId, firstName, lastName, phone);
		}

		return result;
	}
}
