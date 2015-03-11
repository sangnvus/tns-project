package vn.co.taxinet.dao.impl;

// Generated Mar 7, 2015 6:16:51 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import vn.co.taxinet.dao.AgentDAO;
import vn.co.taxinet.orm.Agent;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Agent.
 * @see vn.co.taxinet.orm.Agent
 * @author Hibernate Tools
 */
public class AgentDAOImpl extends BaseDAOImpl implements AgentDAO{

	private static final Logger log = LogManager.getLogger(AgentDAOImpl.class);

	public void persist(Agent transientInstance) {
		log.debug("persisting Agent instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Agent instance) {
		log.debug("attaching dirty Agent instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Agent instance) {
		log.debug("attaching clean Agent instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Agent persistentInstance) {
		log.debug("deleting Agent instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Agent merge(Agent detachedInstance) {
		log.debug("merging Agent instance");
		try {
			Agent result = (Agent) getSessionFactory().getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Agent findById(java.lang.String id) {
		log.debug("getting Agent instance with id: " + id);
		try {
			Agent instance = (Agent) getSessionFactory().getCurrentSession().get(
					"vn.co.taxinet.orm.Agent", id);
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

	public List<Agent> findByExample(Agent instance) {
		log.debug("finding Agent instance by example");
		try {
			List<Agent> results = (List<Agent>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Agent")
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
