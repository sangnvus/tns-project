package vn.co.taxinet.dao.impl;

// Generated Mar 7, 2015 10:20:44 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.CarTypeDAO;
import vn.co.taxinet.orm.CarType;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CarType.
 * @see vn.co.taxinet.orm.CarType
 * @author Hibernate Tools
 */
public class CarTypeDAOImplement extends BaseDAOImpl implements CarTypeDAO{

	private static final Logger log = LogManager.getLogger(CarTypeDAOImplement.class);


	public void persist(CarType transientInstance) {
		log.debug("persisting CarType instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CarType instance) {
		log.debug("attaching dirty CarType instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CarType instance) {
		log.debug("attaching clean CarType instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CarType persistentInstance) {
		log.debug("deleting CarType instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CarType merge(CarType detachedInstance) {
		log.debug("merging CarType instance");
		try {
			CarType result = (CarType) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CarType findById(java.lang.Integer id) {
		log.debug("getting CarType instance with id: " + id);
		try {
			CarType instance = (CarType) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.CarType", id);
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

	public List<CarType> findByExample(CarType instance) {
		log.debug("finding CarType instance by example");
		try {
			List<CarType> results = (List<CarType>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.CarType")
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
