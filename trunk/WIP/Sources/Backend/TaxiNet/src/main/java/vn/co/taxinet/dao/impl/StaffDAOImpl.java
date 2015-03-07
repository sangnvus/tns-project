package vn.co.taxinet.dao.impl;

// Generated Mar 7, 2015 10:20:44 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.StaffDAO;
import vn.co.taxinet.orm.Staff;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Staff.
 * @see vn.co.taxinet.orm.Staff
 * @author Hibernate Tools
 */
public class StaffDAOImpl extends BaseDAOImpl implements StaffDAO{

	private static final Logger log = LogManager.getLogger(StaffDAOImpl.class);

	public void persist(Staff transientInstance) {
		log.debug("persisting Staff instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Staff instance) {
		log.debug("attaching dirty Staff instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Staff instance) {
		log.debug("attaching clean Staff instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Staff persistentInstance) {
		log.debug("deleting Staff instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Staff merge(Staff detachedInstance) {
		log.debug("merging Staff instance");
		try {
			Staff result = (Staff) getSessionFactory().getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Staff findById(java.lang.String id) {
		log.debug("getting Staff instance with id: " + id);
		try {
			Staff instance = (Staff) getSessionFactory().getCurrentSession().get(
					"vn.co.taxinet.orm.Staff", id);
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

	public List<Staff> findByExample(Staff instance) {
		log.debug("finding Staff instance by example");
		try {
			List<Staff> results = (List<Staff>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Staff")
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
