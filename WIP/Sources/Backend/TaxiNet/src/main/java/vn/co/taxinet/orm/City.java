package vn.co.taxinet.orm;

// Generated Jan 23, 2015 10:11:00 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * City generated by hbm2java
 */
public class City implements java.io.Serializable {

	private int cityId;
	private Country country;
	private double northBoundary;
	private double southBoundary;
	private double westBoundary;
	private double eastBoundary;
	private String createdBy;
	private Date creatDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<Address> addresses_1 = new HashSet<Address>(0);
	private Set<Cityname> citynames = new HashSet<Cityname>(0);
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);
	private Set<Cityname> citynames_1 = new HashSet<Cityname>(0);

	public City() {
	}

	public City(int cityId, Country country, double northBoundary,
			double southBoundary, double westBoundary, double eastBoundary,
			String createdBy, Date creatDate, String lastModifiedBy,
			Date lastModifyDate) {
		this.cityId = cityId;
		this.country = country;
		this.northBoundary = northBoundary;
		this.southBoundary = southBoundary;
		this.westBoundary = westBoundary;
		this.eastBoundary = eastBoundary;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public City(int cityId, Country country, double northBoundary,
			double southBoundary, double westBoundary, double eastBoundary,
			String createdBy, Date creatDate, String lastModifiedBy,
			Date lastModifyDate, Set<Address> addresses,
			Set<Address> addresses_1, Set<Cityname> citynames, Set<Trip> trips,
			Set<Trip> trips_1, Set<Cityname> citynames_1) {
		this.cityId = cityId;
		this.country = country;
		this.northBoundary = northBoundary;
		this.southBoundary = southBoundary;
		this.westBoundary = westBoundary;
		this.eastBoundary = eastBoundary;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.addresses = addresses;
		this.addresses_1 = addresses_1;
		this.citynames = citynames;
		this.trips = trips;
		this.trips_1 = trips_1;
		this.citynames_1 = citynames_1;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public double getNorthBoundary() {
		return this.northBoundary;
	}

	public void setNorthBoundary(double northBoundary) {
		this.northBoundary = northBoundary;
	}

	public double getSouthBoundary() {
		return this.southBoundary;
	}

	public void setSouthBoundary(double southBoundary) {
		this.southBoundary = southBoundary;
	}

	public double getWestBoundary() {
		return this.westBoundary;
	}

	public void setWestBoundary(double westBoundary) {
		this.westBoundary = westBoundary;
	}

	public double getEastBoundary() {
		return this.eastBoundary;
	}

	public void setEastBoundary(double eastBoundary) {
		this.eastBoundary = eastBoundary;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatDate() {
		return this.creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
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

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Address> getAddresses_1() {
		return this.addresses_1;
	}

	public void setAddresses_1(Set<Address> addresses_1) {
		this.addresses_1 = addresses_1;
	}

	public Set<Cityname> getCitynames() {
		return this.citynames;
	}

	public void setCitynames(Set<Cityname> citynames) {
		this.citynames = citynames;
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

	public Set<Cityname> getCitynames_1() {
		return this.citynames_1;
	}

	public void setCitynames_1(Set<Cityname> citynames_1) {
		this.citynames_1 = citynames_1;
	}

}
