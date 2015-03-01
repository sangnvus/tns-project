package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CountryDAO;
import vn.co.taxinet.orm.Country;

/**
 * Home object for domain model class Country.
 * 
 * @see vn.co.taxinet.dao.Country
 * @author Hibernate Tools
 */
@Service(value = "countryDAO")
@Transactional
public class CountryDAOImpl extends BaseDAOImpl implements CountryDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2229625565519998903L;
	private static final Logger log = LogManager
			.getLogger(CountryDAOImpl.class);

	public void persist(Country transientInstance) {
		log.debug("persisting Country instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Country instance) {
		log.debug("attaching dirty Country instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Country instance) {
		log.debug("attaching clean Country instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Country persistentInstance) {
		log.debug("deleting Country instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Country merge(Country detachedInstance) {
		log.debug("merging Country instance");
		try {
			Country result = (Country) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Country findById(java.lang.String id) {
		log.debug("getting Country instance with id: " + id);
		try {
			Country instance = (Country) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.orm.Country", id);
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

	public List<Country> findByExample(Country instance) {
		log.debug("finding Country instance by example");
		try {
			List<Country> results = (List<Country>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Country")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see vn.co.taxinet.dao.CountryDAO#selectAllCountry()
	 */
	public List<Country> selectAllCountry() {
		Session session = getSessionFactory().openSession();
		String hql = "FROM Country";
		Query query = session.createQuery(hql);
		List<Country> countryList = query.list();
		return countryList;
	}
}
