/**
 * Copyright(C) 2014, Transport Information Provider Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.dao;

import java.io.Serializable;

import org.hibernate.Session;

public interface BaseDAO extends Serializable {
  
    /**
     * Update an object in Session
     * @param obj
     * @param session
     */
	public void update(Object obj);

	/**
	 * 
	 * @param obj
	 * @param session
	 */
	public void insert(Object obj) ;

	
	/**
	 * 
	 * @param obj
	 * @param session
	 */
	public void delete(Object obj) ;

}
