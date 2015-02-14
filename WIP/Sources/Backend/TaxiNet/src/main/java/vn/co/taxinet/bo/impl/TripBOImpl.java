package vn.co.taxinet.bo.impl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dao.CityDAO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.PaymentDAO;
import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.gcm.Content;
import vn.co.taxinet.gcm.POST2GCM;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.Trip;
import vn.co.taxinet.utils.Utility;

@Service
public class TripBOImpl implements TripBO {
	private static final Logger logger = LogManager.getLogger(TripBOImpl.class);
	@Autowired
	private TripDAO tripDAO;
	private RiderDAO riderDAO;
	private DriverDAO driverDAO;
	private CityDAO cityDAO;
	private PaymentDAO paymentDAO;

	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public void setTripDAO(TripDAO tripDAO) {
		this.tripDAO = tripDAO;
	}

	public void setRiderDAO(RiderDAO riderDAO) {
		this.riderDAO = riderDAO;
	}

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	@Transactional
	public Trip createTrip(String riderId, String driverId, String longitude,
			String latitude) {
		Rider rider = riderDAO.findById(riderId);
		Driver driver = driverDAO.findById(driverId);
		Trip trip = new Trip();
		UUID id = UUID.randomUUID();
		trip.setRequestId(id.toString());
		trip.setDriver(driver);
		trip.setRider(rider);
		trip.setVehicle(driver.getVehicle());
		// hard code
		trip.setCity(cityDAO.findById(1));
		trip.setPayment(paymentDAO.findById(1));

		trip.setStatus(Constants.TripStatus.NEW_TRIP);
		trip.setRequestDatetime(Utility.getCurrentDateTime());
		trip.setCreatedBy(rider.getRiderId());
		trip.setCreatedDate(Utility.getCurrentDateTime());
		trip.setLastModifiedBy(rider.getRiderId());
		trip.setLastModifiedDate(Utility.getCurrentDateTime());
		tripDAO.insert(trip);

		Content content = createContent(driver.getDriverId());

		POST2GCM.post(Constants.apiKey, content);
		return trip;
	}

	public String updateTrip(String requestId, String userId, String status) {
		String message = tripDAO.updateTripStatus(requestId, userId, status);
		return message;
	}

	public static Content createContent(String regId) {

		Content c = new Content();
		c.addRegId(regId);
		c.createData("1", "url", "dao trung hieu", "12", "12", "100");

		return c;
	}
}
