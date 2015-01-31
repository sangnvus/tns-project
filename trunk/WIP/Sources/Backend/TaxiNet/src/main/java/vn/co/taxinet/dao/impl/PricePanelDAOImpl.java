package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.PricePanelDAO;
import vn.co.taxinet.orm.PricePanel;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PricePanel.
 * @see vn.co.taxinet.dao.PricePanel
 * @author Hibernate Tools
 */
public class PricePanelDAOImpl implements PricePanelDAO{

	private static final Log log = LogFactory.getLog(PricePanelDAOImpl.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(PricePanel transientInstance) {
		log.debug("persisting PricePanel instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PricePanel instance) {
		log.debug("attaching dirty PricePanel instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PricePanel instance) {
		log.debug("attaching clean PricePanel instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PricePanel persistentInstance) {
		log.debug("deleting PricePanel instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PricePanel merge(PricePanel detachedInstance) {
		log.debug("merging PricePanel instance");
		try {
			PricePanel result = (PricePanel) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PricePanel findById(java.lang.Integer id) {
		log.debug("getting PricePanel instance with id: " + id);
		try {
			PricePanel instance = (PricePanel) sessionFactory
					.getCurrentSession()
					.get("vn.co.taxinet.dao.PricePanel", id);
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

	public List<PricePanel> findByExample(PricePanel instance) {
		log.debug("finding PricePanel instance by example");
		try {
			List<PricePanel> results = (List<PricePanel>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.PricePanel")
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
