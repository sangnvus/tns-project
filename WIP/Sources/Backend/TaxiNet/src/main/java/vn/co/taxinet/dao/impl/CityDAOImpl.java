package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CityDAO;
import vn.co.taxinet.orm.City;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class City.
 * @see vn.co.taxinet.dao.City
 * @author Hibernate Tools
 */
@Service(value="cityDAO")
@Transactional
public class CityDAOImpl extends BaseDAOImpl implements CityDAO{
	private static final Logger log = LogManager.getLogger(CityDAOImpl.class);

	public void persist(City transientInstance) {
		log.debug("persisting City instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(City instance) {
		log.debug("attaching dirty City instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(City instance) {
		log.debug("attaching clean City instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(City persistentInstance) {
		log.debug("deleting City instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public City merge(City detachedInstance) {
		log.debug("merging City instance");
		try {
			City result = (City) getSessionFactory().getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public City findById(int id) {
		log.debug("getting City instance with id: " + id);
		try {
			City instance = (City) getSessionFactory().getCurrentSession().get(
					"vn.co.taxinet.orm.City", id);
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

	public List<City> findByExample(City instance) {
		log.debug("finding City instance by example");
		try {
			List<City> results = (List<City>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.City")
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
