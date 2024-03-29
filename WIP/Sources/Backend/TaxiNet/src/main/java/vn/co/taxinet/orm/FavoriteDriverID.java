package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

/**
 * FavoriteDriverID generated by hbm2java
 */
public class FavoriteDriverID implements java.io.Serializable {

	private int riderId;
	private int driverId;

	public FavoriteDriverID() {
	}

	public FavoriteDriverID(int riderId, int driverId) {
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
		if (!(other instanceof FavoriteDriverID))
			return false;
		FavoriteDriverID castOther = (FavoriteDriverID) other;

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
