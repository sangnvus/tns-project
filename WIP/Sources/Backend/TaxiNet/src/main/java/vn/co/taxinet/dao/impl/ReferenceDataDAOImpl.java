package vn.co.taxinet.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;

import vn.co.taxinet.dao.ReferenceDataDAO;
import vn.co.taxinet.orm.ReferenceData;
import vn.co.taxinet.orm.ReferenceDataID;
import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="referenceDataDAO")
@Transactional
public class ReferenceDataDAOImpl extends BaseDAOImpl implements ReferenceDataDAO {
	private static final Logger log = LogManager.getLogger(ReferenceDataDAOImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8297833297307435151L;

	@Transactional(readOnly=true)
	public List<ReferenceData> selectAll() {
		Session session = getSessionFactory().getCurrentSession();
		String hql = " FROM ReferenceData R ";
		Query query = session.createQuery(hql);
		List<ReferenceData> result = query.list();
		return result;
	}

	public void persist(ReferenceData transientInstance) {
		log.debug("persisting ReferenceData instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferenceData instance) {
		log.debug("attaching dirty ReferenceData instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferenceData instance) {
		log.debug("attaching clean ReferenceData instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ReferenceData persistentInstance) {
		log.debug("deleting ReferenceData instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferenceData merge(ReferenceData detachedInstance) {
		log.debug("merging ReferenceData instance");
		try {
			ReferenceData result = (ReferenceData) getSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ReferenceData findById(ReferenceDataID id) {
		log.debug("getting ReferenceData instance with id: " + id);
		try {
			ReferenceData instance = (ReferenceData) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.dao.ReferenceData",
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

	public List<ReferenceData> findByExample(ReferenceData instance) {
		log.debug("finding ReferenceData instance by example");
		try {
			List<ReferenceData> results = (List<ReferenceData>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.ReferenceData")
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
