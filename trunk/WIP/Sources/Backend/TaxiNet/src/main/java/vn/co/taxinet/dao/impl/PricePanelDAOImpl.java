package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.PricePanelDAO;
import vn.co.taxinet.orm.PricePanel;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PricePanel.
 * @see vn.co.taxinet.dao.PricePanel
 * @author Hibernate Tools
 */
@Service(value="pricePanelDAO")
@Transactional
public class PricePanelDAOImpl extends BaseDAOImpl implements PricePanelDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102073331249097924L;
	private static final Logger log = LogManager.getLogger(PricePanelDAOImpl.class);


	public void persist(PricePanel transientInstance) {
		log.debug("persisting PricePanel instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PricePanel instance) {
		log.debug("attaching dirty PricePanel instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PricePanel instance) {
		log.debug("attaching clean PricePanel instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PricePanel persistentInstance) {
		log.debug("deleting PricePanel instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PricePanel merge(PricePanel detachedInstance) {
		log.debug("merging PricePanel instance");
		try {
			PricePanel result = (PricePanel) getSessionFactory().getCurrentSession()
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
			PricePanel instance = (PricePanel) getSessionFactory()
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
			List<PricePanel> results = (List<PricePanel>) getSessionFactory()
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
