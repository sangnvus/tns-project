package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.Country;

public interface CountryDAO extends BaseDAO {
	/**
	 * 
	 * @return list of country
	 */
	public List<Country> selectAllCountry();
}
