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
package vn.co.taxinet.mobile.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.NavDrawerListAdapter;
import vn.co.taxinet.mobile.fragment.BlankFragment;
import vn.co.taxinet.mobile.fragment.FavoriteDriverFragment;
import vn.co.taxinet.mobile.fragment.HistoryCallFragment;
import vn.co.taxinet.mobile.fragment.JourneyFragment;
import vn.co.taxinet.mobile.fragment.MapsFragment;
import vn.co.taxinet.mobile.fragment.ProfileFragment;
import vn.co.taxinet.mobile.fragment.SettingFragment;
import vn.co.taxinet.mobile.fragment.TaxiCompanyFragment;
import vn.co.taxinet.mobile.gcm.GcmIntentService;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.utils.Const;
import vn.co.taxinet.mobile.utils.WakeLocker;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Main UI for the demo app.
 */
@SuppressWarnings("deprecation")
public class DemoActivity extends Activity {

	private TextView mDriverName, mPrice;

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
	private Fragment fragment;

	private FragmentManager fragmentManager;
	private static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private boolean isMap = true;;

	/**
	 * Tag used on log messages.
	 */
	private static final String TAG = "GCM Demo";
	private BroadcastReceiver receiver;
	private GoogleCloudMessaging gcm;
	private AtomicInteger msgId = new AtomicInteger();
	private Context context;
	private Rider rider = null;

	String regid;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		context = getApplicationContext();

		// Check device for Play Services APK. If check succeeds, proceed with
		// GCM registration.
		if (checkPlayServices()) {

			gcm = GoogleCloudMessaging.getInstance(this);
			regid = getRegistrationId(context);

			if (regid.isEmpty()) {
				registerInBackground();
			}

		} else {
			Log.i(TAG, "No valid Google Play Services APK found.");
		}

		fragmentManager = getFragmentManager();
		createSlideMenu(savedInstanceState);

		IntentFilter filter = new IntentFilter(Const.DISPLAY_MESSAGE_ACTION);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		receiver = new mHandleMessageReceiver();
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Check device for Play Services APK.
		checkPlayServices();
	}
	
	public void accept(View v) {
		LinearLayout mReqestLayout = (LinearLayout) findViewById(R.id.request_layout);
		mReqestLayout.setVisibility(View.GONE);
	}

	public void deni(View v) {
		LinearLayout mReqestLayout = (LinearLayout) findViewById(R.id.request_layout);
		mReqestLayout.setVisibility(View.GONE);
	}

	/**
	 * Check the device to make sure it has the Google Play Services APK. If it
	 * doesn't, display a dialog that allows users to download the APK from the
	 * Google Play Store or enable it in the device's system settings.
	 */
	private boolean checkPlayServices() {

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);

		if (resultCode != ConnectionResult.SUCCESS) {

			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {

				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();

			} else {
				Log.i(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}

		return true;
	}

	/**
	 * Stores the registration ID and the app versionCode in the application's
	 * {@code SharedPreferences}.
	 * 
	 * @param context
	 *            application's context.
	 * @param regId
	 *            registration ID
	 */
	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getGcmPreferences(context);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}

	/**
	 * Gets the current registration ID for application on GCM service, if there
	 * is one.
	 * <p>
	 * If result is empty, the app needs to register.
	 * 
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	private String getRegistrationId(Context context) {

		final SharedPreferences prefs = getGcmPreferences(context);

		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
				Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	/**
	 * Registers the application with GCM servers asynchronously.
	 * <p>
	 * Stores the registration ID and the app versionCode in the application's
	 * shared preferences.
	 */
	private void registerInBackground() {

		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regid = gcm.register(Const.SENDER_ID);
					msg = "Device registered, registration ID=" + regid;
					System.out.println(msg);
					sendRegistrationIdToBackend();
					storeRegistrationId(context, regid);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
			}
		}.execute(null, null, null);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGcmPreferences(Context context) {
		// This sample app persists the registration ID in shared preferences,
		// but
		// how you store the regID in your app is up to you.
		return getSharedPreferences(DemoActivity.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	/**
	 * Sends the registration ID to your server over HTTP, so it can use
	 * GCM/HTTP or CCS to send messages to your app. Not needed for this demo
	 * since the device sends upstream messages to a server that echoes back the
	 * message using the 'from' address in the message.
	 */
	private void sendRegistrationIdToBackend() {
		// Your implementation here.
	}

	public class mHandleMessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			if (!isMap) {

				rider = new Rider();
				rider.setId(Integer.parseInt(intent
						.getStringExtra(Const.RIDER_ID)));
				rider.setName(intent.getStringExtra(Const.RIDER_NAME));
				rider.setImage(intent.getStringExtra(Const.RIDER_IMAGE));
				rider.setLongitude(Double.parseDouble(intent
						.getStringExtra(Const.LONGITUDE)));
				rider.setLatitude(Double.parseDouble(intent
						.getStringExtra(Const.LATITUDE)));
				displayView(1);
			} else {
				// send message to map
				Intent broadcastIntent = new Intent();
				broadcastIntent.setAction(Const.DISPLAY_REQUEST_ACTION);
				broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

				broadcastIntent.putExtras(intent);

				sendBroadcast(broadcastIntent);
			}
			// wake mobile up
			WakeLocker.acquire(getApplicationContext());
			WakeLocker.release();
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
		// update the main content by replacing fragments
		fragment = null;
		switch (position) {
		case 0:
			fragment = new ProfileFragment();
			isMap = false;
			break;
		case 1:
			if (rider != null) {
				fragment = new MapsFragment(rider);
			} else {
				fragment = new MapsFragment();
			}
			isMap = true;
			break;
		case 2:
			fragment = new JourneyFragment();
			isMap = false;
			break;
		case 3:
			fragment = new JourneyFragment();
			isMap = false;
			break;
		case 5:
			fragment = new TaxiCompanyFragment();
			isMap = false;
			break;
		case 6:
			fragment = new FavoriteDriverFragment();
			isMap = false;
			break;
		case 8:
			fragment = new HistoryCallFragment();
			isMap = false;
			break;
		case 9:
			fragment = new SettingFragment();
			isMap = false;
			break;
		case 10:
			fragment = new SettingFragment();
			isMap = false;
			break;
		default:
			break;
		}

		if (fragment != null) {
			Fragment f = getFragmentManager().findFragmentById(
					R.id.frame_container);
			if (!(f instanceof MapsFragment && position == 1)) {
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
			}

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			// setTitle(navMenuTitles[position]);
			setTitle("");
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
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

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(1);
		}
	}

	public void setNavDrawerItemForCustomer() {
		// adding nav drawer items to array

		navDrawerItems.add(new NavDrawerItem("Hiếu Giề", 2));
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
