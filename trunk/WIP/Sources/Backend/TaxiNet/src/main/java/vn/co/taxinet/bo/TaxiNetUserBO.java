package vn.co.taxinet.bo;

import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.TaxiNetUserDTO;


/**
 * @author Ecchi
 *
 */
public interface TaxiNetUserBO {
	public String register(TaxiNetUserDTO user) throws TNException;
	
	/**
	 * @author Ecchi
	 * @param userID
	 * @param newPassword
	 * @return
	 */
	public String changePass(String userID, String newPassword);
}
