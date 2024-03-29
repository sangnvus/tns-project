package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dao.CurrentStatusDAO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.CurrentStatus;
import vn.co.taxinet.orm.Driver;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CurrentStatus.
 * 
 * @see vn.co.taxinet.dao.CurrentStatus
 * @author Hibernate Tools
 */
@Service(value = "currentStatusDAO")
@Transactional
public class CurrentStatusDAOImpl extends BaseDAOImpl implements
		CurrentStatusDAO {

	private static final Logger log = LogManager
			.getLogger(CurrentStatusDAOImpl.class);

	public void persist(CurrentStatus transientInstance) {
		log.debug("persisting CurrentStatus instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CurrentStatus instance) {
		log.debug("attaching dirty CurrentStatus instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CurrentStatus instance) {
		log.debug("attaching clean CurrentStatus instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CurrentStatus persistentInstance) {
		log.debug("deleting CurrentStatus instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CurrentStatus merge(CurrentStatus detachedInstance) {
		log.debug("merging CurrentStatus instance");
		try {
			CurrentStatus result = (CurrentStatus) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CurrentStatus findById(String id) {
		log.debug("getting CurrentStatus instance with id: " + id);
		try {
			CurrentStatus instance = (CurrentStatus) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.orm.CurrentStatus",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			System.out.println("here");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CurrentStatus> findByExample(CurrentStatus instance) {
		log.debug("finding CurrentStatus instance by example");
		try {
			List<CurrentStatus> results = (List<CurrentStatus>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.CurrentStatus")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public MessageDTO updateCurrentStatus(String driverId, double _longitude,
			double _latitude, String _status) {

		Session session = getSessionFactory().getCurrentSession();

		StringBuilder hql = new StringBuilder();
		String hql1 = "UPDATE CurrentStatus cs ";
		String hql2 = "SET cs.currentStatus =:status, cs.currentLatitude =:latitude, cs.currentLongtitude =:longitude ";
		String hql3 = "WHERE cs.driverId =:driverId";
		hql.append(hql1);
		hql.append(hql2);
		hql.append(hql3);

		Query query = session.createQuery(hql.toString());
		query.setParameter("status", _status);
		query.setParameter("latitude", _latitude);
		query.setParameter("longitude", _longitude);
		query.setParameter("driverId", driverId);
		query.executeUpdate();

		return new MessageDTO(Constants.Message.SUCCESS);
	}

	public CurrentStatus findCurrentStatusById(String id) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		String hql1 = "FROM CurrentStatus c";
		String hql2 = "WHERE c.driverId = :driverId";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		log.debug("HQL " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("driverId", id);
		List<CurrentStatus> driverList = query.list();
		return driverList.get(0);
	}

}
