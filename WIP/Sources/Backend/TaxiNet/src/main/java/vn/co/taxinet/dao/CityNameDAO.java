package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.CityNameID;

public interface CityNameDAO extends BaseDAO {

	/**
	 * @author Ecchi
	 * @param countryCode
	 * @return name of city
	 */
	public List<CityName> selectAllCityName(String countryCode);
	public CityName findById(CityNameID id);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @param langCode
	 * @return
	 */
	public CityName findCityNameByIdAndLanguageCode(int id, String langCode);

}
