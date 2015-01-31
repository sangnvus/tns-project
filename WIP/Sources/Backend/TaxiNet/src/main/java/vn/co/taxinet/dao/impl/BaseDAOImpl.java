package vn.co.taxinet.dao.impl;

import java.io.Serializable;

import javax.naming.InitialContext;

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
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			logger.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
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
