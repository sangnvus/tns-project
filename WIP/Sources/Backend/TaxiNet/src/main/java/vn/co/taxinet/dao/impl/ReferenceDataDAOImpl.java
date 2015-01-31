package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.ReferenceDataDAO;
import vn.co.taxinet.orm.ReferenceData;
import vn.co.taxinet.orm.ReferenceDataID;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ReferenceData.
 * @see vn.co.taxinet.dao.ReferenceData
 * @author Hibernate Tools
 */
public class ReferenceDataDAOImpl implements ReferenceDataDAO{

	private static final Log log = LogFactory.getLog(ReferenceDataDAOImpl.class);

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

	public void persist(ReferenceData transientInstance) {
		log.debug("persisting ReferenceData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferenceData instance) {
		log.debug("attaching dirty ReferenceData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferenceData instance) {
		log.debug("attaching clean ReferenceData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ReferenceData persistentInstance) {
		log.debug("deleting ReferenceData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferenceData merge(ReferenceData detachedInstance) {
		log.debug("merging ReferenceData instance");
		try {
			ReferenceData result = (ReferenceData) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ReferenceData findById(ReferenceDataID id) {
		log.debug("getting ReferenceData instance with id: " + id);
		try {
			ReferenceData instance = (ReferenceData) sessionFactory
					.getCurrentSession().get("vn.co.taxinet.dao.ReferenceData",
							id);
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

	public List<ReferenceData> findByExample(ReferenceData instance) {
		log.debug("finding ReferenceData instance by example");
		try {
			List<ReferenceData> results = (List<ReferenceData>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.ReferenceData")
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
