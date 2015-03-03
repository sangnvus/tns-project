package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import java.util.UUID;

import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.Trip;
import vn.co.taxinet.utils.Utility;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Trip.
 * 
 * @see vn.co.taxinet.dao.Trip
 * @author Hibernate Tools
 */
@Service(value = "tripDAO")
@Transactional
public class TripDAOImpl extends BaseDAOImpl implements TripDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4960737215923716888L;
	private static final Logger log = LogManager.getLogger(TripDAOImpl.class);

	public void persist(Trip transientInstance) {
		log.debug("persisting Trip instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Trip instance) {
		log.debug("attaching dirty Trip instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Trip instance) {
		log.debug("attaching clean Trip instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Trip persistentInstance) {
		log.debug("deleting Trip instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Trip merge(Trip detachedInstance) {
		log.debug("merging Trip instance");
		try {
			Trip result = (Trip) getSessionFactory().getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Trip findTripById(java.lang.String id) {
		log.debug("getting Trip instance with id: " + id);
		try {
			Trip instance = (Trip) getSessionFactory().getCurrentSession().get(
					"vn.co.taxinet.orm.Trip", id);
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

	public MessageDTO updateTripStatus(String requestId, String userId,
			String status) {
		StringBuilder hql = new StringBuilder();
		String hql1 = "UPDATE Trip ";
		String hql2 = "SET status = :status , lastModifiedBy = :userId, lastModifiedDate = :date ";
		String hql3 = "WHERE requestId = :requestId";
		hql.append(hql1);
		hql.append(hql2);
		hql.append(hql3);

		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql.toString());
		query.setParameter("status", status);
		query.setParameter("userId", userId);
		query.setParameter("requestId", requestId);
		query.setParameter("date", Utility.getCurrentDateTime());
		int result = query.executeUpdate();
		if (result == 0) {
			return new MessageDTO(Constants.Message.REQUEST_NOT_FOUND);
		} else if (result > 1) {
			return new MessageDTO(Constants.Message.ERROR);
		} else {
			return new MessageDTO(Constants.Message.SUCCESS);
		}
	}

}
