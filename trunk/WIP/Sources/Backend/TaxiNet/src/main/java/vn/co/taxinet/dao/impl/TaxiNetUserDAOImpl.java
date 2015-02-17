/**
 * 
 */
package vn.co.taxinet.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author DEV
 *
 */
@Transactional
public class TaxiNetUserDAOImpl extends BaseDAOImpl implements TaxiNetUserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(TaxiNetUserDAOImpl.class);
	@Transactional(readOnly = true)
	public TaxiNetUsers select(String uid) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = " FROM TaxiNetUsers U WHERE U.username = :userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", uid);
		// TODO Auto-generated method stub
		List<TaxiNetUsers> result = query.list();
		TaxiNetUsers user = new TaxiNetUsers();
		if (!result.isEmpty()) {
			user = result.get(0);
		}
		return user;
	}

	@Transactional(readOnly = true)
	public TaxiNetUsers findTNUById(String id) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = " FROM TaxiNetUsers U WHERE U.userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", id);
		// TODO Auto-generated method stub
		List<TaxiNetUsers> result = query.list();
		TaxiNetUsers user = new TaxiNetUsers();
		if (!result.isEmpty()) {
			user = result.get(0);
		}
		return user;
	}

	public boolean registerTaxiNetUser(TaxiNetUsers user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean registerRider(Rider rider) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.TaxiNetUserDAO#listAllUsers()
	 */
	public List<TaxiNetUsers> listAllUsers(String username, String email) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from taxinetusers";
		String hql1 = "	inner join usergroup on taxinetusers.groupcode = usergroup.groupcode";
		String hql2 = " inner join rider on taxinetusers.userid = rider.riderid";
		String hql3 = "	inner join driver on taxinetusers.userid = driver.driverid";
		String hql4 = "	inner join company on taxinetusers.companyid = company.companyid";
		String hql5 = "	where taxinetusers.username = :username and taxinetusers.email =:email";
		String hqlQuery = hql.concat(hql1).concat(hql2).concat(hql3)
				.concat(hql4).concat(hql5);
		Query query = session.createQuery(hqlQuery);
		query.setParameter("userName", username.toLowerCase());
		query.setParameter("email", email);
		List<TaxiNetUsers> listUsers = query.list();
		if (listUsers.size() > 0) {
			return listUsers;
		} else
			return new ArrayList<TaxiNetUsers>();

	}

	public List<TaxiNetUsers> loginAuth(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TaxiNetUsers> paginationList(int page, int numberOfElement) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from TaxiNetUsers";
		Query query = session.createQuery(hql);
		query.setFirstResult((page - 1) * numberOfElement + 1);
		query.setMaxResults(numberOfElement);
		List<TaxiNetUsers> result;
		result = query.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.TaxiNetUserDAO#findByID(java.lang.String)
	 */
	@Transactional
	public TaxiNetUsers findByID(String id) {
		log.debug("getting TaxiNetUsers instance with id: " + id);
		try {
			TaxiNetUsers instance = (TaxiNetUsers) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.orm.TaxiNetUsers",
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
	public void persist(TaxiNetUsers transientInstance) {
		log.debug("persisting TaxiNetUsers instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaxiNetUsers instance) {
		log.debug("attaching dirty TaxiNetUsers instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaxiNetUsers instance) {
		log.debug("attaching clean TaxiNetUsers instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaxiNetUsers persistentInstance) {
		log.debug("deleting TaxiNetUsers instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaxiNetUsers merge(TaxiNetUsers detachedInstance) {
		log.debug("merging TaxiNetUsers instance");
		try {
			TaxiNetUsers result = (TaxiNetUsers) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaxiNetUsers findById(java.lang.String id) {
		log.debug("getting TaxiNetUsers instance with id: " + id);
		try {
			TaxiNetUsers instance = (TaxiNetUsers) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.orm.TaxiNetUsers",
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

	public List<TaxiNetUsers> findByExample(TaxiNetUsers instance) {
		log.debug("finding TaxiNetUsers instance by example");
		try {
			List<TaxiNetUsers> results = (List<TaxiNetUsers>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.TaxiNetUsers")
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
