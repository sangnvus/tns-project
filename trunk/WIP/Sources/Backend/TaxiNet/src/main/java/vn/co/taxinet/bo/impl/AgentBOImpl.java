package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.orm.Rider;

@Service(value="agentBO")
@Transactional
public class AgentBOImpl implements AgentBO {
	private static final Logger logger = LogManager.getLogger(AgentBOImpl.class);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Rider register(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}		
}
