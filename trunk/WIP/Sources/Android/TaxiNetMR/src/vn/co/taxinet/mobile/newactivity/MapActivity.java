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
import vn.co.taxinet.mobile.bo.MapBO;
import vn.co.taxinet.mobile.bo.TripBO;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.gcm.GooglePlayService;
import vn.co.taxinet.mobile.googleapi.DirectionsJSONParser;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.LruBitmapCache;
import vn.co.taxinet.mobile.utils.Utils;
import vn.co.taxinet.mobile.utils.WakeLocker;
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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
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
public class MapActivity extends Activity implements ConnectionCallbacks,
		OnConnectionFailedListener, LocationListener {

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
	private Driver x;

	private Location mLastLocation;

	// Google client to interact with Google API
	private GoogleApiClient mGoogleApiClient;

	// boolean flag to toggle periodic location updates
	private boolean mRequestingLocationUpdates = true;

	private LocationRequest mLocationRequest;

	// Location updates intervals in sec
	private static int UPDATE_INTERVAL = 60000; // 60 sec
	private static int FATEST_INTERVAL = 30000; // 30 sec
	private static int DISPLACEMENT = 100; // 100 meters
	private String requestId;
	private String status;
	private DatabaseHandler handler;

	private String driverid, riderid;
	private AlertDialogManager alert;

	// Google Map
	private Marker lastmarker;
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

	private Button send_request;
	private double start_lat=1, start_lng=2, end_lat=3, end_lng=4;

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

		pick_point_new_screen = (RelativeLayout) findViewById(R.id.pick_point_new_screen);
		pick_point_new_screen.setVisibility(View.GONE);

		initialize();
	}

	private void initialize() {
		// Building the GoogleApi client
		buildGoogleApiClient();
		// startLocationUpdates();

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

	// Display Location
	private void displayLocation() {

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		final Location lastLocation = locationManager
				.getLastKnownLocation(locationManager.getBestProvider(criteria,
						true));
		Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT)
				.show();
		if (lastLocation != null) {
			LatLng Taxi1 = new LatLng(21.009809, 105.523515);
			LatLng Taxi2 = new LatLng(21.014917, 105.530317);
			LatLng Taxi3 = new LatLng(21.010250, 105.532162);
			final LatLng me = new LatLng(21.013475, 105.525425);

			MarkerOptions tx1 = new MarkerOptions();
			tx1.title("Nguyễn Văn A");
			tx1.snippet("5KM");
			tx1.position(Taxi1);
			MarkerOptions tx2 = new MarkerOptions();
			tx2.title("Nguyễn Văn B");
			tx2.snippet("1KM");
			tx2.position(Taxi2);
			MarkerOptions tx3 = new MarkerOptions();
			tx3.title("Nguyễn Văn C");
			tx3.snippet("2KM");
			tx3.position(Taxi3);
			MarkerOptions me1 = new MarkerOptions();
			me1.title("Here I stand");
			me1.position(me);

			googleMap.addMarker(tx1);
			googleMap.addMarker(tx2);
			googleMap.addMarker(tx3);
			googleMap.addMarker(me1);

			googleMap.setMyLocationEnabled(true);

			googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(Marker arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),
							arg0.getPosition().toString(), Toast.LENGTH_SHORT)
							.show();
					// Getting URL to the Google Directions API
					String url = getDirectionsUrl(me, arg0.getPosition());

					DownloadTask downloadTask = new DownloadTask();

					// Start downloading json data from Google Directions API
					downloadTask.execute(url);
					rider_send_request_information.setVisibility(View.VISIBLE);
					rider_send_request_first_step.setVisibility(View.VISIBLE);
					pick_point.setVisibility(View.VISIBLE);

					pick_point.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "AAA", 5)
									.show();
							pick_point_new_screen.setVisibility(View.VISIBLE);
							if (googleMap_pp == null) {
								// googleMap_pp = ((MapFragment)
								// getFragmentManager().findFragmentById(
								// R.id.map_pick_point)).getMap();
								if (googleMap_pp == null) {
									Toast.makeText(getApplicationContext(),
											"Sorry! unable to create maps",
											Toast.LENGTH_SHORT).show();
								}
							}

						}
					});

					x = new Driver();

					send_request = (Button) findViewById(R.id.btn_rider_send_request);
					send_request.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							tripBO = new TripBO();
							if (Utils
									.isConnectingToInternet(getApplicationContext())) {

								driverid = "1";
								riderid = "2";
								tripBO.CreateTrip(MapActivity.this, riderid,
										driverid, "" + start_lat, ""
												+ start_lng, "" + end_lat, ""
												+ end_lng);

								rider_send_request_first_step
										.setVisibility(View.GONE);
								rider_send_request_waiting_step
										.setVisibility(View.VISIBLE);
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

			googleMap.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng arg0) {
					// TODO Auto-generated method stub

					// Hide infor box
					rider_send_request_first_step.setVisibility(View.GONE);
					rider_send_request_information.setVisibility(View.GONE);

					Location targetLocation = new Location("");// provider name
																// is unecessary
					targetLocation.setLatitude(arg0.latitude);// your coords of
																// course
					targetLocation.setLongitude(arg0.longitude);

					float distanceInMeters = targetLocation
							.distanceTo(lastLocation);
					if (distanceInMeters > 1000) {
						Toast.makeText(getApplicationContext(), "Out of range",
								Toast.LENGTH_SHORT).show();
					}
					Toast.makeText(getApplicationContext(),
							" " + arg0.latitude + "  " + arg0.longitude,
							Toast.LENGTH_SHORT).show();
					if (lastmarker != null) {
						lastmarker.remove();
					}
					MarkerOptions hereIStand = new MarkerOptions();
					hereIStand.position(arg0);
					Marker marker = googleMap.addMarker(hereIStand);
					lastmarker = marker;

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
	protected void onStart() {
		super.onStart();
		if (mGoogleApiClient != null) {
			mGoogleApiClient.connect();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// stopLocationUpdates();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Check device for Play Services APK.
		googlePlayService.checkPlayServices(MapActivity.this);

		if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
			startLocationUpdates();
		}
		System.out.println("notifi");
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

	/**
	 * Creating google api client object
	 * */
	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	/**
	 * Creating location request object
	 * */
	protected void createLocationRequest() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(UPDATE_INTERVAL);
		mLocationRequest.setFastestInterval(FATEST_INTERVAL);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
	}

	/**
	 * Stopping location updates
	 */
	protected void stopLocationUpdates() {
		LocationServices.FusedLocationApi.removeLocationUpdates(
				mGoogleApiClient, this);
	}

	/**
	 * Starting the location updates
	 * */
	protected void startLocationUpdates() {

		LocationServices.FusedLocationApi.requestLocationUpdates(
				mGoogleApiClient, mLocationRequest, this);

	}

	@Override
	public void onConnected(Bundle arg0) {

		if (mRequestingLocationUpdates) {
			// startLocationUpdates();
		}

	}

	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
	}

	@Override
	public void onLocationChanged(Location location) {
		// // Assign the new location
		// mLastLocation = location;
		//
		// Toast.makeText(getApplicationContext(), "Location changed!",
		// Toast.LENGTH_SHORT).show();
		//
		// // Displaying the new location on UI
		// String[] params = { Constants.UPDATE_CURRENT_STATUS,
		// String.valueOf(mLastLocation.getLongitude()),
		// String.valueOf(mLastLocation.getLatitude()) };
		//
		// mapBO.execute(params);

		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		googleMap.addMarker(new MarkerOptions().position(latLng));
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));

	}
}