package vn.co.taxinet.ws;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bean.TNUser;
import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.dto.RiderDTO;

@RestController
@RequestMapping("/riderController")
public class RiderController {
	 private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();
	    RiderDTO riderDTO;
	    @Autowired
	    private AuthenticationBO authenticationBO;
	    @Autowired
	    private RiderBO riderBO;
	    //Add them spring-webmvc and spring-bean to build path of the project
//	    @RequestMapping("/login")
//	    public TNUser login(@RequestParam Map<String,String> requestParams) {
//	    	TNUser userInfo = new TNUser();
//	    	
//	    	userInfo.setUserName(requestParams.get("userName"));
//	    	userInfo.setPassword(requestParams.get("password"));
//	    	if (authenticationBO.authenticate(userInfo)) {
//	    		userInfo = authenticationBO.login(userInfo);
//	    		userInfo.setPassword("0000");	
//	    	}	    	
//	        return userInfo;
//	    }
		@RequestMapping("/login")
		public RiderDTO login(@RequestParam Map<String,String> requestParams){
			String username = requestParams.get("username");
			String password = requestParams.get("password");
			return riderBO.login(username, password);
		}
}
