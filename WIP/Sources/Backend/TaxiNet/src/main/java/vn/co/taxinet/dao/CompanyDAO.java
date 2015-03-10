package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Company;

public interface CompanyDAO extends BaseDAO {
	public Company findById(java.lang.Integer id);

	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @return
	 */
	public Company findCompanyByDriverId(String id);
}
