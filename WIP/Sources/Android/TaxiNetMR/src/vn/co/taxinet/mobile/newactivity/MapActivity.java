/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.co.taxinet.mobile.newactivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.NavDrawerListAdapter;
import vn.co.taxinet.mobile.alert.AlertDialogManager;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.bo.GetDriverBO;
import vn.co.taxinet.mobile.bo.MapBO;
import vn.co.taxinet.mobile.bo.TripBO;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.gcm.GooglePlayService;
import vn.co.taxinet.mobile.googleapi.DirectionsJSONParser;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.model.Trip;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Constants.TripStatus;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Main UI for the demo app.
 */
@SuppressWarnings("deprecation")
public class MapActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	// Google Map
	private GoogleMap googleMap, googleMap_pp;

	// These tags will be used to cancel the requests
	private TextView mRiderName, mRiderPhoneNumber;
	private LinearLayout mReqestLayout;

	private BroadcastReceiver receiver;

	private Rider rider = null;
	private GooglePlayService googlePlayService;
	private MapBO mapBO;
	private TripBO tripBO;
	private GetDriverBO getDriverBO;
	private Driver driver;
	private MarkerOptions riderPosition;

	private Location mLastLocation;

	// Google client to interact with Google API
	private GoogleApiClient mGoogleApiClient;

	// boolean flag to toggle periodic location updates
	private boolean mRequestingLocationUpdates = true;

	private LocationRequest mLocationRequest;

	private String requestId;
	private String status;
	private DatabaseHandler handler;
	private String distance;

	private String driverid, riderid;
	private AlertDialogManager alert;

	Double PointLongitude;
	Double PointLatitude;

	private Boolean isPickedStartPoint = false, isPickedEndPoint = false;

	// Google Map
	private Marker lastMarker, lastMarker2, lastMarker3;
	private ProgressDialog pDialog;
	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
	String cbb = null;
	private int checkPolyLine = 0;
	private Polyline line = null;
	RelativeLayout rider_send_request_information;
	RelativeLayout rider_send_request_first_step;
	RelativeLayout no_driver_nearby;
	RelativeLayout rider_send_request_waiting_step;
	RelativeLayout rider_send_request_driver_accept;
	RelativeLayout pick_point;
	RelativeLayout pick_point_new_screen;

	DatabaseHandler databaseHandler;

	private Button send_request, pick_start_point, pick_end_point,
			pick_start_point_value, pick_end_point_value;
	private double start_lat = 1, start_lng = 2, end_lat = 3, end_lng = 4;
	LatLng rider_current_position;
	Location lastLocation;

	ArrayList<Driver> listDriver;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_blank);

		// register gcm id
		googlePlayService = new GooglePlayService(MapActivity.this);

		// create slide menu
		createSlideMenu(savedInstanceState);

		// Loading map
		initilizeMap();
		displayLocation();

		initialize();

		databaseHandler = new DatabaseHandler(getApplicationContext());
	}

	private void initialize() {
		// startLocationUpdates();
		receiver = new mHandleMessageReceiver();
		// set up broadcast receiver
		IntentFilter filter = new IntentFilter(
				Constants.BroadcastAction.DISPLAY_REQUEST);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		// receiver = new mHandleMessageReceiver();
		registerReceiver(receiver, filter);

		handler = new DatabaseHandler(this);
	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			// googleMap.setMyLocationEnabled(true);
			// googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// LocationManager locationManager = (LocationManager)
			// getSystemService(LOCATION_SERVICE);
			// Criteria criteria = new Criteria();
			// String bestProvider = locationManager.getBestProvider(criteria,
			// true);
			// Location location = locationManager
			// .getLastKnownLocation(bestProvider);
			// if (location != null) {
			// onLocationChanged(location);
			// }

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(this, "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public class mHandleMessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// lấy trạng thái của trip
			String status = intent.getStringExtra("status");
			if (status.equalsIgnoreCase(TripStatus.CANCELLED)) {
				rider_send_request_waiting_step.setVisibility(View.GONE);
				alert = new AlertDialogManager();
				alert.showAlertDialog(MapActivity.this, "Cancel", "Cancel",
						false);
				// databaseHandler.updateTrip(AppController.getTripID(),
				// TripStatus.CANCELLED);
			}
			if (status.equalsIgnoreCase(TripStatus.PICKING)) {
				rider_send_request_waiting_step.setVisibility(View.GONE);
				rider_send_request_driver_accept.setVisibility(View.VISIBLE);
				// databaseHandler.updateTrip(AppController.getTripID(),
				// TripStatus.PICKING);
			}
			if (status.equalsIgnoreCase(TripStatus.PICKED)) {
				rider_send_request_driver_accept.setVisibility(View.GONE);
				// databaseHandler.updateTrip(AppController.getTripID(),
				// TripStatus.PICKED);
			}
		}
	}

	// Display Location
	private void displayLocation() {

		rider_send_request_information = (RelativeLayout) findViewById(R.id.rider_send_request_information);
		rider_send_request_information.setVisibility(View.GONE);

		rider_send_request_first_step = (RelativeLayout) findViewById(R.id.rider_send_request_first_step);
		rider_send_request_first_step.setVisibility(View.GONE);

		no_driver_nearby = (RelativeLayout) findViewById(R.id.no_driver_nearby);
		no_driver_nearby.setVisibility(View.GONE);

		rider_send_request_waiting_step = (RelativeLayout) findViewById(R.id.rider_send_request_waiting_step);
		rider_send_request_waiting_step.setVisibility(View.GONE);

		rider_send_request_driver_accept = (RelativeLayout) findViewById(R.id.rider_send_request_driver_accept);
		rider_send_request_driver_accept.setVisibility(View.GONE);

		pick_point = (RelativeLayout) findViewById(R.id.pick_point);
		pick_point.setVisibility(View.GONE);

		pick_start_point = (Button) findViewById(R.id.btn_pick_start_point);
		pick_end_point = (Button) findViewById(R.id.btn_pick_end_point);

		pick_start_point_value = (Button) findViewById(R.id.btn_pick_start_point_value);
		pick_end_point_value = (Button) findViewById(R.id.btn_pick_end_point_value);
		pick_start_point_value.setVisibility(View.GONE);
		pick_end_point_value.setVisibility(View.GONE);

		Button cancelWaitingRequest, cancelAcceptedRequest;
		cancelWaitingRequest = (Button) findViewById(R.id.btn_rider_cancel_waiting_request);
		cancelAcceptedRequest = (Button) findViewById(R.id.btn_rider_cancel_driver_accept_request);

		// Trip trip = databaseHandler.getTripStatus();

		tripBO = new TripBO();

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		lastLocation = locationManager.getLastKnownLocation(locationManager
				.getBestProvider(criteria, true));
		Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT)
				.show();
		if (lastLocation != null) {
			getDriverBO = new GetDriverBO(this,
					"" + lastLocation.getLatitude(), ""
							+ lastLocation.getLongitude(), googleMap);

			// for (int i = 0; i < listDriver.size(); i++) {
			// Driver driver = listDriver.get(i);
			// LatLng latLng = new LatLng(driver.getLatitude(),
			// driver.getLongitude());
			// MarkerOptions markerOptions = new MarkerOptions();
			// markerOptions.title(driver.getFirstName() + " "
			// + driver.getLastName());
			// markerOptions.snippet(distance);
			// markerOptions.position(latLng);
			// googleMap.addMarker(markerOptions);
			// }

			rider_current_position = new LatLng(lastLocation.getLatitude(),
					lastLocation.getLongitude());

			riderPosition = new MarkerOptions();
			riderPosition.title("U stand here");
			riderPosition.position(rider_current_position);
			googleMap.addMarker(riderPosition);

			// LatLng Taxi1 = new LatLng(21.009809, 105.523515);
			// LatLng Taxi2 = new LatLng(21.014917, 105.530317);
			// LatLng Taxi3 = new LatLng(21.010250, 105.532162);
			// final LatLng me = new LatLng(21.013475, 105.525425);
			//
			// MarkerOptions tx1 = new MarkerOptions();
			// tx1.title("Nguyễn Văn A");
			// tx1.snippet("5KM");
			// tx1.position(Taxi1);
			// MarkerOptions tx2 = new MarkerOptions();
			// tx2.title("Nguyễn Văn B");
			// tx2.snippet("1KM");
			// tx2.position(Taxi2);
			// MarkerOptions tx3 = new MarkerOptions();
			// tx3.title("Nguyễn Văn C");
			// tx3.snippet("2KM");
			// tx3.position(Taxi3);
			// MarkerOptions me1 = new MarkerOptions();
			// me1.title("Here I stand");
			// me1.position(me);
			//
			// googleMap.addMarker(tx1);
			// googleMap.addMarker(tx2);
			// googleMap.addMarker(tx3);
			// googleMap.addMarker(me1);

			googleMap.setMyLocationEnabled(true);

			googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(Marker marker) {
					// TODO Auto-generated method stub
					List<Driver> drivers = AppController.getListDrivers();
					for (int i = 0; i < drivers.size(); i++) {
						LatLng latLng = new LatLng(
								drivers.get(i).getLatitude(), drivers.get(i)
										.getLongitude());
						if (latLng.equals(marker.getPosition())) {
							driver = drivers.get(i);
						}
					}
					
					// Getting URL to the Google Directions API
					String url = getDirectionsUrl(rider_current_position,
							marker.getPosition());

					DownloadTask downloadTask = new DownloadTask();

					// Start downloading json data from Google Directions API
					downloadTask.execute(url);
					Toast.makeText(getApplicationContext(), ""+AppController.getDistance(), 3)
					.show();
					
					rider_send_request_information.setVisibility(View.VISIBLE);
					rider_send_request_first_step.setVisibility(View.VISIBLE);
					pick_point.setVisibility(View.VISIBLE);

					pick_start_point_value = (Button) findViewById(R.id.btn_pick_start_point_value);
					pick_end_point_value = (Button) findViewById(R.id.btn_pick_end_point_value);

					// Pich Start Point
					pick_start_point.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							pick_start_point_value.setVisibility(View.VISIBLE);
							// googleMap.clear();
							PointLongitude = null;
							PointLatitude = null;
							googleMap
									.setOnMapClickListener(new OnMapClickListener() {

										@Override
										public void onMapClick(LatLng arg0) {
											Location targetLocation = new Location(
													"");
											targetLocation
													.setLatitude(arg0.latitude);
											targetLocation
													.setLongitude(arg0.longitude);

											PointLongitude = arg0.longitude;
											PointLatitude = arg0.latitude;

											if (lastMarker2 != null) {
												lastMarker2.remove();
											}
											MarkerOptions startPoint = new MarkerOptions();
											startPoint.position(arg0);
											startPoint
													.icon(BitmapDescriptorFactory
															.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
											startPoint.title("Start Point");
											Marker marker = googleMap
													.addMarker(startPoint);
											lastMarker2 = marker;

										}
									});
							pick_start_point_value
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											Toast.makeText(
													getApplicationContext(),
													"" + PointLatitude,
													Toast.LENGTH_SHORT).show();
											if (PointLatitude != null
													& PointLongitude != null) {
												start_lat = PointLatitude;
												start_lng = PointLongitude;
											} else
												Toast.makeText(
														getApplicationContext(),
														"Please pick Start Point",
														Toast.LENGTH_LONG)
														.show();
											pick_start_point_value
													.setVisibility(View.GONE);
											pick_start_point.setText("Repick");

										}
									});

						}
					});

					// Pick End Point

					pick_end_point.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							pick_end_point_value.setVisibility(View.VISIBLE);
							// googleMap.clear();
							PointLongitude = null;
							PointLatitude = null;
							googleMap
									.setOnMapClickListener(new OnMapClickListener() {

										@Override
										public void onMapClick(LatLng arg0) {

											PointLongitude = arg0.longitude;
											PointLatitude = arg0.latitude;

											if (lastMarker3 != null) {
												lastMarker3.remove();
											}
											MarkerOptions endPoint = new MarkerOptions();
											endPoint.position(arg0);
											endPoint.icon(BitmapDescriptorFactory
													.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
											endPoint.title("End Point");
											Marker marker = googleMap
													.addMarker(endPoint);
											lastMarker3 = marker;

										}
									});
							pick_end_point_value
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											if (PointLatitude != null
													& PointLongitude != null) {
												end_lat = PointLatitude;
												end_lng = PointLongitude;
											} else
												Toast.makeText(
														getApplicationContext(),
														"Please pick End Point",
														Toast.LENGTH_LONG)
														.show();
											pick_end_point_value
													.setVisibility(View.GONE);
											pick_end_point.setText("Repick");

										}
									});

						}
					});

					send_request = (Button) findViewById(R.id.btn_rider_send_request);
					send_request.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (Utils
									.isConnectingToInternet(getApplicationContext())) {
								DatabaseHandler handler = new DatabaseHandler(
										getApplicationContext());
								Rider rider = handler.findRider();
								driverid = driver.getId();
								riderid = rider.getId();
								tripBO.CreateTrip(MapActivity.this, riderid,
										driverid, "" + start_lat, ""
												+ start_lng, "" + end_lat, ""
												+ end_lng);
								rider_send_request_first_step
										.setVisibility(View.GONE);
								removeLayout();
								rider_send_request_waiting_step
										.setVisibility(View.VISIBLE);
								handler.createTrip(AppController.getTripID(),
										TripStatus.NEW_TRIP);
							} else {
								// show error message
								alert.showAlertDialog(
										MapActivity.this,
										getResources()
												.getString(
														R.string.alert_internet_error_title),
										getResources()
												.getString(
														R.string.alert_internet_error_message),
										false);
							}

						}
					});

					return false;
				}
			});

			// Cancel Waiting Request

			cancelWaitingRequest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					DatabaseHandler handler = new DatabaseHandler(
							getApplicationContext());
					Rider rider = handler.findRider();
					riderid = rider.getId();
					tripBO.UpdateTrip(MapActivity.this,
							AppController.getTripID(), riderid,
							TripStatus.CANCELLED);
					rider_send_request_waiting_step.setVisibility(View.GONE);

					// rider_send_request_driver_accept.setVisibility(View.VISIBLE);

				}
			});

			// Cancel Accepted Request

			cancelAcceptedRequest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DatabaseHandler handler = new DatabaseHandler(
							getApplicationContext());
					Rider rider = handler.findRider();
					riderid = rider.getId();
					tripBO.UpdateTrip(MapActivity.this,
							AppController.getTripID(), riderid,
							TripStatus.CANCELLED);
					rider_send_request_driver_accept.setVisibility(View.GONE);

				}
			});

			googleMap.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng arg0) {
					// TODO Auto-generated method stub

					// Hide infor box
					rider_send_request_first_step.setVisibility(View.GONE);
					rider_send_request_information.setVisibility(View.GONE);

					// Location targetLocation = new Location("");
					// targetLocation.setLatitude(arg0.latitude);
					// targetLocation.setLongitude(arg0.longitude);
					//
					// float distanceInMeters = targetLocation
					// .distanceTo(lastLocation);
					// if (distanceInMeters > 1000) {
					// Toast.makeText(getApplicationContext(), "Out of range",
					// Toast.LENGTH_SHORT).show();
					// }
					// Toast.makeText(getApplicationContext(),
					// " " + arg0.latitude + "  " + arg0.longitude,
					// Toast.LENGTH_SHORT).show();
					// if (lastMarker != null) {
					// lastMarker.remove();
					// }
					// MarkerOptions hereIStand = new MarkerOptions();
					// hereIStand.position(arg0);
					// Marker marker = googleMap.addMarker(hereIStand);
					// lastMarker = marker;

				}
			});

			LatLng latLng = new LatLng(lastLocation.getLatitude(),
					lastLocation.getLongitude());
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,
					13));

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(latLng) // Sets the center of the map to location
									// user
					.zoom(15) // Sets the zoom
					.build(); // Creates a CameraPosition from the builder
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

		}
	}

	private void removeLayout() {
		rider_send_request_information.setVisibility(View.GONE);
		rider_send_request_first_step.setVisibility(View.GONE);
		no_driver_nearby.setVisibility(View.GONE);
		rider_send_request_waiting_step.setVisibility(View.GONE);
		rider_send_request_driver_accept.setVisibility(View.GONE);
		pick_point.setVisibility(View.GONE);
	}

	// Direction
	private String getDirectionsUrl(LatLng origin, LatLng dest) {

		// Origin of route
		String str_origin = "origin=" + origin.latitude + ","
				+ origin.longitude;

		// Destination of route
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		return url;
	}

	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	/** A class to download data from Google Directions URL */
	private class DownloadTask extends AsyncTask<String, Void, String> {

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
		}
	}

	/** A class to parse the Google Directions in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;

			// Traversing through all the routes
			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(2);
				lineOptions.color(Color.RED);
			}

			// Drawing polyline in the Google Map for the i-th route
			if (line == null) {
				line = googleMap.addPolyline(lineOptions);
			} else {
				line.remove();
				line = googleMap.addPolyline(lineOptions);
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Check device for Play Services APK.
		googlePlayService.checkPlayServices(MapActivity.this);

	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.accept:
			Toast.makeText(this, "alo", Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.taxi).setVisible(!drawerOpen);
		mDrawerList.setSelection(0);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {

		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);

		Intent it;
		switch (position) {
		case 0:
			it = new Intent(this, ProfileActivity.class);
			startActivity(it);
			break;
		case 2:
			it = new Intent(this, JourneyFragment.class);
			startActivity(it);
			break;
		case 3:
			it = new Intent(this, JourneyFragment.class);
			startActivity(it);
			break;
		case 5:
			it = new Intent(this, TaxiCompanyFragment.class);
			startActivity(it);
			break;
		case 6:
			it = new Intent(this, FavoriteDriverFragment.class);
			startActivity(it);
			break;
		case 8:
			it = new Intent(this, HistoryCallFragment.class);
			startActivity(it);
			break;
		case 9:
			it = new Intent(this, SettingFragment.class);
			startActivity(it);
			break;
		case 10:
			it = new Intent(this, SettingFragment.class);
			startActivity(it);
			break;
		default:
			break;
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void createSlideMenu(Bundle savedInstanceState) {

		mTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		setNavDrawerItemForCustomer();

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	public void setNavDrawerItemForCustomer() {
		// adding nav drawer items to array

		// DatabaseHandler handler = new DatabaseHandler(this);
		// Driver driver = handler.findDriver();
		// String fullName = driver.getFirstName() + " " + driver.getLastName();

		navDrawerItems.add(new NavDrawerItem("aa", 2));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons
				.getResourceId(0, -1)));
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

}
