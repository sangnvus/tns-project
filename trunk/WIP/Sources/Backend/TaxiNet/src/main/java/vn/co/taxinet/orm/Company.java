package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Company generated by hbm2java
 */
public class Company implements java.io.Serializable {

	private Integer companyID;
	private Document documentByOperatingLisence;
	private Document documentByBusinessRegistration;
	private Address address;
	private Company company;
	private String name;
	private String businessType;
	private Integer balance;
	private String vatnumber;
	private String mobileNo;
	private String serviceCharge;
	private Integer rate;
	private Integer bankNo;
	private Integer bankID;
	private String currentPromotionCode;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private Set<TaxiNetUsers> taxinetuserses = new HashSet<TaxiNetUsers>(0);
	private Set<TaxiNetUsers> taxinetuserses_1 = new HashSet<TaxiNetUsers>(0);
	private Set<PricePanel> pricePanels = new HashSet<PricePanel>(0);
	private Set<Company> companies = new HashSet<Company>(0);
	private Set<PricePanel> pricepanels_1 = new HashSet<PricePanel>(0);
	private Set<CompanyParkArea> companyParkAreas = new HashSet<CompanyParkArea>(
			0);
	private Set<Company> companies_1 = new HashSet<Company>(0);
	private Set<CompanyParkArea> companyparkareas_1 = new HashSet<CompanyParkArea>(
			0);

	public Company() {
	}

	public Company(Document documentByOperatingLisence,
			Document documentByBusinessRegistration, Address address,
			Company company, String name, String businessType,
			String createdBy, Date createdDate, String lastModifiedBy,
			Date lastModifiedDate) {
		this.documentByOperatingLisence = documentByOperatingLisence;
		this.documentByBusinessRegistration = documentByBusinessRegistration;
		this.address = address;
		this.company = company;
		this.name = name;
		this.businessType = businessType;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Company(Document documentByOperatingLisence,
			Document documentByBusinessRegistration, Address address,
			Company company, String name, String businessType, Integer balance,
			String vatnumber, String mobileNo, String serviceCharge,
			Integer rate, Integer bankNo, Integer bankID,
			String currentPromotionCode, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate,
			Set<TaxiNetUsers> taxinetuserses,
			Set<TaxiNetUsers> taxinetuserses_1, Set<PricePanel> pricePanels,
			Set<Company> companies, Set<PricePanel> pricepanels_1,
			Set<CompanyParkArea> companyParkAreas, Set<Company> companies_1,
			Set<CompanyParkArea> companyparkareas_1) {
		this.documentByOperatingLisence = documentByOperatingLisence;
		this.documentByBusinessRegistration = documentByBusinessRegistration;
		this.address = address;
		this.company = company;
		this.name = name;
		this.businessType = businessType;
		this.balance = balance;
		this.vatnumber = vatnumber;
		this.mobileNo = mobileNo;
		this.serviceCharge = serviceCharge;
		this.rate = rate;
		this.bankNo = bankNo;
		this.bankID = bankID;
		this.currentPromotionCode = currentPromotionCode;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.taxinetuserses = taxinetuserses;
		this.taxinetuserses_1 = taxinetuserses_1;
		this.pricePanels = pricePanels;
		this.companies = companies;
		this.pricepanels_1 = pricepanels_1;
		this.companyParkAreas = companyParkAreas;
		this.companies_1 = companies_1;
		this.companyparkareas_1 = companyparkareas_1;
	}

	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public Document getDocumentByOperatingLisence() {
		return this.documentByOperatingLisence;
	}

	public void setDocumentByOperatingLisence(
			Document documentByOperatingLisence) {
		this.documentByOperatingLisence = documentByOperatingLisence;
	}

	public Document getDocumentByBusinessRegistration() {
		return this.documentByBusinessRegistration;
	}

	public void setDocumentByBusinessRegistration(
			Document documentByBusinessRegistration) {
		this.documentByBusinessRegistration = documentByBusinessRegistration;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getBalance() {
		return this.balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getVatnumber() {
		return this.vatnumber;
	}

	public void setVatnumber(String vatnumber) {
		this.vatnumber = vatnumber;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getBankID() {
		return this.bankID;
	}

	public void setBankID(Integer bankID) {
		this.bankID = bankID;
	}

	public String getCurrentPromotionCode() {
		return this.currentPromotionCode;
	}

	public void setCurrentPromotionCode(String currentPromotionCode) {
		this.currentPromotionCode = currentPromotionCode;
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

	public Set<PricePanel> getPricepanels() {
		return this.pricePanels;
	}

	public void setPricepanels(Set<PricePanel> pricePanels) {
		this.pricePanels = pricePanels;
	}

	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	public Set<PricePanel> getPricepanels_1() {
		return this.pricepanels_1;
	}

	public void setPricepanels_1(Set<PricePanel> pricepanels_1) {
		this.pricepanels_1 = pricepanels_1;
	}

	public Set<CompanyParkArea> getCompanyparkareas() {
		return this.companyParkAreas;
	}

	public void setCompanyparkareas(Set<CompanyParkArea> companyParkAreas) {
		this.companyParkAreas = companyParkAreas;
	}

	public Set<Company> getCompanies_1() {
		return this.companies_1;
	}

	public void setCompanies_1(Set<Company> companies_1) {
		this.companies_1 = companies_1;
	}

	public Set<CompanyParkArea> getCompanyparkareas_1() {
		return this.companyparkareas_1;
	}

	public void setCompanyparkareas_1(Set<CompanyParkArea> companyparkareas_1) {
		this.companyparkareas_1 = companyparkareas_1;
	}

}
