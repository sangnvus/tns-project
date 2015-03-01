package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.PaymentDAO;
import vn.co.taxinet.orm.Payment;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Payment.
 * @see vn.co.taxinet.dao.Payment
 * @author Hibernate Tools
 */
@Service(value="paymentDAO")
@Transactional
public class PaymentDAOImpl extends BaseDAOImpl implements PaymentDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 790448166071964395L;
	private static final Logger log = LogManager.getLogger(PaymentDAOImpl.class);

	public void persist(Payment transientInstance) {
		log.debug("persisting Payment instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Payment instance) {
		log.debug("attaching dirty Payment instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Payment instance) {
		log.debug("attaching clean Payment instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Payment persistentInstance) {
		log.debug("deleting Payment instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Payment merge(Payment detachedInstance) {
		log.debug("merging Payment instance");
		try {
			Payment result = (Payment) getSessionFactory().getCurrentSession()
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
			Payment instance = (Payment) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.Payment", id);
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
			List<Payment> results = (List<Payment>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Payment")
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
