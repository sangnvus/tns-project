package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.VehicleDAO;
import vn.co.taxinet.orm.PricePanel;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.orm.Vehicle;
import vn.co.taxinet.utils.Utility;

/**
 * Home object for domain model class Vehicle.
 * 
 * @see vn.co.taxinet.dao.Vehicle
 * @author Hibernate Tools
 */
@Service(value = "vehicleDAO")
@Transactional
public class VehicleDAOImpl extends BaseDAOImpl implements VehicleDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252900041253117826L;
	private static final Logger log = LogManager
			.getLogger(VehicleDAOImpl.class);

	public void persist(Vehicle transientInstance) {
		log.debug("persisting Vehicle instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Vehicle instance) {
		log.debug("attaching dirty Vehicle instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Vehicle instance) {
		log.debug("attaching clean Vehicle instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Vehicle persistentInstance) {
		log.debug("deleting Vehicle instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Vehicle merge(Vehicle detachedInstance) {
		log.debug("merging Vehicle instance");
		try {
			Vehicle result = (Vehicle) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Vehicle findById(java.lang.Integer id) {
		log.debug("getting Vehicle instance with id: " + id);
		try {
			Vehicle instance = (Vehicle) getSessionFactory()
					.getCurrentSession().get("vn.co.taxinet.orm.Vehicle", id);
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

	public List<Vehicle> findByExample(Vehicle instance) {
		log.debug("finding Vehicle instance by example");
		try {
			List<Vehicle> results = (List<Vehicle>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Vehicle")
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
