package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TaxiNetUsers generated by hbm2java
 */
public class TaxiNetUsers implements java.io.Serializable {

	private String userId;
	private UserGroup userGroup;
	private Language language;
	private Company company;
	private Country country;
	private String username;
	private String password;
	private String email;
	private Integer cityId;
	private String status;
	private String image;
	private String postalCode;
	private Date deactivatedDate;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private Agent agent;
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Payment> payments_1 = new HashSet<Payment>(0);
	private Set<AccountTransaction> accountTransactions = new HashSet<AccountTransaction>(
			0);
	private Driver driver;
	private Rider rider;
	private Set<AccountTransaction> accounttransactions_1 = new HashSet<AccountTransaction>(
			0);

	public TaxiNetUsers() {
	}

	public TaxiNetUsers(String userId, UserGroup userGroup, Language language,
			Country country, String username, String status, String createdBy,
			Date createdDate, String lastModifiedBy,
			Date lastModifiedDate) {
		this.userId = userId;
		this.userGroup = userGroup;
		this.language = language;
		this.country = country;
		this.username = username;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public TaxiNetUsers(String userId, UserGroup userGroup, Language language,
			Company company, Country country, String username, String password,
			String email, Integer cityId, String status, String image,
			String postalCode, Date deactivatedDate, String createdBy,
			Date createdDate, String lastModifiedBy, Date lastModifiedDate,
			Agent agent, Set<Payment> payments,
			Set<Payment> payments_1,
			Set<AccountTransaction> accountTransactions, Driver driver,
			Rider rider,
			Set<AccountTransaction> accounttransactions_1) {
		this.userId = userId;
		this.userGroup = userGroup;
		this.language = language;
		this.company = company;
		this.country = country;
		this.username = username;
		this.password = password;
		this.email = email;
		this.cityId = cityId;
		this.status = status;
		this.image = image;
		this.postalCode = postalCode;
		this.deactivatedDate = deactivatedDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.agent = agent;
		this.payments = payments;
		this.agent = agent;
		this.payments_1 = payments_1;
		this.accountTransactions = accountTransactions;
		this.driver = driver;
		this.rider = rider;
		this.driver = driver;
		this.rider = rider;
		this.accounttransactions_1 = accounttransactions_1;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserGroup getUsergroup() {
		return this.userGroup;
	}

	public void setUsergroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Set<AccountTransaction> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(Set<AccountTransaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	public Date getDeactivatedDate() {
		return this.deactivatedDate;
	}

	public void setDeactivatedDate(Date deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
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

	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Set<Payment> getPayments_1() {
		return this.payments_1;
	}

	public void setPayments_1(Set<Payment> payments_1) {
		this.payments_1 = payments_1;
	}

	public Set<AccountTransaction> getAccounttransactions() {
		return this.accountTransactions;
	}

	public void setAccounttransactions(
			Set<AccountTransaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}
	
	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Rider getRider() {
		return this.rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

	public Set<AccountTransaction> getAccounttransactions_1() {
		return this.accounttransactions_1;
	}

	public void setAccounttransactions_1(
			Set<AccountTransaction> accounttransactions_1) {
		this.accounttransactions_1 = accounttransactions_1;
	}

}
