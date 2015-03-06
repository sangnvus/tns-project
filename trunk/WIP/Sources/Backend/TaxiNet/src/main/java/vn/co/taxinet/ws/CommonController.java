package vn.co.taxinet.ws;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bean.TNUser;
import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.bo.DriverBO;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.bo.TaxiNetUserBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.Constants.GroupUser;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.dto.RiderDTO;
import vn.co.taxinet.dto.TaxiNetUserDTO;
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

		TaxiNetUserDTO user = new TaxiNetUserDTO();

		user.setUserName(email);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhone(phone);
		user.setLanguage(language);
		user.setUserGroup(userGroup);
		user.setCountryCode(countryCode);
		if (userGroup == null) {
			return "";
		}
		String result = "1234";
		String userId = "";
		try {
			userId = taxiNetUserBO.register(user);
		} catch (TNException e) {
			e.printStackTrace();
		}

		return userId;
	}

	@RequestMapping(value = "/UpdateRegId", method = RequestMethod.POST)
	@ResponseBody
	public MessageDTO updateRegId(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String id = requestParams.get("id");
		String regId = requestParams.get("regId");
		String groupUser = requestParams.get("groupUser");
		System.out.println(regId);
		MessageDTO result = taxiNetUserBO.updateRegId(id, groupUser, regId);
		return result;

	}
}
