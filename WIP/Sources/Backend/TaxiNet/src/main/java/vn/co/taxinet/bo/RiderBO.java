/**
 * Copyright(C) 2014, Transport Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.bo;

import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dto.RiderDTO;
import vn.co.taxinet.orm.Rider;

/**
 * @author Ecchi
 *
 */
public interface RiderBO {
	/**
	 * @author Ecchi
	 * @param rider
	 * @return
	 * @throws TNSException
	 */
	public void register(Rider rider) throws TNSException;

	public void test(Rider rider);

	public Rider findByID(String uid);

	public void updatePassword(String uid, String password);

	public void updateProfile(String uid, String surName, String name,
			String countryCode, String phoneNo, String languageCode,
			String zipCode);
	public RiderDTO login(String username, String password);
	public String register(String riderId, String firstName, String lastName, String mobileNo);
}
