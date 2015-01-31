package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.VehicleDAO;
import vn.co.taxinet.orm.Vehicle;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Vehicle.
 * @see vn.co.taxinet.dao.Vehicle
 * @author Hibernate Tools
 */
@Transactional
public class VehicleDAOImpl implements VehicleDAO{

	private static final Log log = LogFactory.getLog(VehicleDAOImpl.class);

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

	public void persist(Vehicle transientInstance) {
		log.debug("persisting Vehicle instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Vehicle instance) {
		log.debug("attaching dirty Vehicle instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Vehicle instance) {
		log.debug("attaching clean Vehicle instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Vehicle persistentInstance) {
		log.debug("deleting Vehicle instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Vehicle merge(Vehicle detachedInstance) {
		log.debug("merging Vehicle instance");
		try {
			Vehicle result = (Vehicle) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Vehicle findById(java.lang.Integer id) {
		log.debug("getting Vehicle instance with id: " + id);
		try {
			Vehicle instance = (Vehicle) sessionFactory.getCurrentSession()
					.get("vn.co.taxinet.dao.Vehicle", id);
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

	public List<Vehicle> findByExample(Vehicle instance) {
		log.debug("finding Vehicle instance by example");
		try {
			List<Vehicle> results = (List<Vehicle>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.Vehicle")
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
