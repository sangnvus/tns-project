package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.CarMaker;

public interface CarMakerDAO extends BaseDAO {
	/**
	 * select all car maker
	 * @return
	 */
	public List<CarMaker> selectAllCarMaker();
}
