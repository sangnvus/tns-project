package vn.co.taxinet.mobile.fragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.utils.Constants;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MapsFragment extends Fragment {

	private View rootView;
	// Google Map
	private GoogleMap googleMap;

	private String TAG = MapsFragment.class.getSimpleName();
	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req";
	private Timer timer;
	private MyTimerTask task;
	private RelativeLayout mError;
	private int count = 0;
	private Rider rider = null;
	private TextView mRiderName;
	private LinearLayout mReqestLayout;
	private BroadcastReceiver receiver;
	private Button mAccept, mDeni;

	public MapsFragment(Rider rider) {
		this.rider = rider;
	}

	public MapsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (rootView != null) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
				parent.removeView(rootView);
		}
		try {
			rootView = inflater
					.inflate(R.layout.fragment_map, container, false);
			// initialize();
			try {
				// Loading map
				initilizeMap();
				initialize();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}
		return rootView;

	}

	public class mHandleMessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			Rider rider = new Rider();
			rider.setId(Integer.parseInt(intent.getStringExtra(Constants.RIDER_ID)));
			rider.setName(intent.getStringExtra(Constants.RIDER_NAME));
			rider.setImage(intent.getStringExtra(Constants.RIDER_IMAGE));
			rider.setLongitude(Double.parseDouble(intent
					.getStringExtra(Constants.LONGITUDE)));
			rider.setLatitude(Double.parseDouble(intent
					.getStringExtra(Constants.LATITUDE)));
			Toast.makeText(rootView.getContext(), "đù", Toast.LENGTH_LONG)
					.show();
			mReqestLayout.setVisibility(View.VISIBLE);
		}
	};



	private void initialize() {
		IntentFilter filter = new IntentFilter(Constants.BroadcastAction.DISPLAY_REQUEST);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		receiver = new mHandleMessageReceiver();
		getActivity().registerReceiver(receiver, filter);

		mRiderName = (TextView) rootView.findViewById(R.id.tv_rider_name);
		mReqestLayout = (LinearLayout) rootView
				.findViewById(R.id.request_layout);
		mReqestLayout.setVisibility(View.GONE);
		mAccept = (Button) rootView.findViewById(R.id.accept);
		mAccept.setOnClickListener(accept);
		mDeni = (Button) rootView.findViewById(R.id.deni);
		mDeni.setOnClickListener(deni);

		if (rider != null) {
			mRiderName.setText(rider.getName());
			mReqestLayout.setVisibility(View.VISIBLE);
		} else {
			Toast.makeText(rootView.getContext(), "Đù", Toast.LENGTH_LONG)
					.show();
		}

		// Releasing wake lock
		// timer = new Timer();
		// task = new MyTimerTask();
		// cd = new ConnectionDetector(getActivity().getApplicationContext());
		// mError = (RelativeLayout) rootView
		// .findViewById(R.id.relativelayout_connection);
		// mError.setVisibility(View.GONE);
		// timer.schedule(task, 3000, 1000);
		settingMap();
	}

	OnClickListener accept = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mReqestLayout.setVisibility(View.GONE);
		}
	};

	OnClickListener deni = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mReqestLayout.setVisibility(View.GONE);
		}
	};

	public void settingMap() {
		googleMap.setMyLocationEnabled(true);
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
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
				Toast.makeText(getActivity().getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initilizeMap();
	}

	private void makeJsonObjReq() {
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
				Constants.URL.GET_TERM, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						Toast.makeText(getActivity().getApplicationContext(),
								response.toString(), Toast.LENGTH_LONG).show();

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
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

	/*
	 * This class check internet connection after 3s and send position to server
	 * after 60 s
	 */

	private class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			count++;

			getActivity().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (count % 10 == 0) {
						if (true) {
							if (mError.getVisibility() == View.GONE) {
								mError.startAnimation(slideToBottom(rootView,
										mError.getHeight()));
								mError.setVisibility(View.VISIBLE);
							}
						} else {
							if (mError.getVisibility() == View.VISIBLE) {
								mError.startAnimation(slideToTop(rootView,
										mError.getHeight()));
								mError.setVisibility(View.GONE);
							}
						}
					}
					if (count == 60) {
						// send position to server

						count = 0;
					}
				}
			});

		}
	}

	// To animate view slide out from bottom to top
	public TranslateAnimation slideToTop(View view, float y) {
		TranslateAnimation animate = new TranslateAnimation(0, 0, y, 0);
		animate.setDuration(20000);
		animate.setFillAfter(true);
		return animate;
	}

	public TranslateAnimation slideToBottom(View view, float y) {
		TranslateAnimation animate = new TranslateAnimation(0, 0, 0, y);
		animate.setDuration(20000);
		animate.setFillAfter(true);
		return animate;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		timer = null;
		MapFragment f = (MapFragment) getFragmentManager().findFragmentById(
				R.id.map);
		if (f != null)
			getFragmentManager().beginTransaction().remove(f).commit();
	}
}
