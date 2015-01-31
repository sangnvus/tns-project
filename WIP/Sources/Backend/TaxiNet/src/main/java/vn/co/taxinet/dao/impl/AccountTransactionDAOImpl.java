package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.AccountTransactionDAO;
import vn.co.taxinet.orm.AccountTransaction;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class AccountTransaction.
 * @see vn.co.taxinet.dao.AccountTransaction
 * @author Hibernate Tools
 */
@Transactional
public class AccountTransactionDAOImpl extends BaseDAOImpl implements AccountTransactionDAO{

	private static final Log log = LogFactory
			.getLog(AccountTransactionDAOImpl.class);

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

	public void persist(AccountTransaction transientInstance) {
		log.debug("persisting AccountTransaction instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AccountTransaction instance) {
		log.debug("attaching dirty AccountTransaction instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AccountTransaction instance) {
		log.debug("attaching clean AccountTransaction instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AccountTransaction persistentInstance) {
		log.debug("deleting AccountTransaction instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AccountTransaction merge(AccountTransaction detachedInstance) {
		log.debug("merging AccountTransaction instance");
		try {
			AccountTransaction result = (AccountTransaction) sessionFactory
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
			AccountTransaction instance = (AccountTransaction) sessionFactory
					.getCurrentSession().get(
							"vn.co.taxinet.dao.AccountTransaction", id);
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
			List<AccountTransaction> results = (List<AccountTransaction>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.AccountTransaction")
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
