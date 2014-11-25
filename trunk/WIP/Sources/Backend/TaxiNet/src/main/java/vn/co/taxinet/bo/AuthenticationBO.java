/**
 * Copyright(C) 2014, Trading and Service Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.bo;

import vn.co.taxinet.bean.TNUser;

/**
 * Contain all authentication services
 * @author DEV
 *
 */
public interface AuthenticationBO {
	/**
	 * Verify the logged in user 
	 * 	Return true if user name and password are valid
	 *  Otherwise return false
	 * @param tnUser
	 * 	the <code>TNUser</code> object
	 * @return
	 */
	boolean authenticate(TNUser tnUser);
}
