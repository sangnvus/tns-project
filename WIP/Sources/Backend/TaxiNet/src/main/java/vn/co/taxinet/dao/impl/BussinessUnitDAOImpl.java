package vn.co.taxinet.dao.impl;

// Generated Mar 7, 2015 10:20:44 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.BussinessUnitDAO;
import vn.co.taxinet.orm.BussinessUnit;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class BussinessUnit.
 * @see vn.co.taxinet.orm.BussinessUnit
 * @author Hibernate Tools
 */
public class BussinessUnitDAOImpl extends BaseDAOImpl implements BussinessUnitDAO{

	private static final Log log = LogFactory.getLog(BussinessUnitDAOImpl.class);

	public void persist(BussinessUnit transientInstance) {
		log.debug("persisting BussinessUnit instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(BussinessUnit instance) {
		log.debug("attaching dirty BussinessUnit instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BussinessUnit instance) {
		log.debug("attaching clean BussinessUnit instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(BussinessUnit persistentInstance) {
		log.debug("deleting BussinessUnit instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BussinessUnit merge(BussinessUnit detachedInstance) {
		log.debug("merging BussinessUnit instance");
		try {
			BussinessUnit result = (BussinessUnit) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BussinessUnit findById(java.lang.String id) {
		log.debug("getting BussinessUnit instance with id: " + id);
		try {
			BussinessUnit instance = (BussinessUnit) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.orm.BussinessUnit",
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

	public List<BussinessUnit> findByExample(BussinessUnit instance) {
		log.debug("finding BussinessUnit instance by example");
		try {
			List<BussinessUnit> results = (List<BussinessUnit>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.BussinessUnit")
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
