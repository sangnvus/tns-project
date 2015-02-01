package vn.co.taxinet.bo.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

@Service(value = "agentBO")
@Transactional
public class AgentBOImpl implements AgentBO {
	private static final Logger logger = LogManager
			.getLogger(AgentBOImpl.class);
	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;

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
}
