package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Trip generated by hbm2java
 */
public class Trip implements java.io.Serializable {

	private String requestId;
	private Rider rider;
	private Vehicle vehicle;
	private Driver driver;
	private City city;
	private Payment payment;
	private Date requestDatetime;
	private Integer countryId;
	private String fromAddress;
	private Integer toCity;
	private String toAddress;
	private Integer timeRequest;
	private Date timeStart;
	private Date timeEnd;
	private Double startLatitude;
	private Double startLongtitude;
	private Double endLatitude;
	private Double endLongtitude;
	private Double fee;
	private String status;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;

	public Trip() {
	}

	public Trip(String requestId, Rider rider, Vehicle vehicle, Driver driver,
			String createdBy, Date createdDate, String lastModifiedBy,
			Date lastModifiedDate) {
		this.requestId = requestId;
		this.rider = rider;
		this.vehicle = vehicle;
		this.driver = driver;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Trip(String requestId, Rider rider, Vehicle vehicle, Driver driver,
			City city, Payment payment, Date requestDatetime,
			Integer countryId, String fromAddress, Integer toCity,
			String toAddress, Integer timeRequest, Date timeStart,
			Date timeEnd, Double startLatitude, Double startLongtitude,
			Double endLatitude, Double endLongtitude, Double fee,
			String status, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate) {
		this.requestId = requestId;
		this.rider = rider;
		this.vehicle = vehicle;
		this.driver = driver;
		this.city = city;
		this.payment = payment;
		this.requestDatetime = requestDatetime;
		this.countryId = countryId;
		this.fromAddress = fromAddress;
		this.toCity = toCity;
		this.toAddress = toAddress;
		this.timeRequest = timeRequest;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.startLatitude = startLatitude;
		this.startLongtitude = startLongtitude;
		this.endLatitude = endLatitude;
		this.endLongtitude = endLongtitude;
		this.fee = fee;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Rider getRider() {
		return this.rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Date getRequestDatetime() {
		return this.requestDatetime;
	}

	public void setRequestDatetime(Date requestDatetime) {
		this.requestDatetime = requestDatetime;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Integer getToCity() {
		return this.toCity;
	}

	public void setToCity(Integer toCity) {
		this.toCity = toCity;
	}

	public String getToAddress() {
		return this.toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Integer getTimeRequest() {
		return this.timeRequest;
	}

	public void setTimeRequest(Integer timeRequest) {
		this.timeRequest = timeRequest;
	}

	public Date getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Double getStartLatitude() {
		return this.startLatitude;
	}

	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Double getStartLongtitude() {
		return this.startLongtitude;
	}

	public void setStartLongtitude(Double startLongtitude) {
		this.startLongtitude = startLongtitude;
	}

	public Double getEndLatitude() {
		return this.endLatitude;
	}

	public void setEndLatitude(Double endLatitude) {
		this.endLatitude = endLatitude;
	}

	public Double getEndLongtitude() {
		return this.endLongtitude;
	}

	public void setEndLongtitude(Double endLongtitude) {
		this.endLongtitude = endLongtitude;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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

}
