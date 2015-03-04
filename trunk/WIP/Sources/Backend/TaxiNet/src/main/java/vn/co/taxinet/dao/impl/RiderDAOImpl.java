package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.orm.Rider;
import static org.hibernate.criterion.Example.create;
/**
 * Home object for domain model class Rider.
 * 
 * @see vn.co.taxinet.dao.Rider
 * @author Hibernate Tools
 */
@Service(value = "riderDAO")
@Transactional
public class RiderDAOImpl extends BaseDAOImpl implements RiderDAO {

	private static final Logger log = LogManager.getLogger(RiderDAOImpl.class);

	public void persist(Rider transientInstance) {
		log.debug("persisting Rider instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Rider instance) {
		log.debug("attaching dirty Rider instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rider instance) {
		log.debug("attaching clean Rider instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Rider persistentInstance) {
		log.debug("deleting Rider instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rider merge(Rider detachedInstance) {
		log.debug("merging Rider instance");
		try {
			Rider result = (Rider) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
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
			Rider instance = (Rider) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.Rider", id);
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
			List<Rider> results = (List<Rider>) getSessionFactory()
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.RiderDAO#select(java.lang.String)
	 */
	public Rider select(String uid) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "From Rider R, Taxinetusers U WHERE R.RiderID = U.UserID AND U.UserID = :uid";
		Query query = session.createQuery(hql);
		query.setParameter("uid", uid);
		List<Rider> riderList = query.list();
		if (riderList.size() == 0) {
			return null;
		} else {
			return riderList.get(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.RiderDAO#updatePassword(java.lang.String,
	 * java.lang.String)
	 */
	public void updatePassword(String uid, String rePassword) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "UPDATE Taxinetusers set password = :password WHERE userId = :uid";
		Query query = session.createQuery(hql);
		query.setParameter("uid", uid);
		int result = query.executeUpdate();
		log.debug("Result update password : " + result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.RiderDAO#updateProfile(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public void updateProfile(String uid, String surName, String name,
			String countryCode, String phoneNo, String languageCode,
			String zipCode, Date date, String username) {
		Session session = getSessionFactory().getCurrentSession();
		// update Rider table
		String hql1 = "UPDATE Rider set firstName = :firstname, lastName = :lastname, phoneNo = :phoneNo,lastModifiedBy = :username, lastModifieDate = :date WHERE riderId = :uid";
		Query query = session.createQuery(hql1);
		query.setParameter("firstname", surName);
		query.setParameter("lastname", name);
		query.setParameter("zipCode", zipCode);
		query.setParameter("phoneNo", phoneNo);
		query.setParameter("username", username);
		query.setParameter("date", date);
		query.setParameter("uid", uid);
		int result = query.executeUpdate();
		log.debug("Result " + result);

		// update Taxinetusers table
		String hql2 = "UPDATE Taxinetusers set languageCode = :languageCode, countryCode = :countryCode, postalCode = :zipCode, lastModifiedBy = :username, lastModifiedDate = :date where userId = :uid ";
		Query query1 = session.createQuery(hql2);
		query1.setParameter("languageCode", languageCode);
		query1.setParameter("countryCode", countryCode);
		query1.setParameter("postalCode", zipCode);
		query1.setParameter("date", date);
		query1.setParameter("username", username);
		query1.setParameter("uid", uid);
		int result1 = query.executeUpdate();
		log.debug("Result " + result1);
	}
}
