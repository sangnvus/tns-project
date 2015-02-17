package vn.co.taxinet.dao.impl;

// Generated Feb 17, 2015 1:59:09 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.TermDAO;
import vn.co.taxinet.orm.Term;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Term.
 * @see vn.co.taxinet.orm.Term
 * @author Hibernate Tools
 */
@Transactional
public class TermDAOImpl extends BaseDAOImpl implements TermDAO{

	private static final Logger log = LogManager.getLogger(TermDAOImpl.class);

	public void persist(Term transientInstance) {
		log.debug("persisting Term instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Term instance) {
		log.debug("attaching dirty Term instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Term instance) {
		log.debug("attaching clean Term instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Term persistentInstance) {
		log.debug("deleting Term instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Term merge(Term detachedInstance) {
		log.debug("merging Term instance");
		try {
			Term result = (Term) getSessionFactory().getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Term findById(java.lang.String id) {
		log.debug("getting Term instance with id: " + id);
		try {
			Term instance = (Term) getSessionFactory().getCurrentSession().get(
					"vn.co.taxinet.orm.Term", id);
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

	public List<Term> findByExample(Term instance) {
		log.debug("finding Term instance by example");
		try {
			List<Term> results = (List<Term>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Term")
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
