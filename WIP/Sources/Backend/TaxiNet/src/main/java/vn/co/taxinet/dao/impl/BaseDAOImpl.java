package vn.co.taxinet.dao.impl;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDAOImpl implements Serializable {
	private static final Logger logger = LogManager.getLogger(BaseDAOImpl.class);
    private static final long serialVersionUID = 1L;
    private final static String THIS = "BaseDAO";


    private SessionFactory sessionFactory;

    /**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		logger.info("setSessionFactory()");
        this.sessionFactory = sessionFactory;
    }

    /**
     * Update an object in Session
     * @param obj
     * @param session
     */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Object obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	/**
	 * 
	 * @param obj
	 * @param session
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(Object obj) {
		sessionFactory.getCurrentSession().persist(obj);

	}

	/**
	 * 
	 * @param obj
	 * @param session
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Object obj) {
		sessionFactory.getCurrentSession().delete(obj);		
	}

}
