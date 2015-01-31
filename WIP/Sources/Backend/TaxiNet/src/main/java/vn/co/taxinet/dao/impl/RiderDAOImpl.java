package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.orm.Rider;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Rider.
 * @see vn.co.taxinet.dao.Rider
 * @author Hibernate Tools
 */
public class RiderDAOImpl implements RiderDAO{

	private static final Log log = LogFactory.getLog(RiderDAOImpl.class);

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

	public void persist(Rider transientInstance) {
		log.debug("persisting Rider instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Rider instance) {
		log.debug("attaching dirty Rider instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rider instance) {
		log.debug("attaching clean Rider instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Rider persistentInstance) {
		log.debug("deleting Rider instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rider merge(Rider detachedInstance) {
		log.debug("merging Rider instance");
		try {
			Rider result = (Rider) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rider findById(java.lang.String id) {
		log.debug("getting Rider instance with id: " + id);
		try {
			Rider instance = (Rider) sessionFactory.getCurrentSession().get(
					"vn.co.taxinet.dao.Rider", id);
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

	public List<Rider> findByExample(Rider instance) {
		log.debug("finding Rider instance by example");
		try {
			List<Rider> results = (List<Rider>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.Rider")
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
