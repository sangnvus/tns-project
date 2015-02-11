package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.UserGroupDAO;
import vn.co.taxinet.orm.UserGroup;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class UserGroup.
 * @see vn.co.taxinet.dao.UserGroup
 * @author Hibernate Tools
 */
@Service(value="userGroupDAO")
@Transactional
public class UserGroupDAOImpl extends BaseDAOImpl implements UserGroupDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -311300218582809494L;
	private static final Logger log = LogManager.getLogger(UserGroupDAOImpl.class);


	public void persist(UserGroup transientInstance) {
		log.debug("persisting UserGroup instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserGroup instance) {
		log.debug("attaching dirty UserGroup instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserGroup instance) {
		log.debug("attaching clean UserGroup instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UserGroup persistentInstance) {
		log.debug("deleting UserGroup instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserGroup merge(UserGroup detachedInstance) {
		log.debug("merging UserGroup instance");
		try {
			UserGroup result = (UserGroup) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserGroup findById(java.lang.String id) {
		log.debug("getting UserGroup instance with id: " + id);
		try {
			UserGroup instance = (UserGroup) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.UserGroup", id);
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

	public List<UserGroup> findByExample(UserGroup instance) {
		log.debug("finding UserGroup instance by example");
		try {
			List<UserGroup> results = (List<UserGroup>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.UserGroup")
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
