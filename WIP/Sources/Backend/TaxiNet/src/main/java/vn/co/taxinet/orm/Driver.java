package vn.co.taxinet.orm;

// Generated Jan 26, 2015 2:47:29 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Driver generated by hbm2java
 */
public class Driver implements java.io.Serializable {

	private String driverId;
	private TaxiNetUsers taxinetusers;
	private Document documentByDriverLisense;
	private Vehicle vehicle;
	private Address address;
	private Document documentByDriveExperience;
	private String firstName;
	private String lastName;
	private Integer mobileNo;
	private Integer invitationCode;
	private String createdBy;
	private Date createDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Currentstatus currentstatus;
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);

	public Driver() {
	}

	public Driver(TaxiNetUsers taxinetusers, Document documentByDriverLisense,
			Address address, Document documentByDriveExperience,
			String createdBy, Date createDate, String lastModifiedBy,
			Date lastModifyDate) {
		this.taxinetusers = taxinetusers;
		this.documentByDriverLisense = documentByDriverLisense;
		this.address = address;
		this.documentByDriveExperience = documentByDriveExperience;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public Driver(TaxiNetUsers taxinetusers, Document documentByDriverLisense,
			Vehicle vehicle, Address address,
			Document documentByDriveExperience, String firstName,
			String lastName, Integer mobileNo, Integer invitationCode,
			String createdBy, Date createDate, String lastModifiedBy,
			Date lastModifyDate, Currentstatus currentstatus, Set<Trip> trips,
			Set<Trip> trips_1) {
		this.taxinetusers = taxinetusers;
		this.documentByDriverLisense = documentByDriverLisense;
		this.vehicle = vehicle;
		this.address = address;
		this.documentByDriveExperience = documentByDriveExperience;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.invitationCode = invitationCode;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.currentstatus = currentstatus;
		this.trips = trips;
		this.currentstatus = currentstatus;
		this.trips_1 = trips_1;
	}

	public String getDriverId() {
		return this.driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public TaxiNetUsers getTaxinetusers() {
		return this.taxinetusers;
	}

	public void setTaxinetusers(TaxiNetUsers taxinetusers) {
		this.taxinetusers = taxinetusers;
	}

	public Document getDocumentByDriverLisense() {
		return this.documentByDriverLisense;
	}

	public void setDocumentByDriverLisense(Document documentByDriverLisense) {
		this.documentByDriverLisense = documentByDriverLisense;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Document getDocumentByDriveExperience() {
		return this.documentByDriveExperience;
	}

	public void setDocumentByDriveExperience(Document documentByDriveExperience) {
		this.documentByDriveExperience = documentByDriveExperience;
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

	public Integer getInvitationCode() {
		return this.invitationCode;
	}

	public void setInvitationCode(Integer invitationCode) {
		this.invitationCode = invitationCode;
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

	public Currentstatus getCurrentstatus() {
		return this.currentstatus;
	}

	public void setCurrentstatus(Currentstatus currentstatus) {
		this.currentstatus = currentstatus;
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