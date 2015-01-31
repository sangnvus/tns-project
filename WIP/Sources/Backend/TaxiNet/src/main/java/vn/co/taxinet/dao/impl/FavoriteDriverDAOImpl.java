package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.FavoriteDriverDAO;
import vn.co.taxinet.orm.FavoriteDriver;
import vn.co.taxinet.orm.FavoriteDriverID;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FavoriteDriver.
 * @see vn.co.taxinet.dao.FavoriteDriver
 * @author Hibernate Tools
 */
@Service(value="favoriteDriverDAO")
@Transactional
public class FavoriteDriverDAOImpl extends BaseDAOImpl implements FavoriteDriverDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5672751495454538463L;
	private static final Logger log = LogManager.getLogger(FavoriteDriverDAOImpl.class);

	public void persist(FavoriteDriver transientInstance) {
		log.debug("persisting FavoriteDriver instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FavoriteDriver instance) {
		log.debug("attaching dirty FavoriteDriver instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FavoriteDriver instance) {
		log.debug("attaching clean FavoriteDriver instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FavoriteDriver persistentInstance) {
		log.debug("deleting FavoriteDriver instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FavoriteDriver merge(FavoriteDriver detachedInstance) {
		log.debug("merging FavoriteDriver instance");
		try {
			FavoriteDriver result = (FavoriteDriver) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FavoriteDriver findById(FavoriteDriverID id) {
		log.debug("getting FavoriteDriver instance with id: " + id);
		try {
			FavoriteDriver instance = (FavoriteDriver) getSessionFactory()
					.getCurrentSession().get(
							"vn.co.taxinet.dao.FavoriteDriver", id);
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

	public List<FavoriteDriver> findByExample(FavoriteDriver instance) {
		log.debug("finding FavoriteDriver instance by example");
		try {
			List<FavoriteDriver> results = (List<FavoriteDriver>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.FavoriteDriver")
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
