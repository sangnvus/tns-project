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

	public void createData(String riderid, String riderimage,
			String ridername, String longitude, String latitude,
			String requestId) {
		if (data == null)
			data = new HashMap<String, String>();
		data.put("riderid", "1");
		data.put("riderimage", "12");
		data.put("ridername", "Dao trung hieu");
		data.put("longitude", "11");
		data.put("latitude", "11");
		data.put("requestId", "11");
	}
}