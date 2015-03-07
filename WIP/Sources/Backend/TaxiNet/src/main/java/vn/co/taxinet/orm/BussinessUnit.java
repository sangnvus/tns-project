package vn.co.taxinet.orm;

// Generated Mar 7, 2015 10:20:43 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BussinessUnit generated by hbm2java
 */
public class BussinessUnit implements java.io.Serializable {

	private String businessUnitId;
	private City city;
	private Country country;
	private BussinessUnit bussinessUnit;
	private String businessUnitName;
	private String status;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private Set<Staff> staffs = new HashSet<Staff>(0);
	private Set<BussinessUnit> bussinessUnits = new HashSet<BussinessUnit>(0);
	private Set<Staff> staffs_1 = new HashSet<Staff>(0);
	private Set<BussinessUnit> bussinessUnits_1 = new HashSet<BussinessUnit>(0);

	public BussinessUnit() {
	}

	public BussinessUnit(String businessUnitId, Country country, String status,
			String createdBy, Date createdDate, String lastModifiedBy,
			Date lastModifiedDate) {
		this.businessUnitId = businessUnitId;
		this.country = country;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public BussinessUnit(String businessUnitId, City city, Country country,
			BussinessUnit bussinessUnit, String businessUnitName,
			String status, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate, Set<Staff> staffs,
			Set<BussinessUnit> bussinessUnits, Set<Staff> staffs_1,
			Set<BussinessUnit> bussinessUnits_1) {
		this.businessUnitId = businessUnitId;
		this.city = city;
		this.country = country;
		this.bussinessUnit = bussinessUnit;
		this.businessUnitName = businessUnitName;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.staffs = staffs;
		this.bussinessUnits = bussinessUnits;
		this.staffs_1 = staffs_1;
		this.bussinessUnits_1 = bussinessUnits_1;
	}

	public String getBusinessUnitId() {
		return this.businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
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

	public BussinessUnit getBussinessUnit() {
		return this.bussinessUnit;
	}

	public void setBussinessUnit(BussinessUnit bussinessUnit) {
		this.bussinessUnit = bussinessUnit;
	}

	public String getBusinessUnitName() {
		return this.businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

	public Set<BussinessUnit> getBussinessUnits() {
		return this.bussinessUnits;
	}

	public void setBussinessUnits(Set<BussinessUnit> bussinessUnits) {
		this.bussinessUnits = bussinessUnits;
	}

	public Set<Staff> getStaffs_1() {
		return this.staffs_1;
	}

	public void setStaffs_1(Set<Staff> staffs_1) {
		this.staffs_1 = staffs_1;
	}

	public Set<BussinessUnit> getBussinessUnits_1() {
		return this.bussinessUnits_1;
	}

	public void setBussinessUnits_1(Set<BussinessUnit> bussinessUnits_1) {
		this.bussinessUnits_1 = bussinessUnits_1;
	}

}
