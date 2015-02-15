package vn.co.taxinet.dao;

public interface VehicleDAO extends BaseDAO {
	/**
	 * @author Ecchi
	 * @param carMaker
	 * @param carModel
	 * @param yearOfProduct
	 * @param inColor
	 * @param exColor
	 * @param plate
	 * @param countryCode
	 * @param cityCode
	 * @param userID
	 */
	public void insertVehicles(String carMaker, String carModel,
			String yearOfProduct, String inColor, String exColor, String plate,
			String countryCode, String cityCode, String userID);
}
