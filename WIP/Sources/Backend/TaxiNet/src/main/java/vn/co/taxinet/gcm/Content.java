package vn.co.taxinet.gcm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5321996977622412871L;
	private List<String> registration_ids;

	public List<String> getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	private Map<String, String> data;

	public void addRegId(String regId) {
		if (registration_ids == null)
			registration_ids = new LinkedList<String>();
		registration_ids.add(regId);
	}

	public void createTripNotification(String image, String name,
			String startLongitude, String startLatitude, String stopLongitude,
			String stopLatitude, String estimateCost, String estimateDistance,
			String paymentMethod, String requestId, String status, String phone) {
		if (data == null)
			data = new HashMap<String, String>();
		data.put("image", image);
		data.put("name", name);
		data.put("startLongitude", startLongitude);
		data.put("startLatitude", startLatitude);
		data.put("stopLongitude", stopLongitude);
		data.put("stopLatitude", stopLatitude);
		data.put("id", requestId);
		data.put("status", status);
		data.put("phone", phone);
		data.put("estimateCost", estimateCost);
		data.put("estimateDistance", estimateDistance);
		data.put("paymentMethod", paymentMethod);
	}

	public void updateTripNotification(String status) {
		if (data == null)
			data = new HashMap<String, String>();
		data.put("status", status);
	}
}