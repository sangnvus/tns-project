package vn.co.taxinet.bo.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.dao.AccountTransactionDAO;
import vn.co.taxinet.dao.AgentDAO;
import vn.co.taxinet.dao.CityNameDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dto.AccountTransactionDTO;
import vn.co.taxinet.dto.TaxiNetUserDTO;
import vn.co.taxinet.orm.AccountTransaction;
import vn.co.taxinet.orm.Agent;
import vn.co.taxinet.orm.CityNameID;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

//@Service(value = "agentBO")
@Transactional
public class AgentBOImpl implements AgentBO, Serializable {
	private static final Logger logger = LogManager
			.getLogger(AgentBOImpl.class);
	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;
	@Autowired
	private CityNameDAO cityNameDAO;
	@Autowired
	private AccountTransactionDAO accountTransactionDAO;
	@Autowired
	private AgentDAO agentDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public Rider register(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<TaxiNetUsers> listAllUsers(String username, String email) {
		// TODO Auto-generated method stub
		return taxiNetUserDAO.listAllUsers(username, email);
	}

	public void addUserBalance() {
		// TODO Auto-generated method stub

	}

	public void showUserTransHistory() {
		// TODO Auto-generated method stub

	}

	public List<TaxiNetUserDTO> paginationList(String username, String name,
			int page, int numberOfElement) {
		List<TaxiNetUserDTO> listUserDTO = new ArrayList();
		List<TaxiNetUsers> listUser = taxiNetUserDAO.searchPaginationList(
				username, name, page, numberOfElement);
		for (int i = 0; i < listUser.size(); i++) {
			TaxiNetUserDTO userDTO = new TaxiNetUserDTO();
			if (listUser.get(i).getDriver() != null) {
				userDTO.setFirstName(listUser.get(i).getDriver().getFirstName());
				userDTO.setLastName(listUser.get(i).getDriver().getLastName());
				userDTO.setPhone(listUser.get(i).getDriver().getMobileNo());
				userDTO.setBalance(listUser.get(i).getDriver().getBalance());
				if (listUser.get(i).getDriver().getAddress() != null) {
					userDTO.setAddress(listUser.get(i).getDriver().getAddress()
							.getAddressDetail());
				}
			} else if (listUser.get(i).getCompany() != null) {
				userDTO.setPhone(listUser.get(i).getCompany().getMobileNo());
				userDTO.setBalance(listUser.get(i).getCompany().getBalance());
				if (listUser.get(i).getCompany().getAddress() != null) {
					userDTO.setAddress(listUser.get(i).getCompany()
							.getAddress().getAddressDetail());
				}
			}
			userDTO.setUserId(listUser.get(i).getUserId());
			userDTO.setUserName(listUser.get(i).getUsername());
			//get city name
			if(listUser.get(i).getCityId()!=null){
				String languageCode = listUser.get(i).getLanguage().getLanguageCode();
				int cityID = listUser.get(i).getCityId();
				CityNameID cityNameID = new CityNameID(cityID, languageCode);
				if(cityNameDAO.findById(cityNameID)!=null){
					String cityName = cityNameDAO.findById(cityNameID).getCityName();
					userDTO.setCity(cityName);
				}
			}
			//get company name
			if (listUser.get(i).getCompany() != null) {
				userDTO.setCompanyName(listUser.get(i).getCompany().getName());
			}
			listUserDTO.add(userDTO);
		}

		return listUserDTO;
	}
	
	public String chargeMoney(String transactionId, String userId, String agentId, Date chargeDate,
			double amount) {
		AccountTransaction accountTransaction = new AccountTransaction();
		TaxiNetUsers user = taxiNetUserDAO.findById(userId);
		Agent agent = agentDAO.findById(agentId);
		
		accountTransaction.setTransactionId(transactionId);
		accountTransaction.setDateTime(1);
		accountTransaction.setAmount(amount);
		accountTransaction.setTaxinetusers(user);
		accountTransaction.setCreatedBy(agentId);
		accountTransaction.setCreatedDate(Utility.getCurrentDateTime());
		accountTransaction.setLastModifiedBy(agentId);
		accountTransaction.setLastModifiedDate(Utility.getCurrentDateTime());
		accountTransactionDAO.insert(accountTransaction);
		return null;
	}
	@Transactional
	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	public void setCityNameDAO(CityNameDAO cityNameDAO) {
		this.cityNameDAO = cityNameDAO;
	}

	public void setAccountTransactionDAO(AccountTransactionDAO accountTransactionDAO) {
		this.accountTransactionDAO = accountTransactionDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public int countDriverCompany(String username, String name) {
		// TODO Auto-generated method stub
		return taxiNetUserDAO.countDriverCompany(username, name);
	}

	public List<AccountTransactionDTO> listAccountTransaction(String username,
			int page, int pageSize, Date fromDate, Date toDate) {
		List<AccountTransaction> listAcountTransaction = accountTransactionDAO.pagination(username, page, pageSize, fromDate, toDate);
		List<AccountTransactionDTO> result = new ArrayList();
		for (int i = 0; i < listAcountTransaction.size(); i++) {
			AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
			accountTransactionDTO.setInvoiceNumber(listAcountTransaction.get(i).getTransactionId());
			accountTransactionDTO.setChargeDate(listAcountTransaction.get(i).getCreatedDate());
			accountTransactionDTO.setAmount(listAcountTransaction.get(i).getAmount());
			accountTransactionDTO.setUsername(listAcountTransaction.get(i).getTaxinetusers().getUsername());
			result.add(accountTransactionDTO);
		}
		return result;
	}

	public int countAcountTransaction() {
		// TODO Auto-generated method stub
		return 0;
	}

}
