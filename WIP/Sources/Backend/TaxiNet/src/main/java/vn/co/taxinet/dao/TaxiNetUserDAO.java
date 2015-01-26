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

import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author sangnv
 *
 */
public interface TaxiNetUserDAO extends BaseDAO {

	TaxiNetUsers select(String uid);
}
