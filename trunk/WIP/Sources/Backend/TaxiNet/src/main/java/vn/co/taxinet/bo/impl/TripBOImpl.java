package vn.co.taxinet.bo.impl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dao.CityDAO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.PaymentDAO;
import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.dto.MessageDTO;
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
	public MessageDTO createTrip(String riderId, String driverId,
			String longitude, String latitude) throws TNException {
		if (driverId == null || driverId.equalsIgnoreCase("")) {
			throw new TNException("DriverID is null");
		}
		if (riderId == null || riderId.equalsIgnoreCase("")) {
			throw new TNException("RiderId is null");
		}
		try {
			Double.parseDouble(longitude);
			Double.parseDouble(latitude);
		} catch (NumberFormatException e) {
			throw new TNException("Invalid location");
		}
		Rider rider = riderDAO.findById(riderId);
		Driver driver = driverDAO.findDriverById(driverId);
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

		Content content = createRequestNotification(driver.getDriverId(), rider
				.getTaxinetusers().getImage(), rider.getFirstName() + " "
				+ rider.getLastName(), longitude, latitude, String.valueOf(id));

		String message = POST2GCM.post(Constants.apiKey, content);
		if (message.equals(Constants.Message.SUCCESS)) {
			return new MessageDTO(message);
		}
		throw new TNException(Constants.Message.FAIL);
	}

	public MessageDTO updateTrip(String requestId, String userId, String status)
			throws TNException {
		if (requestId == null || requestId.equalsIgnoreCase("")) {
			throw new TNException("requestId is null");
		}
		if (userId == null || userId.equalsIgnoreCase("")) {
			throw new TNException("userId is null");
		}
		if (status == null || status.equalsIgnoreCase("")) {
			throw new TNException("status is null");
		}
		Trip trip = tripDAO.findTripById(requestId);
		if (trip == null) {
			throw new TNException("trip is null");
		}
		if (userId.equalsIgnoreCase(trip.getDriver().getDriverId())) {
			// send notification to rider
			createCancelNotification(userId, status, requestId);
		}
		if (userId.equalsIgnoreCase(trip.getRider().getRiderId())) {
			// send notification to driver
			createCancelNotification(userId, status, requestId);
		}
		return tripDAO.updateTripStatus(requestId, userId, status);
	}

	public static Content createRequestNotification(String regId,
			String riderName, String riderImage, String longitude,
			String latitude, String requestId) {

		Content c = new Content();
		c.addRegId(regId);
		c.createTripNotification(riderImage, riderName, longitude, latitude,
				requestId);

		return c;
	}

	public static Content createCancelNotification(String regId, String status,
			String requestId) {

		Content c = new Content();
		c.addRegId(regId);
		c.createNotificationSendToRider(status, requestId);

		return c;
	}
}
