package vn.co.taxinet.bo.impl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.FunctionalException;
import vn.co.taxinet.common.exception.SystemException;
import vn.co.taxinet.common.exception.TNSException;
import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Address;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.TaxiNetUsers;
import vn.co.taxinet.utils.Utility;

@Service
public class RiderBOImpl implements RiderBO {
	private final static String THIS = "RiderBOImpl";
	private static final Logger logger = LogManager
			.getLogger(RiderBOImpl.class);
	// Refer to taxiNetUserDAO declared in ApplicationContext of Spring
	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;
	
	private RiderDAO riderDAO;
	/**
	 * @return the riderDAO
	 */
	public RiderDAO getRiderDAO() {
		return riderDAO;
	}

	/**
	 * @param riderDAO the riderDAO to set
	 */
	public void setRiderDAO(RiderDAO riderDAO) {
		this.riderDAO = riderDAO;
	}

	/**
	 * @return the taxiNetUserDAO
	 */
	public TaxiNetUserDAO getTaxiNetUserDAO() {
		return taxiNetUserDAO;
	}

	/**
	 * @param taxiNetUserDAO the taxiNetUserDAO to set
	 */
	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void register(Rider rider) throws TNSException {
		final String begin = " BEGIN: params ({0}, {1}) ";
		logger.info(begin, rider.getFirstName(), rider.getLastName());
		try {
			// Insert parent tables first, then insert child tables
			// 1. Insert user table
			// Check if user name exist in DB
			TaxiNetUsers user = rider.getTaxinetusers();
			TaxiNetUsers oldUser = taxiNetUserDAO.select(user.getUsername());
			if (oldUser != null) {
				throw new FunctionalException(THIS, "Rider User is existing",
						Constants.Errors.DUPLICATED_ERROR);
			}
			UUID id = UUID.randomUUID();
			user.setUserId(id.toString());
			// User Name always is in lower case
			user.setUsername(user.getUsername().toLowerCase());
			user.setCreatedDate(Utility.getCurrentDateTime());
			user.setCreatedBy(user.getUsername());
			user.setLastModifiedDate(Utility.getCurrentDateTime());
			user.setLastModifiedBy(user.getUsername());
			taxiNetUserDAO.insert(user);

			// 2. Insert Home Address if not null
			if (rider.getAddressByHomeAddressId() != null) {
				UUID homeAddressId = UUID.randomUUID();
				Address homeAddress = rider.getAddressByHomeAddressId();
				homeAddress.setAddressId(homeAddressId.toString());
				homeAddress.setCreatedDate(Utility.getCurrentDateTime());
				homeAddress.setLastModifiedDate(Utility.getCurrentDateTime());
				homeAddress.setCreatedBy(user.getUsername());
				homeAddress.setLastModifiedBy(user.getUsername());
				taxiNetUserDAO.insert(homeAddress);

			}

			// 3. Insert Office Address if not null
			if (rider.getAddressByOfficeAddressId() != null) {
				UUID officeAddressId = UUID.randomUUID();
				Address offAddress = rider.getAddressByHomeAddressId();
				offAddress.setAddressId(officeAddressId.toString());
				offAddress.setCreatedDate(Utility.getCurrentDateTime());
				offAddress.setLastModifiedDate(Utility.getCurrentDateTime());
				offAddress.setCreatedBy(user.getUsername());
				offAddress.setLastModifiedBy(user.getUsername());
				taxiNetUserDAO.insert(offAddress);
			}

			// 4. Insert data into Rider table
			rider.setRiderId(id.toString());
			rider.setCreatedDate(Utility.getCurrentDateTime());
			rider.setLastModifiedDate(Utility.getCurrentDateTime());
			rider.setCreatedBy(user.getUsername());
			rider.setLastModifiedBy(user.getUsername());
			taxiNetUserDAO.insert(rider);
		} catch (TNSException tnex) {
			throw tnex;
		} catch (Throwable t) {
			throw new SystemException(THIS, t);
		}
		// 5. Return
		logger.info(" END: params ({0}, {1} )", rider.getFirstName(),
				rider.getLastName());
	}

	/* (non-Javadoc)
	 * @see vn.co.taxinet.bo.RiderBO#test(vn.co.taxinet.orm.Rider)
	 */
	@Transactional
	public void test(Rider rider) {
		if(rider == null){
			System.out.println("KhÃ´ng cÃ³ ngÆ°á»�i dÃ¹ng");
		} else {
			System.out.println("CÃ³ ngÆ°á»�i dÃ¹ng");
		}
		
	}

}
