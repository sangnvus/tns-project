package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CurrentStatusDAO;
import vn.co.taxinet.orm.CurrentStatus;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CurrentStatus.
 * @see vn.co.taxinet.dao.CurrentStatus
 * @author Hibernate Tools
 */
@Service(value="currentStatusDAO")
@Transactional
public class CurrentStatusDAOImpl extends BaseDAOImpl implements CurrentStatusDAO{

	private static final Logger log = LogManager.getLogger(CurrentStatusDAOImpl.class);


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
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
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

	public CurrentStatus findById(java.lang.String id) {
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
}
