package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Term;

public interface TermDAO extends BaseDAO {

	/**
	 * @author Hieu-Gie
	 * 
	 * @param type
	 * @return
	 */
	public Term findTermByType(String type);

}
