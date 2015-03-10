package vn.co.taxinet.bo;

import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.CompanyDTO;

public interface CompanyBO {
	/**
	 * @author Hieu-Gie
	 * 
	 * @param id
	 * @return
	 * @throws TNException 
	 */
	public CompanyDTO findCompanyByDriverId(String id) throws TNException;
}
