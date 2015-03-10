package vn.co.taxinet.bo.impl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.Constants.DriverStatus;
import vn.co.taxinet.common.Constants.Message;
import vn.co.taxinet.common.Constants.TripStatus;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dao.CityDAO;
import vn.co.taxinet.dao.CurrentStatusDAO;
import vn.co.taxinet.dao.DriverDAO;
import vn.co.taxinet.dao.PaymentDAO;
import vn.co.taxinet.dao.RiderDAO;
import vn.co.taxinet.dao.TripDAO;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.dto.TripDTO;
import vn.co.taxinet.gcm.Content;
import vn.co.taxinet.gcm.POST2GCM;
import vn.co.taxinet.orm.CurrentStatus;
import vn.co.taxinet.orm.Driver;
import vn.co.taxinet.orm.Payment;
import vn.co.taxinet.orm.Rider;
import vn.co.taxinet.orm.Trip;
import vn.co.taxinet.utils.Utility;

@Service
public class TripBOImpl implements TripBO {
	private static final Logger logger = LogManager.getLogger(TripBOImpl.class);
	@Autowired
	private TripDAO tripDAO;
	@Autowired
	private RiderDAO riderDAO;
	@Autowired
	private DriverDAO driverDAO;
	@Autowired
	private CityDAO cityDAO;
	@Autowired
	private PaymentDAO paymentDAO;
	@Autowired
	private CurrentStatusDAO currentStatusDAO;

	public void setCurrentStatusDAO(CurrentStatusDAO currentStatusDAO) {
		this.currentStatusDAO = currentStatusDAO;
	}

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
	public TripDTO createTrip(String riderId, String driverId,
			String startLongitude, String startLatitude, String stopLongitude,
			String stopLatitude) throws TNException {
		if (driverId == null || driverId.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		if (riderId == null || riderId.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		double log1, log2, lat1, lat2;
		try {
			log1 = Double.parseDouble(startLongitude);
			lat1 = Double.parseDouble(startLatitude);
			log2 = Double.parseDouble(stopLongitude);
			lat2 = Double.parseDouble(stopLatitude);
		} catch (NumberFormatException e) {
			throw new TNException("Invalid location");
		}
		Rider rider = riderDAO.findById(riderId);
		if (rider == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}
		Driver driver = driverDAO.findDriverById(driverId);
		if (driver == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}
		if (!driver.getCurrentstatus().getCurrentStatus()
				.equalsIgnoreCase(DriverStatus.AVAIABLE)) {
			TripDTO tripDTO = new TripDTO();
			tripDTO.setMessage(Message.FAIL);
			return tripDTO;
		}
		Trip trip = new Trip();
		UUID id = UUID.randomUUID();
		trip.setRequestId(id.toString());
		trip.setDriver(driver);
		trip.setRider(rider);
		trip.setVehicle(driver.getVehicle());
		trip.setStartLatitude(lat1);
		trip.setStartLongtitude(log1);
		trip.setEndLatitude(lat2);
		trip.setEndLongtitude(log2);
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

		driver.getCurrentstatus().setCurrentStatus(DriverStatus.OUT_OF_SERVICE);

		Content content = createNotification(driver.getRegId(),
				rider.getFirstName() + " " + rider.getLastName(), rider
						.getTaxinetusers().getImage(), startLongitude,
				startLatitude, String.valueOf(id), TripStatus.NEW_TRIP,
				rider.getMobileNo());

		String message = POST2GCM.post(Constants.apiKey, content);
		if (message.equals(Constants.Message.SUCCESS)) {
			TripDTO tripDTO = new TripDTO();
			tripDTO.setId(String.valueOf(id));
			tripDTO.setMessage(Message.SUCCESS);
			return tripDTO;
		}
		throw new TNException(Constants.Message.FAIL);
	}

	@Transactional
	public MessageDTO updateTrip(String requestId, String userId, String status)
			throws TNException {
		if (requestId == null || requestId.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		if (userId == null || userId.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		if (status == null || status.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		Trip trip = tripDAO.findTripById(requestId);
		if (trip == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}

		// náº¿u ngÆ°á»�i thay upadte trip lÃ  driver
		if (userId.equalsIgnoreCase(trip.getDriver().getDriverId())) {
			// update trip
			int result = tripDAO.updateTripStatus(requestId, userId, status);
			// náº¿u status lÃ  cancel thÃ¬ gá»­i thÃ´ng bÃ¡o cancel Ä‘áº¿n
			// rider
			if (result == 0) {
				return new MessageDTO(Constants.Message.REQUEST_NOT_FOUND);
			} else if (result > 1) {
				return new MessageDTO(Constants.Message.ERROR);
			}
			if (status.equalsIgnoreCase(Constants.TripStatus.CANCELLED)) {
				// CurrentStatus currentStatus =
				// trip.getDriver().getCurrentstatus();
				// currentStatus.setCurrentStatus(DriverStatus.BUSY);
				// currentStatusDAO.update(currentStatus);
				// send notification to rider
				Content content = createNotification(
						trip.getRider().getRegId(), null, null, null, null,
						null, TripStatus.CANCELLED, null);
				POST2GCM.post(Constants.apiKey, content);
			} else {
				// náº¿u k thÃ¬ cáº­p nháº­t trang thÃ¡i cá»§a driver
				CurrentStatus currentStatus = currentStatusDAO.findById(userId);
				if (currentStatus == null) {
					throw new TNException(Message.DATA_NOT_FOUND);
				}

				// náº¿u lÃ  picking thÃ¬ tráº¡ng thÃ¡i lÃ  BUSY
				if (status.equalsIgnoreCase(Constants.TripStatus.PICKING)
						|| status.equalsIgnoreCase(Constants.TripStatus.PICKED)) {
					currentStatus.setCurrentStatus(Constants.DriverStatus.BUSY);
					currentStatusDAO.update(currentStatus);
					// rá»“i gá»­i tráº¡ng thÃ¡i picking cho rider
					Content content = createNotification(trip.getRider()
							.getRegId(), null, null, null, null, null, status,
							null);
					String message = POST2GCM.post(Constants.apiKey, content);
				}
			}

			return new MessageDTO(status);
		}
		if (userId.equalsIgnoreCase(trip.getRider().getRiderId())) {
			// náº¿u ngÆ°á»�i gá»­i lÃ  rider há»§y chuyáº¿n Ä‘i
			// thÃ¬ gá»­i thÃ´ng bÃ¡o Ä‘áº¿n cho driver

			int result = tripDAO.updateTripStatus(requestId, userId, status);
			Content content = createNotification(trip.getDriver().getRegId(),
					null, null, null, null, requestId, TripStatus.CANCELLED,
					null);
			String message = POST2GCM.post(Constants.apiKey, content);
			if (result == 0) {
				return new MessageDTO(Constants.Message.REQUEST_NOT_FOUND);
			}
			if (result > 1) {
				return new MessageDTO(Constants.Message.ERROR);
			}
			if (message.equalsIgnoreCase(Constants.FAILED)) {
				return new MessageDTO(Constants.Message.ERROR);
			}
			return new MessageDTO(status);

		}
		throw new TNException(Message.DATA_NOT_FOUND);
	}

	public static Content createNotification(String regId, String name,
			String image, String longitude, String latitude, String requestId,
			String status, String phone) {

		Content c = new Content();
		c.addRegId(regId);
		c.createNotification(image, name, longitude, latitude, requestId,
				status, phone);

		return c;
	}

	public MessageDTO completeTrip(String requestId, String driverId,
			String cost) throws TNException {
		if (requestId == null || requestId.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		if (driverId == null || driverId.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		double cost2 = 0;
		try {
			cost2 = Double.parseDouble(cost);
		} catch (NumberFormatException ex) {
			throw new TNException(Message.NUMBER_FORMAT_EXCEPTION);
		}
		Trip trip = tripDAO.findTripById(requestId);
		if (trip == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}
		Payment payment = new Payment();

		trip.setStatus(TripStatus.COMPLETED);
		trip.setFee(cost2);
		trip.setPayment(payment);
		tripDAO.update(trip);

		return new MessageDTO(Constants.SUCCESS);
	}
}
