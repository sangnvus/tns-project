package vn.co.taxinet.bo;

import java.util.List;

import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author Ecchi
 *
 */
public interface AgentBO {
	
	/**
	 * list all user ( rider,driver,taxigroup)
	 * @return
	 */
	public List<TaxiNetUsers> listAllUsers(String username, String mail);
	
	/**
	 * @author Ecchi
	 * @category add balance
	 */
	public void addUserBalance();
	
	/**
	 * 
	 */
	public void showUserTransHistory();
	
}
