package vn.co.taxinet.bo;

import java.util.Date;
import java.util.List;

import vn.co.taxinet.dto.TaxiNetUserDTO;
import vn.co.taxinet.orm.AccountTransaction;
import vn.co.taxinet.orm.Rider;
import java.util.List;
import vn.co.taxinet.orm.TaxiNetUsers;

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
	
	/**
	 * @author Ha
	 * @param username
	 * @param name
	 * @param page
	 * @param numberOfElement
	 * @return
	 */
	public List<TaxiNetUserDTO> paginationList(String username, String name, int page, int numberOfElement);
	/**
	 * @author Ha
	 * @param username
	 * @param name
	 * @return
	 */
	public int countDriverCompany(String username, String name);
	/**
	 * @author Ha
	 * @param transactionID
	 * @param userId
	 * @param agentId
	 * @param chargeDate
	 * @param amount
	 * @return
	 */
	public String chargeMoney(String transactionId, String userId, String agentId, Date chargeDate, double amount);
	public List<AccountTransaction> listAccountTransaction (String username, int page, int pageSize, Date fromDate, Date toDate);
}
