package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.CityName;

public interface CityNameDAO extends BaseDAO {
	
	/**
	 * @author Ecchi
	 * @param countryCode
	 * @return name of city
	 */
	public List<CityName> selectAllCityName(String countryCode);
}
