package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.co.taxinet.bo.TermBO;
import vn.co.taxinet.dao.TermDAO;
import vn.co.taxinet.orm.Term;

@Service
public class TermBOImpl implements TermBO {
	private static final Logger logger = LogManager.getLogger(TermBOImpl.class);

	@Autowired
	private TermDAO termDAO;

	public TermDAO getTermDAO() {
		return termDAO;
	}

	public void setTermDAO(TermDAO termDAO) {
		this.termDAO = termDAO;
	}

	public Term findTermByType(String type) {
		if (type.equalsIgnoreCase("")) {
			return null;
		}
		return termDAO.findTermByType(type);
	}

}
