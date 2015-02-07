package vn.co.taxinet.mobile.bo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import vn.co.taxinet.mobile.activity.RegisterActivity;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class RegisterBO {

	private static final String tag_json_obj = "register_driver_account";

	public static String checkRegisterInfo(String email, String password,
			String confirmPassword, String firstName, String lastName,
			String phoneNumber) {

		return Const.SUCCESS;
	}

	public static void register(Activity context, final String email,
			final String password, final String firstName,
			final String lastName, final String phoneNumber) {
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
				Const.URL_REGISTER_DRIVER, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(RegisterActivity.class.getSimpleName(),
								"Error: " + error.getMessage());
					}
				}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("email", email);
				params.put("password", password);
				params.put("firstname", firstName);
				params.put("lastname", lastName);
				params.put("phonenumber", password);

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}

}
