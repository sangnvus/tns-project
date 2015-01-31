package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CarMakerDAO;
import vn.co.taxinet.orm.CarMaker;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CarMaker.
 * @see vn.co.taxinet.dao.CarMaker
 * @author Hibernate Tools
 */
@Transactional
public class CarMakerDAOImpl implements CarMakerDAO{

	private static final Log log = LogFactory.getLog(CarMakerDAOImpl.class);

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

	public void persist(CarMaker transientInstance) {
		log.debug("persisting CarMaker instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CarMaker instance) {
		log.debug("attaching dirty CarMaker instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CarMaker instance) {
		log.debug("attaching clean CarMaker instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CarMaker persistentInstance) {
		log.debug("deleting CarMaker instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CarMaker merge(CarMaker detachedInstance) {
		log.debug("merging CarMaker instance");
		try {
			CarMaker result = (CarMaker) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CarMaker findById(java.lang.String id) {
		log.debug("getting CarMaker instance with id: " + id);
		try {
			CarMaker instance = (CarMaker) sessionFactory.getCurrentSession()
					.get("vn.co.taxinet.dao.CarMaker", id);
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

	public List<CarMaker> findByExample(CarMaker instance) {
		log.debug("finding CarMaker instance by example");
		try {
			List<CarMaker> results = (List<CarMaker>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.CarMaker")
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
