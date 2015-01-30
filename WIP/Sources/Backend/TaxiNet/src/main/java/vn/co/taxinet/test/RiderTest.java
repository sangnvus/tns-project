package vn.co.taxinet.test;

import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

public class RiderTest {

	public static void createRider( RiderBO riderBO) {
		try {
		Rider rider = new Rider();
		rider.setFirstName("Thanh");
		rider.setLastName("Nguyen");
		rider.setMobileNo(10);
		TaxiNetUsers user = new TaxiNetUsers();
		user.setPassword("0001");
		user.setUsername("thanh@fpt.edu.vn");
		user.setEmail("thanh@fpt.edu.vn");
		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
