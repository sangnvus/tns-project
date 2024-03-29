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

import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.orm.Driver;

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
		String hql1 = "SELECT D FROM Driver D, CurrentStatus C ";
		String hql2 = "WHERE D.driverId = C.driverId AND C.currentStatus = 'AC' AND D.vehicle is not null";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		System.out.println(" HQL : " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		List<Driver> result = query.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.DriverDAO#findDriverByCompanyID(java.lang.String)
	 */
	@Transactional
	public List<DriverDTO> findDriverByCompanyID(String companyID,
			int pageIndex, int pageSize) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		String hql1 = "Select D FROM TaxiNetUsers U, Driver D, CurrentStatus CS, Vehicle V";
		String hql2 = " WHERE U.company.companyId = :companyId AND D.driverId = CS.driverId";
		String hql3 = " AND U.userId = D.driverId and D.vehicle.vehicleId = V.vehicleId";
		String hql4 = " AND CS.currentStatus = 'AC'";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		stringBuilder.append(hql3);
		stringBuilder.append(hql4);
		System.out.println("HQL : " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("companyId", Integer.parseInt(companyID));
		query.setFirstResult(pageIndex);
		query.setMaxResults(pageSize);
		List<Driver> driverList = query.list();
		if (driverList != null) {
			String plate = driverList.get(0).getVehicle().getPlate();
			List<DriverDTO> listDriverDTO = new ArrayList<DriverDTO>();
			for (int i = 0; i < driverList.size(); i++) {
				DriverDTO driverDTO = new DriverDTO();
				driverDTO.setFirstName(driverList.get(i).getFirstName());
				driverDTO.setLastName(driverList.get(i).getLastName());
				driverDTO.setPlate(driverList.get(0).getVehicle().getPlate());
				driverDTO.setCurrentStatus(driverList.get(0).getCurrentstatus()
						.getCurrentStatus());
				driverDTO.setCurrentLocation(driverList.get(0)
						.getCurrentstatus().getCurrentLocation());
				listDriverDTO.add(driverDTO);
			}
			return listDriverDTO;
		} else {
			return new ArrayList<DriverDTO>();
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
		String hql1 = "Select D FROM TaxiNetUsers U, Driver D, CurrentStatus CS, Vehicle V";
		String hql2 = " WHERE U.company.companyId = :companyId AND D.driverId = CS.driverId";
		String hql3 = " AND U.userId = D.driverId and D.vehicle.vehicleId = V.vehicleId";
		String hql4 = " AND CS.currentStatus = 'AC'";
		stringBuilder.append(hql1);
		stringBuilder.append(hql2);
		stringBuilder.append(hql3);
		stringBuilder.append(hql4);
		log.debug("HQL " + stringBuilder.toString());
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("companyId", Integer.parseInt(companyID));
		List<Driver> driverList = query.list();
		String i = driverList.get(0).getVehicle().getPlate();
		return driverList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.DriverDAO#getAllDriver(java.lang.String, int, int)
	 */
	@Transactional
	public List<DriverDTO> getAllDriver(String companyID, int pageIndex,
			int pageSize) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT D from Driver D, TaxiNetUsers U, Vehicle V, Country C, Language L");
		stringBuilder
				.append(" WHERE U.company.companyId = :companyId AND D.vehicle.vehicleId = V.vehicleId");
		stringBuilder
				.append(" AND U.userId = D.driverId AND U.country.code = C.code ");
		stringBuilder.append(" AND U.language.languageCode = L.languageCode");
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("companyId", Integer.parseInt(companyID));
		query.setFirstResult(pageIndex);
		query.setMaxResults(pageSize);
		List<Driver> driverList = query.list();
		if (driverList != null) {
			String plate = driverList.get(0).getVehicle().getPlate();
			List<DriverDTO> listDriverDTO = new ArrayList<DriverDTO>();
			for (int i = 0; i < driverList.size(); i++) {
				DriverDTO driverDTO = new DriverDTO();
				driverDTO.setFirstName(driverList.get(i).getFirstName());
				driverDTO.setLastName(driverList.get(i).getLastName());
				driverDTO.setPlate(driverList.get(0).getVehicle().getPlate());
				driverDTO.setEmail(driverList.get(i).getTaxinetusers()
						.getEmail());
				driverDTO.setPhoneNumber(driverList.get(i).getMobileNo());
				driverDTO.setCountry(driverList.get(i).getTaxinetusers()
						.getCountry().getName());
				driverDTO.setUsername(driverList.get(i).getTaxinetusers()
						.getUsername());
				driverDTO.setLanguage(driverList.get(i).getTaxinetusers()
						.getLanguage().getLanguageCode());
				driverDTO.setId(driverList.get(i).getDriverId());
				driverDTO.setCarTypeID(String.valueOf(driverList.get(0)
						.getVehicle().getCartype().getCarTypeId()));
				listDriverDTO.add(driverDTO);
			}
			return listDriverDTO;
		} else {
			return new ArrayList<DriverDTO>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.DriverDAO#countAllDriver(java.lang.String)
	 */
	@Transactional
	public int countAllDriver(String companyID) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT COUNT (DISTINCT D.driverId) FROM Driver D, TaxiNetUsers U , Vehicle V");
		stringBuilder
				.append(" WHERE D.driverId = U.userId AND U.company.companyId = :companyId");
		stringBuilder.append(" AND D.vehicle.vehicleId = V.vehicleId");
		Query query = session.createQuery(stringBuilder.toString());
		query.setParameter("companyId", Integer.parseInt(companyID));
		return ((Number) query.uniqueResult()).intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.co.taxinet.dao.DriverDAO#editDriverInfo(vn.co.taxinet.dto.DriverDTO)
	 */
	public String editDriverInfo(DriverDTO driverDTO) {
		Session session = getSessionFactory().getCurrentSession();

		// TODO edit language code and email in taxinetusers table
		String hql = "UPDATE TaxiNetUsers U set U.email = :email AND U.language.languageCode = :langCode WHERE U.userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("email", driverDTO.getEmail());
		query.setParameter("langCode", driverDTO.getLanguage());
		query.setParameter("userId", driverDTO.getId());
		int result = query.executeUpdate();

		// TODO edit mobileNo in driver table
		String hql1 = "UPDATE Driver D set D.mobileNo = :mobileNo WHERE D.driverId = :driverId";
		Query query1 = session.createQuery(hql1);
		query1.setParameter("driverId", driverDTO.getId());
		query1.executeUpdate();

		// TODO edit vehicle info
		String hql2 = "UPDATE Vehicle V set V.carType.carTypeId = :carTypeID WHERE V.plate = :plate";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("carTypeID",
				Integer.parseInt(driverDTO.getCarTypeID()));
		query2.setParameter("plate", driverDTO.getPlate());
		query2.executeUpdate();
		
		return String.valueOf(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.co.taxinet.dao.DriverDAO#removeDriver(java.lang.String)
	 */
	public String removeDriver(String driverID) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "UPDATE TaxiNetUsers U set U.company.companyId = null WHERE U.userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", driverID);
		query.executeUpdate();
		return Constants.SUCCESS;
	}
}
