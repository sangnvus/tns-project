package vn.co.taxinet.orm;

// Generated Jan 26, 2015 2:47:29 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Language generated by hbm2java
 */
public class Language implements java.io.Serializable {

	private Integer languageCode;
	private String language;
	private String createdBy;
	private Date creatDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Set<Cityname> citynames = new HashSet<Cityname>(0);
	private Set<Cityname> citynames_1 = new HashSet<Cityname>(0);
	private Set<TaxiNetUsers> taxinetuserses = new HashSet<TaxiNetUsers>(0);
	private Set<TaxiNetUsers> taxinetuserses_1 = new HashSet<TaxiNetUsers>(0);

	public Language() {
	}

	public Language(String language, String createdBy, Date creatDate,
			String lastModifiedBy, Date lastModifyDate) {
		this.language = language;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public Language(String language, String createdBy, Date creatDate,
			String lastModifiedBy, Date lastModifyDate,
			Set<Cityname> citynames, Set<Cityname> citynames_1,
			Set<TaxiNetUsers> taxinetuserses, Set<TaxiNetUsers> taxinetuserses_1) {
		this.language = language;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.citynames = citynames;
		this.citynames_1 = citynames_1;
		this.taxinetuserses = taxinetuserses;
		this.taxinetuserses_1 = taxinetuserses_1;
	}

	public Integer getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(Integer languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public Set<Cityname> getCitynames() {
		return this.citynames;
	}

	public void setCitynames(Set<Cityname> citynames) {
		this.citynames = citynames;
	}

	public Set<Cityname> getCitynames_1() {
		return this.citynames_1;
	}

	public void setCitynames_1(Set<Cityname> citynames_1) {
		this.citynames_1 = citynames_1;
	}

	public Set<TaxiNetUsers> getTaxinetuserses() {
		return this.taxinetuserses;
	}

	public void setTaxinetuserses(Set<TaxiNetUsers> taxinetuserses) {
		this.taxinetuserses = taxinetuserses;
	}

	public Set<TaxiNetUsers> getTaxinetuserses_1() {
		return this.taxinetuserses_1;
	}

	public void setTaxinetuserses_1(Set<TaxiNetUsers> taxinetuserses_1) {
		this.taxinetuserses_1 = taxinetuserses_1;
	}

}