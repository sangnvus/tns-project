package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.CarType;

public interface CarTypeDAO extends BaseDAO{
	/**
	 * @author Ecchi
	 * @param carMakerID
	 * @return
	 */
	public List<CarType> getAllCarTypeByCarMaker(String carMakerID);
}
