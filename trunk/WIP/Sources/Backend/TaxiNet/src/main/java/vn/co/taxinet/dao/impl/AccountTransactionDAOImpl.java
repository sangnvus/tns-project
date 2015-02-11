package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.AccountTransactionDAO;
import vn.co.taxinet.orm.AccountTransaction;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class AccountTransaction.
 * @see vn.co.taxinet.dao.AccountTransaction
 * @author Hibernate Tools
 */
@Service("accountTransactionDAO")
@Transactional
public class AccountTransactionDAOImpl extends BaseDAOImpl implements AccountTransactionDAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 582893727580502426L;
	private static final Logger log = LogManager.getLogger(AccountTransactionDAOImpl.class);

	public void persist(AccountTransaction transientInstance) {
		log.debug("persisting AccountTransaction instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AccountTransaction instance) {
		log.debug("attaching dirty AccountTransaction instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AccountTransaction instance) {
		log.debug("attaching clean AccountTransaction instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AccountTransaction persistentInstance) {
		log.debug("deleting AccountTransaction instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AccountTransaction merge(AccountTransaction detachedInstance) {
		log.debug("merging AccountTransaction instance");
		try {
			AccountTransaction result = (AccountTransaction) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AccountTransaction findById(java.lang.Integer id) {
		log.debug("getting AccountTransaction instance with id: " + id);
		try {
			AccountTransaction instance = (AccountTransaction) getSessionFactory()
					.getCurrentSession().get(
							"vn.co.taxinet.orm.AccountTransaction", id);
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

	public List<AccountTransaction> findByExample(AccountTransaction instance) {
		log.debug("finding AccountTransaction instance by example");
		try {
			List<AccountTransaction> results = (List<AccountTransaction>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.AccountTransaction")
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
