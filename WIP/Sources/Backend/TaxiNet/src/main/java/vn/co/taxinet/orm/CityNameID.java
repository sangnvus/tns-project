package vn.co.taxinet.orm;

// Generated Jan 28, 2015 9:41:06 PM by Hibernate Tools 4.0.0

/**
 * CityNameID generated by hbm2java
 */
public class CityNameID implements java.io.Serializable {

	private int cityId;
	private int languageCode;

	public CityNameID() {
	}

	public CityNameID(int cityId, int languageCode) {
		this.cityId = cityId;
		this.languageCode = languageCode;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(int languageCode) {
		this.languageCode = languageCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CityNameID))
			return false;
		CityNameID castOther = (CityNameID) other;

		return (this.getCityId() == castOther.getCityId())
				&& (this.getLanguageCode() == castOther.getLanguageCode());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCityId();
		result = 37 * result + this.getLanguageCode();
		return result;
	}

}
