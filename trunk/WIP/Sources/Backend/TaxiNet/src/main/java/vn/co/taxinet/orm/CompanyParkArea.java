package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * CompanyParkArea generated by hbm2java
 */
public class CompanyParkArea implements java.io.Serializable {

	private Integer parkAreaId;
	private Company company;
	private double latitute;
	private double longtitude;
	private String name;
	private int numberOfCar;
	private String creatBy;
	private Date createdDate;
	private String lastModifyBy;
	private Date lastModifiedDate;

	public CompanyParkArea() {
	}

	public CompanyParkArea(Company company, double latitute, double longtitude,
			String name, int numberOfCar, String creatBy, Date createdDate,
			String lastModifyBy, Date lastModifiedDate) {
		this.company = company;
		this.latitute = latitute;
		this.longtitude = longtitude;
		this.name = name;
		this.numberOfCar = numberOfCar;
		this.creatBy = creatBy;
		this.createdDate = createdDate;
		this.lastModifyBy = lastModifyBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Integer getParkAreaId() {
		return this.parkAreaId;
	}

	public void setParkAreaId(Integer parkAreaId) {
		this.parkAreaId = parkAreaId;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public double getLatitute() {
		return this.latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}

	public double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfCar() {
		return this.numberOfCar;
	}

	public void setNumberOfCar(int numberOfCar) {
		this.numberOfCar = numberOfCar;
	}

	public String getCreatBy() {
		return this.creatBy;
	}

	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifyBy() {
		return this.lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}