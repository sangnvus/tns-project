package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * CityName generated by hbm2java
 */
public class CityName implements java.io.Serializable {

	private CityNameID id;
	private City city;
	private Language language;
	private String cityName;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;

	public CityName() {
	}

	public CityName(CityNameID id, City city, Language language,
			String cityName, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate) {
		this.id = id;
		this.city = city;
		this.language = language;
		this.cityName = cityName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public CityNameID getId() {
		return this.id;
	}

	public void setId(CityNameID id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

}