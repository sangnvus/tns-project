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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.NavDrawerListAdapter;
import vn.co.taxinet.mobile.alert.AlertDialogManager;
import vn.co.taxinet.mobile.bo.MapBO;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.gps.GooglePlayService;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Constants.DriverStatus;
import vn.co.taxinet.mobile.utils.Constants.TripStatus;
import vn.co.taxinet.mobile.utils.LruBitmapCache;
import vn.co.taxinet.mobile.utils.WakeLocker;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
	private GoogleMap googleMap;

	// These tags will be used to cancel the requests
	private TextView mRiderName, mRiderPhoneNumber;
	private LinearLayout mReqestLayout;

	private BroadcastReceiver receiver;

	private Rider rider = null;
	private GooglePlayService googlePlayService;
	private MapBO mapBO;

	// boolean flag to toggle periodic location updates
	private String requestId;
	private String tripStatus, driverStatus;
	private DatabaseHandler handler;

	private Location mLastLocation;

	// Google client to interact with Google API
	private GoogleApiClient mGoogleApiClient;

	// boolean flag to toggle periodic location updates
	private boolean mRequestingLocationUpdates = false;

	private LocationRequest mLocationRequest;

	// Location updates intervals in sec
	private static int UPDATE_INTERVAL = 10000; // 10 sec
	private static int FATEST_INTERVAL = 5000; // 5 sec
	private static int DISPLACEMENT = 10; // 10 meters
	private Context mContext = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_blank);

		// register gcm id
		googlePlayService = new GooglePlayService(MapActivity.this);
		if (googlePlayService.checkPlayServices(this)) {

			// Building the GoogleApi client
			buildGoogleApiClient();

			createLocationRequest();
		}

		// create slide menu
		createSlideMenu(savedInstanceState);

		initialize();
		// Loading map
		initilizeMap();
	}

	private void initialize() {
		// set up broadcast receiver
		IntentFilter filter = new IntentFilter(
				Constants.BroadcastAction.DISPLAY_REQUEST);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		receiver = new mHandleMessageReceiver();
		registerReceiver(receiver, filter);

		mRiderName = (TextView) findViewById(R.id.tv_rider_name);
		mRiderPhoneNumber = (TextView) findViewById(R.id.tv_rider_phone_number);
		mReqestLayout = (LinearLayout) findViewById(R.id.request_layout);
		mReqestLayout.setVisibility(View.GONE);

		handler = new DatabaseHandler(this);
		driverStatus = DriverStatus.AVAIABLE;
	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.setMyLocationEnabled(true);
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			Criteria criteria = new Criteria();
			String bestProvider = locationManager.getBestProvider(criteria,
					true);
			Location location = locationManager
					.getLastKnownLocation(bestProvider);
			if (location != null) {
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				LatLng latLng = new LatLng(latitude, longitude);
				googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
				googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
				onLocationChanged(location);
			}

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(this, "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
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
		stopLocationUpdates();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Check device for Play Services APK.
		googlePlayService.checkPlayServices(this);

		// Resuming the periodic location updates
		if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
			startLocationUpdates();
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	public void accept(View v) {
		driverStatus = DriverStatus.BUSY;
		Driver driver = handler.findDriver();
		Button mButton = (Button) findViewById(R.id.bt_accept);
		if (tripStatus.equalsIgnoreCase(Constants.TripStatus.NEW_TRIP)) {
			tripStatus = Constants.TripStatus.PICKING;
			String params[] = { Constants.RESPONSE_REQUEST, requestId,
					Constants.TripStatus.PICKING, driver.getId() };
			mapBO = new MapBO(MapActivity.this, true);
			mapBO.execute(params);
			mButton.setText(getString(R.string.picked));
			return;

		}
		if (tripStatus.equalsIgnoreCase(Constants.TripStatus.PICKING)) {
			tripStatus = Constants.TripStatus.PICKED;
			String params[] = { Constants.RESPONSE_REQUEST, requestId,
					Constants.TripStatus.PICKED, driver.getId() };
			mapBO = new MapBO(MapActivity.this, true);
			mapBO.execute(params);
			mButton.setText(getString(R.string.return_customer));
			LinearLayout mReqestLayout = (LinearLayout) findViewById(R.id.request_layout);
			mReqestLayout.setVisibility(View.GONE);
			Button deni = (Button) findViewById(R.id.bt_deni);
			deni.setVisibility(View.GONE);
			return;

		}
		if (tripStatus.equalsIgnoreCase(Constants.TripStatus.PICKED)) {
			tripStatus = Constants.TripStatus.PICKED;
			String params[] = { Constants.RESPONSE_REQUEST, requestId,
					Constants.TripStatus.PICKED, driver.getId() };
			mapBO = new MapBO(MapActivity.this, true);
			mapBO.execute(params);
			mButton.setText(getString(R.string.return_customer));
			LinearLayout mReqestLayout = (LinearLayout) findViewById(R.id.request_layout);
			mReqestLayout.setVisibility(View.GONE);
			return;

		}
	}

	public void deni(View v) {
		Driver driver = handler.findDriver();
		String params[] = { Constants.RESPONSE_REQUEST, requestId,
				Constants.TripStatus.CANCELLED, driver.getId() };
		LinearLayout mReqestLayout = (LinearLayout) findViewById(R.id.request_layout);
		mReqestLayout.setVisibility(View.GONE);
		mapBO = new MapBO(MapActivity.this, true);
		mapBO.execute(params);
	}

	public class mHandleMessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			// lấy trạng thái của trip
			String status = intent.getStringExtra(Constants.STATUS);
			// nếu trạng thái là new trip
			if (status.equalsIgnoreCase(TripStatus.NEW_TRIP)) {

				rider = new Rider();
				rider.setId(Integer.parseInt(intent
						.getStringExtra(Constants.ID)));
				rider.setName(intent.getStringExtra(Constants.NAME));
				rider.setImage(intent.getStringExtra(Constants.IMAGE));
				rider.setLongitude(Double.parseDouble(intent
						.getStringExtra(Constants.LONGITUDE)));
				rider.setLatitude(Double.parseDouble(intent
						.getStringExtra(Constants.LATITUDE)));
				rider.setPhone(intent.getStringExtra(Constants.PHONE));
				// display request

				mReqestLayout.setVisibility(View.VISIBLE);
				mRiderName.setText(rider.getName());
				mRiderPhoneNumber.setText(rider.getPhone());
				requestId = intent.getStringExtra("id");

				ImageLoader.ImageCache imageCache = new LruBitmapCache();
				ImageLoader imageLoader = new ImageLoader(
						Volley.newRequestQueue(getApplicationContext()),
						imageCache);
				NetworkImageView imgAvatar = (NetworkImageView) findViewById(R.id.iv_driver_image);
				imgAvatar.setImageUrl(rider.getImage(), imageLoader);

				// wake mobile up
				WakeLocker.acquire(context);
				WakeLocker.release();

				// Moving Camera to a Location with animation
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(new LatLng(rider.getLatitude(), rider
								.getLongitude())).zoom(14).build();

				// add maker
				MarkerOptions marker = new MarkerOptions().position(
						new LatLng(rider.getLatitude(), rider.getLongitude()))
						.title(rider.getName());

				googleMap.addMarker(marker);

				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));

				tripStatus = Constants.TripStatus.NEW_TRIP;
			}
			// nếu trạng thái là cancel, thông báo ra màn hình và tắt thông tin
			// chuyến đi
			if (status.equalsIgnoreCase(TripStatus.CANCELLED)) {
				if (mReqestLayout.isShown()) {
					mReqestLayout.setVisibility(View.GONE);
				}
				AlertDialogManager manager = new AlertDialogManager();
				manager.showCancelRequestAlert(mContext,
						getString(R.string.cancel_request_title),
						getString(R.string.cancel_request_message));
			}
		}
	};

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

		DatabaseHandler handler = new DatabaseHandler(this);
		Driver driver = handler.findDriver();
		String fullName = driver.getFirstName() + " " + driver.getLastName();

		navDrawerItems.add(new NavDrawerItem(fullName, 2));
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
	 * Starting the location updates
	 * */
	protected void startLocationUpdates() {

		LocationServices.FusedLocationApi.requestLocationUpdates(
				mGoogleApiClient, mLocationRequest, this);

	}

	/**
	 * Stopping location updates
	 */
	protected void stopLocationUpdates() {
		LocationServices.FusedLocationApi.removeLocationUpdates(
				mGoogleApiClient, this);
	}

	/**
	 * Google api callback methods
	 */
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		Log.i("", "Connection failed: ConnectionResult.getErrorCode() = "
				+ result.getErrorCode());
	}

	@Override
	public void onConnected(Bundle arg0) {

		// Once connected with google api, get the location

		if (mRequestingLocationUpdates) {
			startLocationUpdates();
		}
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();
	}

	@Override
	public void onLocationChanged(Location location) {
		// Assign the new location
		mLastLocation = location;

		Driver driver = handler.findDriver();
		mapBO = new MapBO(this, true);
		String address = GetAddress(mLastLocation.getLatitude(),
				mLastLocation.getLongitude());
		String params[] = { Constants.UPDATE_CURRENT_STATUS,
				String.valueOf(mLastLocation.getLatitude()),
				String.valueOf(mLastLocation.getLongitude()), address,
				driverStatus, driver.getId() };
		mapBO.execute(params);
		// Displaying the new location on UI
		Toast.makeText(
				this,
				"Latitude : " + mLastLocation.getLatitude() + "\nLongitude : "
						+ mLastLocation.getLongitude(), Toast.LENGTH_LONG)
				.show();
	}

	public String GetAddress(double lat, double lon) {
		Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
		String ret = "";
		try {
			List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
			if (addresses != null) {
				Address returnedAddress = addresses.get(0);
				StringBuilder strReturnedAddress = new StringBuilder();
				for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
					if (!returnedAddress.getAddressLine(i).equalsIgnoreCase(
							"Unnamed Rd")) {
						strReturnedAddress.append(
								returnedAddress.getAddressLine(i)).append(" ");
					}

				}
				ret = strReturnedAddress.toString();
			} else {
				ret = "No Address returned!";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = "Can't get Address!";
		}
		return ret;
	}
}
