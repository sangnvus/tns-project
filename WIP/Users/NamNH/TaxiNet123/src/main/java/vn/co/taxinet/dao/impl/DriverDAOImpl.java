package vn.co.taxinet.dao.impl;

// Generated Jan 29, 2015 12:52:24 AM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.Trip;

/**
 * Home object for domain model class Driver.
 * 
 * @see vn.co.taxinet.dao.Driver
 * @author Hibernate Tools
 */
@Service(value = "driverDAO")
@Transactional
public class DriverDAOImpl extends BaseDAOImpl implements DriverDAO {
	private static final long serialVersionUID = -621994306928001928L;
	private static final Logger log = LogManager.getLogger(DriverDAOImpl.class);

	public void persist(Driver transientInstance) {
		log.debug("persisting Driver instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Driver instance) {
		log.debug("attaching dirty Driver instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Driver instance) {
		log.debug("attaching clean Driver instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance,
					LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Driver persistentInstance) {
		log.debug("deleting Driver instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Driver merge(Driver detachedInstance) {
		log.debug("merging Driver instance");
		try {
			Driver result = (Driver) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Driver findDriverById(String id) {
		log.debug("getting Driver instance with id: " + id);
		try {
			Driver instance = (Driver) getSessionFactory().getCurrentSession()
					.get("vn.co.taxinet.orm.Driver", id);
			if (instance == null) {
				return null;
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Driver> findByExample(Driver instance) {
		log.debug("finding Driver instance by example");
		try {
			List<Driver> results = (List<Driver>) getSessionFactory()
					.getCurrentSession()
					.createCriteria("vn.co.taxinet.orm.Driver")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = true)
	public List<Driver> getNearListDriver() {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		String hql1 = "SELECT D FROM Driver D, CurrentStatus C WHERE D.driverId = C.driverId ";
		String hql2 = "AND C.currentStatus = 'AC' AND D.vehicle is not null";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		System.out.println(" HQL : " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		List<Driver> result = query.list();
		return result;
	}

	public String createTrip(String riderId, String driverId) {
		Session session = getSessionFactory().getCurrentSession();
		// get rider
		String hql = "select R from Rider R where R.riderId = :rid";
		Query query = session.createQuery(hql);
		query.setParameter("rid", riderId.toLowerCase());
		List<Rider> listRider = query.list();
		Rider rider = null;
		if (!listRider.isEmpty()) {
			rider = listRider.get(0);
		}
		// get driver
		hql = "select D from Driver where D.driverId = :did";
		query = session.createQuery(hql);
		query.setParameter("did", driverId.toLowerCase());
		List<Driver> listDriver = query.list();
		Driver driver = null;
		if (!listDriver.isEmpty()) {
			driver = listDriver.get(0);
		}
		Trip trip = new Trip();
		UUID id = UUID.randomUUID();
		trip.setRequestId(id.toString());
		trip.setRider(rider);
		trip.setDriver(driver);
		return rider.getRiderId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.DriverDAO#findDriverByCompanyID(java.lang.String)
	 */
	@Transactional
	public List<Driver> findDriverByCompanyID(String companyID, int pageIndex,
			int pageSize) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		String hql1 = "Select D FROM TaxiNetUsers U, Driver D, CurrentStatus CS ";
		String hql2 = "WHERE U.company.companyId = :companyId AND D.driverId = CS.driverId";
		String hql3 = " AND U.userId = D.driverId";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		stringBuilder.append(hql3);
		System.out.println("HQL : " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("companyId", Integer.parseInt(companyID));
		query.setFirstResult(pageIndex);
		query.setMaxResults(pageSize);
		List<Driver> driverList = query.list();
		if (driverList != null) {
			return driverList;
		} else {
			return new ArrayList<Driver>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.DriverDAO#countDriverByCompanyID(java.lang.String)
	 */
	@Transactional
	public List<Driver> countDriverByCompanyID(String companyID) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		String hql1 = "Select D FROM TaxiNetUsers U, Driver D, CurrentStatus CS";
		String hql2 = " WHERE U.company.companyId = :companyId AND D.driverId = CS.driverId";
		String hql3 = " AND U.userId = D.driverId";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		stringBuilder.append(hql3);
		log.debug("HQL " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("companyId", Integer.parseInt(companyID));
		List<Driver> driverList = query.list();
		return driverList;
	}

	public List<Driver> getDriverList() {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		String hql = "Select * FROM Driver";
		Query query = session.createQuery(hql.toString());
		List<Driver> driverList = query.list();
		return driverList;
	}

}
