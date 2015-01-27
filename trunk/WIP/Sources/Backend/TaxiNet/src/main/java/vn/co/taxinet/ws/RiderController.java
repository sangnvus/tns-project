package vn.co.taxinet.ws;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bean.TNUser;
import vn.co.taxinet.bo.AuthenticationBO;

@RestController
@RequestMapping("/riderController")
public class RiderController {
	 private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();
	    @Autowired
	    private AuthenticationBO authenticationBO;
	    //Add them spring-webmvc and spring-bean to build path of the project
	    @RequestMapping("/login")
	    public TNUser login(@RequestParam(value="userName", defaultValue="") String userName) {
	    	TNUser userInfo = new TNUser();
	    	
	    	userInfo.setUserName(userName);
	    	if (authenticationBO.authenticate(userInfo)) {
	    		userInfo.setPassword("0000");	
	    	}	    	
	        return userInfo;
	    }
}
