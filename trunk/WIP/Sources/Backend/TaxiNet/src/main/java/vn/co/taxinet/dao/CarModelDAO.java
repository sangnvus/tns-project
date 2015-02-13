package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.CarModel;

public interface CarModelDAO extends BaseDAO {
	
	/**
	 * find car model by car maker id
	 * @param id
	 * @return car model
	 */
	public List<CarModel> selectCarModelByCarMakerID(String id);
}
