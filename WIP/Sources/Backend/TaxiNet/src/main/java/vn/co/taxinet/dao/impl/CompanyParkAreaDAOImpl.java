package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CompanyParkAreaDAO;
import vn.co.taxinet.orm.CompanyParkArea;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CompanyParkArea.
 * @see vn.co.taxinet.dao.CompanyParkArea
 * @author Hibernate Tools
 */
@Transactional
public class CompanyParkAreaDAOImpl implements CompanyParkAreaDAO{

	private static final Log log = LogFactory.getLog(CompanyParkAreaDAOImpl.class);

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

	public void persist(CompanyParkArea transientInstance) {
		log.debug("persisting CompanyParkArea instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CompanyParkArea instance) {
		log.debug("attaching dirty CompanyParkArea instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CompanyParkArea instance) {
		log.debug("attaching clean CompanyParkArea instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CompanyParkArea persistentInstance) {
		log.debug("deleting CompanyParkArea instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CompanyParkArea merge(CompanyParkArea detachedInstance) {
		log.debug("merging CompanyParkArea instance");
		try {
			CompanyParkArea result = (CompanyParkArea) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CompanyParkArea findById(java.lang.Integer id) {
		log.debug("getting CompanyParkArea instance with id: " + id);
		try {
			CompanyParkArea instance = (CompanyParkArea) sessionFactory
					.getCurrentSession().get(
							"vn.co.taxinet.dao.CompanyParkArea", id);
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

	public List<CompanyParkArea> findByExample(CompanyParkArea instance) {
		log.debug("finding CompanyParkArea instance by example");
		try {
			List<CompanyParkArea> results = (List<CompanyParkArea>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.CompanyParkArea")
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
