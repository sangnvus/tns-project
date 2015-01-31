package vn.co.taxinet.mobile.bo;

import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.utils.Const;

public class LoginBO {
	public String checkLoginInfo(String account, String password) {
		return Const.SUCCESS;
	}

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
