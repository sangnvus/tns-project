package vn.co.taxinet.orm;

// Generated Jan 23, 2015 10:11:00 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Document generated by hbm2java
 */
public class Document implements java.io.Serializable {

	private int documentId;
	private String fileName;
	private String link;
	private String expiredMonth;
	private String expiredYear;
	private String createdBy;
	private Date createDate;
	private String lastModifiedBy;
	private Date lastModifyDate;
	private Set<Company> companiesForBusinessRegistration = new HashSet<Company>(
			0);
	private Set<Company> companiesForOperatingLisence = new HashSet<Company>(0);
	private Set<Driver> driversForDriverLisense = new HashSet<Driver>(0);
	private Set<Driver> driversForDriverLisense_1 = new HashSet<Driver>(0);
	private Set<Driver> driversForDriveExperience = new HashSet<Driver>(0);
	private Set<Driver> driversForDriveExperience_1 = new HashSet<Driver>(0);
	private Set<Company> companiesForOperatingLisence_1 = new HashSet<Company>(
			0);
	private Set<Company> companiesForBusinessRegistration_1 = new HashSet<Company>(
			0);

	public Document() {
	}

	public Document(String fileName, String link, String expiredMonth,
			String expiredYear, String createdBy, Date createDate,
			String lastModifiedBy, Date lastModifyDate) {
		this.fileName = fileName;
		this.link = link;
		this.expiredMonth = expiredMonth;
		this.expiredYear = expiredYear;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
	}

	public Document(String fileName, String link, String expiredMonth,
			String expiredYear, String createdBy, Date createDate,
			String lastModifiedBy, Date lastModifyDate,
			Set<Company> companiesForBusinessRegistration,
			Set<Company> companiesForOperatingLisence,
			Set<Driver> driversForDriverLisense,
			Set<Driver> driversForDriverLisense_1,
			Set<Driver> driversForDriveExperience,
			Set<Driver> driversForDriveExperience_1,
			Set<Company> companiesForOperatingLisence_1,
			Set<Company> companiesForBusinessRegistration_1) {
		this.fileName = fileName;
		this.link = link;
		this.expiredMonth = expiredMonth;
		this.expiredYear = expiredYear;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifyDate = lastModifyDate;
		this.companiesForBusinessRegistration = companiesForBusinessRegistration;
		this.companiesForOperatingLisence = companiesForOperatingLisence;
		this.driversForDriverLisense = driversForDriverLisense;
		this.driversForDriverLisense_1 = driversForDriverLisense_1;
		this.driversForDriveExperience = driversForDriveExperience;
		this.driversForDriveExperience_1 = driversForDriveExperience_1;
		this.companiesForOperatingLisence_1 = companiesForOperatingLisence_1;
		this.companiesForBusinessRegistration_1 = companiesForBusinessRegistration_1;
	}

	public int getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getExpiredMonth() {
		return this.expiredMonth;
	}

	public void setExpiredMonth(String expiredMonth) {
		this.expiredMonth = expiredMonth;
	}

	public String getExpiredYear() {
		return this.expiredYear;
	}

	public void setExpiredYear(String expiredYear) {
		this.expiredYear = expiredYear;
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

	public Set<Company> getCompaniesForBusinessRegistration() {
		return this.companiesForBusinessRegistration;
	}

	public void setCompaniesForBusinessRegistration(
			Set<Company> companiesForBusinessRegistration) {
		this.companiesForBusinessRegistration = companiesForBusinessRegistration;
	}

	public Set<Company> getCompaniesForOperatingLisence() {
		return this.companiesForOperatingLisence;
	}

	public void setCompaniesForOperatingLisence(
			Set<Company> companiesForOperatingLisence) {
		this.companiesForOperatingLisence = companiesForOperatingLisence;
	}

	public Set<Driver> getDriversForDriverLisense() {
		return this.driversForDriverLisense;
	}

	public void setDriversForDriverLisense(Set<Driver> driversForDriverLisense) {
		this.driversForDriverLisense = driversForDriverLisense;
	}

	public Set<Driver> getDriversForDriverLisense_1() {
		return this.driversForDriverLisense_1;
	}

	public void setDriversForDriverLisense_1(
			Set<Driver> driversForDriverLisense_1) {
		this.driversForDriverLisense_1 = driversForDriverLisense_1;
	}

	public Set<Driver> getDriversForDriveExperience() {
		return this.driversForDriveExperience;
	}

	public void setDriversForDriveExperience(
			Set<Driver> driversForDriveExperience) {
		this.driversForDriveExperience = driversForDriveExperience;
	}

	public Set<Driver> getDriversForDriveExperience_1() {
		return this.driversForDriveExperience_1;
	}

	public void setDriversForDriveExperience_1(
			Set<Driver> driversForDriveExperience_1) {
		this.driversForDriveExperience_1 = driversForDriveExperience_1;
	}

	public Set<Company> getCompaniesForOperatingLisence_1() {
		return this.companiesForOperatingLisence_1;
	}

	public void setCompaniesForOperatingLisence_1(
			Set<Company> companiesForOperatingLisence_1) {
		this.companiesForOperatingLisence_1 = companiesForOperatingLisence_1;
	}

	public Set<Company> getCompaniesForBusinessRegistration_1() {
		return this.companiesForBusinessRegistration_1;
	}

	public void setCompaniesForBusinessRegistration_1(
			Set<Company> companiesForBusinessRegistration_1) {
		this.companiesForBusinessRegistration_1 = companiesForBusinessRegistration_1;
	}

}
