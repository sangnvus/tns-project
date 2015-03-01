package vn.co.taxinet.dao;

import vn.co.taxinet.orm.City;

public interface CityDAO extends BaseDAO {
	public City findById(int id);
}
