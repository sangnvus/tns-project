package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.dto.TaxiNetUserDTO;
import vn.co.taxinet.orm.Country;
import vn.co.taxinet.orm.Language;
import vn.co.taxinet.orm.TaxiNetUsers;


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
	
	/**
	 * @author Ecchi
	 * @return
	 */
	public List<Country> listAllCountry();
	
	/**
	 * @author Ecchi
	 * @return
	 */
	public List<Language> listAllLanguage();
	
	public MessageDTO updateRegId(String id, String groupUser, String regId) throws TNException;
	
	/**
	 * @author Ecchi
	 * @param userId
	 * @return
	 */
	public TaxiNetUsers getUserInfo(String userId);
}
