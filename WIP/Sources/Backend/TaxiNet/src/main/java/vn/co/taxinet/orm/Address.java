package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Address generated by hbm2java
 */
public class Address implements java.io.Serializable {

	private String addressId;
	private City city;
	private Country country;
	private int districtName;
	private Integer addressDetail;
	private Double latitude;
	private Double longitude;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private Set<Rider> ridersForOfficeAddressId = new HashSet<Rider>(0);
	private Set<Rider> ridersForHomeAddressId = new HashSet<Rider>(0);
	private Set<Driver> drivers = new HashSet<Driver>(0);
	private Set<Driver> drivers_1 = new HashSet<Driver>(0);
	private Set<Rider> ridersForHomeAddressId_1 = new HashSet<Rider>(0);
	private Set<Rider> ridersForOfficeAddressId_1 = new HashSet<Rider>(0);
	private Set<Company> companies = new HashSet<Company>(0);
	private Set<Company> companies_1 = new HashSet<Company>(0);

	public Address() {
	}

	public Address(String addressId, City city, Country country,
			int districtName, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate) {
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.districtName = districtName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Address(String addressId, City city, Country country,
			int districtName, Integer addressDetail, Double latitude,
			Double longitude, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate,
			Set<Rider> ridersForOfficeAddressId,
			Set<Rider> ridersForHomeAddressId, Set<Driver> drivers,
			Set<Driver> drivers_1, Set<Rider> ridersForHomeAddressId_1,
			Set<Rider> ridersForOfficeAddressId_1, Set<Company> companies,
			Set<Company> companies_1) {
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.districtName = districtName;
		this.addressDetail = addressDetail;
		this.latitude = latitude;
		this.longitude = longitude;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.ridersForOfficeAddressId = ridersForOfficeAddressId;
		this.ridersForHomeAddressId = ridersForHomeAddressId;
		this.drivers = drivers;
		this.drivers_1 = drivers_1;
		this.ridersForHomeAddressId_1 = ridersForHomeAddressId_1;
		this.ridersForOfficeAddressId_1 = ridersForOfficeAddressId_1;
		this.companies = companies;
		this.companies_1 = companies_1;
	}

	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(int districtName) {
		this.districtName = districtName;
	}

	public Integer getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(Integer addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Set<Rider> getRidersForOfficeAddressId() {
		return this.ridersForOfficeAddressId;
	}

	public void setRidersForOfficeAddressId(Set<Rider> ridersForOfficeAddressId) {
		this.ridersForOfficeAddressId = ridersForOfficeAddressId;
	}

	public Set<Rider> getRidersForHomeAddressId() {
		return this.ridersForHomeAddressId;
	}

	public void setRidersForHomeAddressId(Set<Rider> ridersForHomeAddressId) {
		this.ridersForHomeAddressId = ridersForHomeAddressId;
	}

	public Set<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}

	public Set<Driver> getDrivers_1() {
		return this.drivers_1;
	}

	public void setDrivers_1(Set<Driver> drivers_1) {
		this.drivers_1 = drivers_1;
	}

	public Set<Rider> getRidersForHomeAddressId_1() {
		return this.ridersForHomeAddressId_1;
	}

	public void setRidersForHomeAddressId_1(Set<Rider> ridersForHomeAddressId_1) {
		this.ridersForHomeAddressId_1 = ridersForHomeAddressId_1;
	}

	public Set<Rider> getRidersForOfficeAddressId_1() {
		return this.ridersForOfficeAddressId_1;
	}

	public void setRidersForOfficeAddressId_1(
			Set<Rider> ridersForOfficeAddressId_1) {
		this.ridersForOfficeAddressId_1 = ridersForOfficeAddressId_1;
	}

	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	public Set<Company> getCompanies_1() {
		return this.companies_1;
	}

	public void setCompanies_1(Set<Company> companies_1) {
		this.companies_1 = companies_1;
	}

}