package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.LanguageDAO;
import vn.co.taxinet.orm.Language;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Language.
 * @see vn.co.taxinet.dao.Language
 * @author Hibernate Tools
 */
@Transactional
public class LanguageDAOImpl implements LanguageDAO{

	private static final Log log = LogFactory.getLog(LanguageDAOImpl.class);

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

	public void persist(Language transientInstance) {
		log.debug("persisting Language instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Language instance) {
		log.debug("attaching dirty Language instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Language instance) {
		log.debug("attaching clean Language instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Language persistentInstance) {
		log.debug("deleting Language instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Language merge(Language detachedInstance) {
		log.debug("merging Language instance");
		try {
			Language result = (Language) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Language findById(java.lang.Integer id) {
		log.debug("getting Language instance with id: " + id);
		try {
			Language instance = (Language) sessionFactory.getCurrentSession()
					.get("vn.co.taxinet.dao.Language", id);
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

	public List<Language> findByExample(Language instance) {
		log.debug("finding Language instance by example");
		try {
			List<Language> results = (List<Language>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.Language")
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
