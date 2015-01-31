package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.InvitationLogDAO;
import vn.co.taxinet.orm.InvitationLog;
import vn.co.taxinet.orm.InvitationLogID;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InvitationLog.
 * @see vn.co.taxinet.dao.InvitationLog
 * @author Hibernate Tools
 */
@Service(value="invitationLogDAO")
@Transactional
public class InvitationLogDAOImpl extends BaseDAOImpl implements InvitationLogDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8977552420279486169L;
	private static final Logger log = LogManager.getLogger(InvitationLogDAOImpl.class);

	public void persist(InvitationLog transientInstance) {
		log.debug("persisting InvitationLog instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InvitationLog instance) {
		log.debug("attaching dirty InvitationLog instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvitationLog instance) {
		log.debug("attaching clean InvitationLog instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InvitationLog persistentInstance) {
		log.debug("deleting InvitationLog instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvitationLog merge(InvitationLog detachedInstance) {
		log.debug("merging InvitationLog instance");
		try {
			InvitationLog result = (InvitationLog) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InvitationLog findById(InvitationLogID id) {
		log.debug("getting InvitationLog instance with id: " + id);
		try {
			InvitationLog instance = (InvitationLog) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.dao.InvitationLog",
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

	public List<InvitationLog> findByExample(InvitationLog instance) {
		log.debug("finding InvitationLog instance by example");
		try {
			List<InvitationLog> results = (List<InvitationLog>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.InvitationLog")
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
