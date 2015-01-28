package vn.co.taxinet.mobile.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class DriverHomeActivity extends Activity {

	// Google Map
	private GoogleMap googleMap;

	private String TAG = DriverHomeActivity.class.getSimpleName();
	private ProgressDialog pDialog;
	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
	String cbb = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_home);

//		pDialog = new ProgressDialog(this);
//		pDialog.setMessage("Loading...");
//		pDialog.setCancelable(false);
		
		try {
			// Loading map
			initilizeMap();
			settingMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void settingMap(){
		googleMap.setMyLocationEnabled(true);
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
				Const.URL_IMAGE, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						Toast.makeText(getApplicationContext(),
								response.toString(), Toast.LENGTH_LONG).show();
						hideProgressDialog();

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
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
				params.put("name", "Androidhive");
				params.put("email", "abc@androidhive.info");
				params.put("pass", "password123");

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}

	/**
	 * Making json array request
	 * */
	private void makeJsonArryReq() {
		showProgressDialog();
		JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						Toast.makeText(getApplicationContext(),
								response.toString(), Toast.LENGTH_LONG).show();
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req, tag_json_arry);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
	}

	public void denoVolley(View v) {
		makeJsonArryReq();
	}

}
