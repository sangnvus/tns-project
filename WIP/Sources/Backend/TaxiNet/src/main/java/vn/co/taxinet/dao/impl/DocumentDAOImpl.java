package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.DocumentDAO;
import vn.co.taxinet.orm.Document;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Document.
 * @see vn.co.taxinet.dao.Document
 * @author Hibernate Tools
 */
@Service(value="documentDAO")
@Transactional
public class DocumentDAOImpl extends BaseDAOImpl implements DocumentDAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1081527119536563601L;
	private static final Logger log = LogManager.getLogger(DocumentDAOImpl.class);

	public void persist(Document transientInstance) {
		log.debug("persisting Document instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Document instance) {
		log.debug("attaching dirty Document instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Document instance) {
		log.debug("attaching clean Document instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Document persistentInstance) {
		log.debug("deleting Document instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Document merge(Document detachedInstance) {
		log.debug("merging Document instance");
		try {
			Document result = (Document) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Document findById(java.lang.Integer id) {
		log.debug("getting Document instance with id: " + id);
		try {
			Document instance = (Document) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.dao.Document", id);
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

	public List<Document> findByExample(Document instance) {
		log.debug("finding Document instance by example");
		try {
			List<Document> results = (List<Document>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.Document")
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
