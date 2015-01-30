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
import vn.co.taxinet.orm.Rider;
/**
 */
public interface RiderBO {
	public Rider register(Rider rider) throws TNSException;
}
