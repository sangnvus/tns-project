package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Payment generated by hbm2java
 */
public class Payment implements java.io.Serializable {

	private Integer paymentID;
	private Bank bank;
	private TaxiNetUsers taxiNetUsers;
	private String cardNo;
	private String expiredYear;
	private String expiredMonth;
	private String cvv;
	private String type;
	private String status;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);

	public Payment() {
	}

	public Payment(Bank bank, TaxiNetUsers taxiNetUsers, String cardNo,
			String createdBy, Date createdDate, String lastModifiedBy,
			Date lastModifiedDate) {
		this.bank = bank;
		this.taxiNetUsers = taxiNetUsers;
		this.cardNo = cardNo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Payment(Bank bank, TaxiNetUsers taxiNetUsers, String cardNo,
			String expiredYear, String expiredMonth, String cvv, String type,
			String status, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate, Set<Trip> trips,
			Set<Trip> trips_1) {
		this.bank = bank;
		this.taxiNetUsers = taxiNetUsers;
		this.cardNo = cardNo;
		this.expiredYear = expiredYear;
		this.expiredMonth = expiredMonth;
		this.cvv = cvv;
		this.type = type;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.trips = trips;
		this.trips_1 = trips_1;
	}

	public Integer getPaymentID() {
		return this.paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}

	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public TaxiNetUsers getTaxinetusers() {
		return this.taxiNetUsers;
	}

	public void setTaxinetusers(TaxiNetUsers taxiNetUsers) {
		this.taxiNetUsers = taxiNetUsers;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpiredYear() {
		return this.expiredYear;
	}

	public void setExpiredYear(String expiredYear) {
		this.expiredYear = expiredYear;
	}

	public String getExpiredMonth() {
		return this.expiredMonth;
	}

	public void setExpiredMonth(String expiredMonth) {
		this.expiredMonth = expiredMonth;
	}

	public String getCvv() {
		return this.cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

}
