package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CityNameDAO;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.CityNameID;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CityName.
 * @see vn.co.taxinet.dao.CityName
 * @author Hibernate Tools
 */
@Service(value="cityNameDAO")
@Transactional
public class CityNameDAOImpl extends BaseDAOImpl implements CityNameDAO{

	private static final Logger log = LogManager.getLogger(CityNameDAOImpl.class);

	public void persist(CityName transientInstance) {
		log.debug("persisting CityName instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CityName instance) {
		log.debug("attaching dirty CityName instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CityName instance) {
		log.debug("attaching clean CityName instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CityName persistentInstance) {
		log.debug("deleting CityName instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CityName merge(CityName detachedInstance) {
		log.debug("merging CityName instance");
		try {
			CityName result = (CityName) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CityName findById(CityNameID id) {
		log.debug("getting CityName instance with id: " + id);
		try {
			CityName instance = (CityName) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.CityName", id);
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

	public List<CityName> findByExample(CityName instance) {
		log.debug("finding CityName instance by example");
		try {
			List<CityName> results = (List<CityName>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.CityName")
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
