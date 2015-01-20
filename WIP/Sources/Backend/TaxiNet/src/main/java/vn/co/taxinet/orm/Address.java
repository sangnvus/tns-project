/**
 * Copyright(C) 2014, Transport Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.orm;


/**
 * @author DEV
 *
 */
public class Address extends BaseEntity {
	private static final long serialVersionUID = 826333374099751687L;
	private String street;
	private String houseNo;
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the houseNo
	 */
	public String getHouseNo() {
		return houseNo;
	}
	/**
	 * @param houseNo the houseNo to set
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}	
}
