package vn.co.taxinet.dto;

public class DriverDTO {
	public String driverId;
	public String driverName;
	public String driverImage;
	public String type;
	public double longitude;
	public double latitude;
	public double rating;
	public double openKm;
	public double openPrice;
	public double firstKm;
	public double firstKmPrice;
	public double nextKm;
	public double nextKmPrice;
	public double waitingPrice;
	public double returnPrice;
	public String driverType;
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverImage() {
		return driverImage;
	}
	public void setDriverImage(String driverImage) {
		this.driverImage = driverImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getOpenKm() {
		return openKm;
	}
	public void setOpenKm(double openKm) {
		this.openKm = openKm;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getFirstKm() {
		return firstKm;
	}
	public void setFirstKm(double firstKm) {
		this.firstKm = firstKm;
	}
	public double getFirstKmPrice() {
		return firstKmPrice;
	}
	public void setFirstKmPrice(double firstKmPrice) {
		this.firstKmPrice = firstKmPrice;
	}
	public double getNextKm() {
		return nextKm;
	}
	public void setNextKm(double nextKm) {
		this.nextKm = nextKm;
	}
	public double getNextKmPrice() {
		return nextKmPrice;
	}
	public void setNextKmPrice(double nextKmPrice) {
		this.nextKmPrice = nextKmPrice;
	}
	public double getWaitingPrice() {
		return waitingPrice;
	}
	public void setWaitingPrice(double waitingPrice) {
		this.waitingPrice = waitingPrice;
	}
	public double getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(double returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getDriverType() {
		return driverType;
	}
	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
}
