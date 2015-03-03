package vn.co.taxinet.mobile.bo;

import org.json.JSONException;
import org.json.JSONObject;

public class GetDriverBO {
	public int parseJson(JSONObject response) {
		try {
			int responseCode = response.getInt("responseCode");
			return responseCode;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
