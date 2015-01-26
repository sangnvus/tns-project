package vn.co.taxinet.orm;

// Generated Jan 26, 2015 2:47:29 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Rider generated by hbm2java
 */
public class Rider implements java.io.Serializable {

	private String riderId;
	private Address addressByHomeAddressId;
	private TaxiNetUsers taxinetusers;
	private Address addressByOfficeAddressId;
	private String firstName;
	private String lastName;
	private Integer mobileNo;
	private String createdBy;
	private Date createDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);

	public Rider() {
	}

	public Rider(Address addressByHomeAddressId, TaxiNetUsers taxinetusers,
			Address addressByOfficeAddressId, String createdBy,
			Date createDate, String lastModifiedBy, Date lastModifyDate) {
		this.addressByHomeAddressId = addressByHomeAddressId;
		this.taxinetusers = taxinetusers;
		this.addressByOfficeAddressId = addressByOfficeAddressId;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public Rider(Address addressByHomeAddressId, TaxiNetUsers taxinetusers,
			Address addressByOfficeAddressId, String firstName,
			String lastName, Integer mobileNo, String createdBy,
			Date createDate, String lastModifiedBy, Date lastModifyDate,
			Set<Trip> trips, Set<Trip> trips_1) {
		this.addressByHomeAddressId = addressByHomeAddressId;
		this.taxinetusers = taxinetusers;
		this.addressByOfficeAddressId = addressByOfficeAddressId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.trips = trips;
		this.trips_1 = trips_1;
	}

	public String getRiderId() {
		return this.riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	public Address getAddressByHomeAddressId() {
		return this.addressByHomeAddressId;
	}

	public void setAddressByHomeAddressId(Address addressByHomeAddressId) {
		this.addressByHomeAddressId = addressByHomeAddressId;
	}

	public TaxiNetUsers getTaxinetusers() {
		return this.taxinetusers;
	}

	public void setTaxinetusers(TaxiNetUsers taxinetusers) {
		this.taxinetusers = taxinetusers;
	}

	public Address getAddressByOfficeAddressId() {
		return this.addressByOfficeAddressId;
	}

	public void setAddressByOfficeAddressId(Address addressByOfficeAddressId) {
		this.addressByOfficeAddressId = addressByOfficeAddressId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Set<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	public Set<Trip> getTrips_1() {
		return this.trips_1;
	}

	public void setTrips_1(Set<Trip> trips_1) {
		this.trips_1 = trips_1;
	}

}