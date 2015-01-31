package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.CarModelDAO;
import vn.co.taxinet.orm.CarModel;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CarModel.
 * @see vn.co.taxinet.dao.CarModel
 * @author Hibernate Tools
 */
@Transactional
public class CarModelDAOImpl implements CarModelDAO{

	private static final Log log = LogFactory.getLog(CarModelDAOImpl.class);

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

	public void persist(CarModel transientInstance) {
		log.debug("persisting CarModel instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CarModel instance) {
		log.debug("attaching dirty CarModel instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CarModel instance) {
		log.debug("attaching clean CarModel instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CarModel persistentInstance) {
		log.debug("deleting CarModel instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CarModel merge(CarModel detachedInstance) {
		log.debug("merging CarModel instance");
		try {
			CarModel result = (CarModel) sessionFactory.getCurrentSession()
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
			CarModel instance = (CarModel) sessionFactory.getCurrentSession()
					.get("vn.co.taxinet.dao.CarModel", id);
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
			List<CarModel> results = (List<CarModel>) sessionFactory
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.dao.CarModel")
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
