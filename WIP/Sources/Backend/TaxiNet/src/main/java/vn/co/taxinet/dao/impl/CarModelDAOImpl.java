package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CarModelDAO;
import vn.co.taxinet.orm.CarModel;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CarModel.
 * @see vn.co.taxinet.dao.CarModel
 * @author Hibernate Tools
 */
@Service(value="carModelDAO")
@Transactional
public class CarModelDAOImpl extends BaseDAOImpl implements CarModelDAO{

	private static final Logger log = LogManager.getLogger(CarModelDAOImpl.class);

	public void persist(CarModel transientInstance) {
		log.debug("persisting CarModel instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CarModel instance) {
		log.debug("attaching dirty CarModel instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CarModel instance) {
		log.debug("attaching clean CarModel instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CarModel persistentInstance) {
		log.debug("deleting CarModel instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CarModel merge(CarModel detachedInstance) {
		log.debug("merging CarModel instance");
		try {
			CarModel result = (CarModel) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CarModel findById(java.lang.Integer id) {
		log.debug("getting CarModel instance with id: " + id);
		try {
			CarModel instance = (CarModel) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.CarModel", id);
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

	public List<CarModel> findByExample(CarModel instance) {
		log.debug("finding CarModel instance by example");
		try {
			List<CarModel> results = (List<CarModel>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.CarModel")
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
