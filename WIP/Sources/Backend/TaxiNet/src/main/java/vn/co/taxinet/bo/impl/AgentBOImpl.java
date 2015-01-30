package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.orm.Rider;

@Transactional
public class AgentBOImpl implements AgentBO {
	private static final Logger logger = LogManager.getLogger(AuthenticationBOImpl.class);

	public Rider register(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}		
}
