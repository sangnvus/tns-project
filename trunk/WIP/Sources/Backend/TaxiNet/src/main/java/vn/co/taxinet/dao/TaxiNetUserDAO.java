/**
 * Copyright(C) 2014, Transport Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author sangnv
 *
 */
public interface TaxiNetUserDAO extends BaseDAO {

	TaxiNetUsers select(String uid);
	
	/**
	 * @param email
	 * @param password
	 * @return user
	 */
	List<TaxiNetUsers> loginAuth(String email);

	/**
	 * @author Ecchi function register for ALL USER
	 * @param user
	 * @return boolean
	 */
	boolean registerTaxiNetUser(TaxiNetUsers user);

	/**
	 * @author Ecchi function register for RIDER
	 * @param rider
	 * @return boolean
	 */
	boolean registerRider(Rider rider);

	/**
	 * @author Ecchi
	 * @return all users ( rider, driver, taxigroup )
	 */
	List<TaxiNetUsers> listAllUsers(String username, String email);
	List<TaxiNetUsers> paginationList(int page, int numberOfElement);
}
