package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.PaymentDAO;
import vn.co.taxinet.orm.Payment;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Payment.
 * @see vn.co.taxinet.dao.Payment
 * @author Hibernate Tools
 */
public class PaymentDAOImpl implements PaymentDAO{

	private static final Log log = LogFactory.getLog(PaymentDAOImpl.class);

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

	public void persist(Payment transientInstance) {
		log.debug("persisting Payment instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Payment instance) {
		log.debug("attaching dirty Payment instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Payment instance) {
		log.debug("attaching clean Payment instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Payment persistentInstance) {
		log.debug("deleting Payment instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Payment merge(Payment detachedInstance) {
		log.debug("merging Payment instance");
		try {
			Payment result = (Payment) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Payment findById(java.lang.Integer id) {
		log.debug("getting Payment instance with id: " + id);
		try {
			Payment instance = (Payment) sessionFactory.getCurrentSession()
					.get("vn.co.taxinet.dao.Payment", id);
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

	public List<Payment> findByExample(Payment instance) {
		log.debug("finding Payment instance by example");
		try {
			List<Payment> results = (List<Payment>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.Payment")
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
