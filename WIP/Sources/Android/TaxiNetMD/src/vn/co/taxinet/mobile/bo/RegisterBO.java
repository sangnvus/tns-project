package vn.co.taxinet.mobile.bo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.activity.LoginActivity;
import vn.co.taxinet.mobile.activity.RegisterActivity;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.utils.Const;
import vn.co.taxinet.mobile.utils.Validator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class RegisterBO {

	private static final String tag_json_obj = "register_driver_account";

	public static String checkRegisterInfo(Context context, String email,
			String password, String confirmPassword, String firstName,
			String lastName, String phoneNumber) {
		Validator validator = new Validator();
		if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
				|| TextUtils.isEmpty(confirmPassword)
				|| TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)
				|| TextUtils.isEmpty(phoneNumber)) {
			Toast.makeText(context,
					context.getString(R.string.register_blank_error),
					Toast.LENGTH_LONG).show();
			return null;
		}
		if (!validator.validateEmail(email)) {
			Toast.makeText(context, context.getString(R.string.email_error),
					Toast.LENGTH_LONG).show();
			return null;
		}
		if (!password.equals(confirmPassword)) {
			Toast.makeText(context,
					context.getString(R.string.two_password_error),
					Toast.LENGTH_LONG).show();
			return null;
		}
		if (!validator.validatePhoneNumber(phoneNumber)) {
			Toast.makeText(context,
					context.getString(R.string.phonenumber_error),
					Toast.LENGTH_LONG).show();
			return null;
		}
		return Const.SUCCESS;
	}

	public static void register(final Activity context, final String email,
			final String password, final String firstName,
			final String lastName, final String phoneNumber) {
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
				Const.URL_REGISTER_DRIVER, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							String responseCode = response
									.getString("responeseCode");
							if (responseCode.equalsIgnoreCase(Const.SUCCESS)) {
								Intent it = new Intent(context,
										LoginActivity.class);
								context.startActivity(it);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
