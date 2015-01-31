package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.BankDAO;
import vn.co.taxinet.orm.Bank;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Bank.
 * @see vn.co.taxinet.dao.Bank
 * @author Hibernate Tools
 */
@Transactional
public class BankDAOImpl implements BankDAO{

	private static final Log log = LogFactory.getLog(BankDAOImpl.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Bank transientInstance) {
		log.debug("persisting Bank instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Bank instance) {
		log.debug("attaching dirty Bank instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bank instance) {
		log.debug("attaching clean Bank instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Bank persistentInstance) {
		log.debug("deleting Bank instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bank merge(Bank detachedInstance) {
		log.debug("merging Bank instance");
		try {
			Bank result = (Bank) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Bank findById(java.lang.Integer id) {
		log.debug("getting Bank instance with id: " + id);
		try {
			Bank instance = (Bank) sessionFactory.getCurrentSession().get(
					"vn.co.taxinet.dao.Bank", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Bank> findByExample(Bank instance) {
		log.debug("finding Bank instance by example");
		try {
			List<Bank> results = (List<Bank>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.Bank")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
