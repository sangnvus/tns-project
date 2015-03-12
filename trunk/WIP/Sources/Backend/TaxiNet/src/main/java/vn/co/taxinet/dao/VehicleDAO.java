package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Vehicle;

public interface VehicleDAO extends BaseDAO {
	/**
	 * @author Ecchi
	 * @param vehicleID
	 * @return 
	 */
	public Vehicle getVehicleFromID(String vehicleID);
	
	/**
	 * @author Ecchi
	 * @param plate
	 * @return
	 */
	public Vehicle getVehicleFromPlate(String plate);
}
