package vn.co.taxinet.orm;

// Generated Jan 23, 2015 10:11:00 AM by Hibernate Tools 4.0.0

/**
 * FavoritedriverId generated by hbm2java
 */
public class FavoritedriverId implements java.io.Serializable {

	private int riderId;
	private int driverId;

	public FavoritedriverId() {
	}

	public FavoritedriverId(int riderId, int driverId) {
		this.riderId = riderId;
		this.driverId = driverId;
	}

	public int getRiderId() {
		return this.riderId;
	}

	public void setRiderId(int riderId) {
		this.riderId = riderId;
	}

	public int getDriverId() {
		return this.driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FavoritedriverId))
			return false;
		FavoritedriverId castOther = (FavoritedriverId) other;

		return (this.getRiderId() == castOther.getRiderId())
				&& (this.getDriverId() == castOther.getDriverId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRiderId();
		result = 37 * result + this.getDriverId();
		return result;
	}

}
