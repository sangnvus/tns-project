package vn.co.taxinet.orm;

// Generated Jan 26, 2015 2:47:29 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Country generated by hbm2java
 */
public class Country implements java.io.Serializable {
	private static final long serialVersionUID = -1104316893344313405L;
	private Integer code;
	private String name;
	private int postalCode;
	private String currency;
	private String internationalCode;
	private String createdBy;
	private Date creatDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Set<TaxiNetUsers> taxinetuserses = new HashSet<TaxiNetUsers>(0);
	private Set<City> cities = new HashSet<City>(0);
	private Set<TaxiNetUsers> taxinetuserses_1 = new HashSet<TaxiNetUsers>(0);
	private Set<City> cities_1 = new HashSet<City>(0);
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<Address> addresses_1 = new HashSet<Address>(0);

	public Country() {
	}

	public Country(String name, int postalCode, String currency,
			String internationalCode, String createdBy, Date creatDate,
			String lastModifiedBy, Date lastModifyDate) {
		this.name = name;
		this.postalCode = postalCode;
		this.currency = currency;
		this.internationalCode = internationalCode;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public Country(String name, int postalCode, String currency,
			String internationalCode, String createdBy, Date creatDate,
			String lastModifiedBy, Date lastModifyDate,
			Set<TaxiNetUsers> taxinetuserses, Set<City> cities,
			Set<TaxiNetUsers> taxinetuserses_1, Set<City> cities_1,
			Set<Address> addresses, Set<Address> addresses_1) {
		this.name = name;
		this.postalCode = postalCode;
		this.currency = currency;
		this.internationalCode = internationalCode;
		this.createdBy = createdBy;
		this.creatDate = creatDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.taxinetuserses = taxinetuserses;
		this.cities = cities;
		this.taxinetuserses_1 = taxinetuserses_1;
		this.cities_1 = cities_1;
		this.addresses = addresses;
		this.addresses_1 = addresses_1;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInternationalCode() {
		return this.internationalCode;
	}

	public void setInternationalCode(String internationalCode) {
		this.internationalCode = internationalCode;
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

	public Set<TaxiNetUsers> getTaxinetuserses() {
		return this.taxinetuserses;
	}

	public void setTaxinetuserses(Set<TaxiNetUsers> taxinetuserses) {
		this.taxinetuserses = taxinetuserses;
	}

	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	public Set<TaxiNetUsers> getTaxinetuserses_1() {
		return this.taxinetuserses_1;
	}

	public void setTaxinetuserses_1(Set<TaxiNetUsers> taxinetuserses_1) {
		this.taxinetuserses_1 = taxinetuserses_1;
	}

	public Set<City> getCities_1() {
		return this.cities_1;
	}

	public void setCities_1(Set<City> cities_1) {
		this.cities_1 = cities_1;
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

}
