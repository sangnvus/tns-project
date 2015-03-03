package vn.co.taxinet.mobile.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.NavDrawerListAdapter;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.bo.GetDriverBO;
import vn.co.taxinet.mobile.googleapi.DirectionsJSONParser;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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

public class HomeActivity extends Activity {

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
	private GoogleMap googleMap;
	private Marker lastmarker;
	private GetDriverBO getDriverBO;

	private String TAG = HomeActivity.class.getSimpleName();
	private ProgressDialog pDialog;
	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
	String cbb = null;
	private int checkPolyLine = 0;
	private Polyline line = null;
	RelativeLayout tyty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// create slide menu
		createSlideMenu(savedInstanceState);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);
		try {
			// Loading map
			tyty = (RelativeLayout) findViewById(R.id.tyty);
			tyty.setVisibility(View.GONE);
			initilizeMap();
			displayLocation();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// private void getDrivers(final LatLng position){
	// JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
	// Const.URL_LOGIN_AUTHEN, null,
	// new Response.Listener<JSONObject>() {
	//
	// @Override
	// public void onResponse(JSONObject response) {
	// Log.d(TAG, response.toString());
	// int responseCode = getDriverBO.parseJson(response);
	// if (responseCode == 200) {
	// Intent it = new Intent(LoginActivity.this,
	// MainActivity.class);
	// startActivity(it);
	// } else {
	// Toast.makeText(
	// LoginActivity.this,
	// getResources().getString(
	// R.string.login_error),
	// Toast.LENGTH_LONG).show();
	// }
	//
	// }
	// }, new Response.ErrorListener() {
	//
	// @Override
	// public void onErrorResponse(VolleyError error) {
	// VolleyLog.d(TAG, "Error: " + error.getMessage());
	// }
	// }) {
	// @Override
	// protected Map<String, String> getParams() {
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("lat", ""+position.latitude);
	// params.put("lng", ""+position.longitude);
	//
	// return params;
	// }
	//
	// };
	//
	// // Adding request to request queue
	// AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	//
	// }

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
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

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
					tyty.setVisibility(View.VISIBLE);

					return false;
				}
			});

			googleMap.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng arg0) {
					// TODO Auto-generated method stub
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

	public void callTaxiFirms(View v) {
		Intent it = new Intent(HomeActivity.this, TaxiCompanyActivity.class);
		startActivity(it);
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


		navDrawerItems.add(new NavDrawerItem("dau ma", 2));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
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
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 1:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 2:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 3:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 5:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 6:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 8:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 9:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		case 10:
			it = new Intent(this, ResetPasswordActivity.class);
			startActivity(it);
			break;
		default:
			break;
		}
	}
	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
//		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		menu.findItem(R.id.taxi).setVisible(!drawerOpen);
		mDrawerList.setSelection(0);
		return super.onPrepareOptionsMenu(menu);
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
