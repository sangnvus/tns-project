package vn.co.taxinet.bo;

import vn.co.taxinet.orm.TaxiNetUsers;


/**
 * @author Ecchi
 *
 */
public interface TaxiNetUserBO {
	public String register(String username, String password, String email, String userGroup,
		String languageCode, String countryCode);
	
	/**
	 * @author Ecchi
	 * @param userID
	 * @param newPassword
	 * @return
	 */
	public String changePass(String userID, String newPassword);
	public TaxiNetUsers findUserbyEmail(String email);
}
