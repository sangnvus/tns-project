package vn.co.taxinet.mobile.activity;

import java.util.ArrayList;


import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.NavDrawerListAdapter;
import vn.co.taxinet.mobile.adapter.TitleNavigationAdapter;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.model.SpinnerNavItem;
import vn.co.taxinet.mobile.slidemenu.FavoriteDriverFragment;
import vn.co.taxinet.mobile.slidemenu.HistoryCallFragment;
import vn.co.taxinet.mobile.slidemenu.JourneyFragment;
import vn.co.taxinet.mobile.slidemenu.MapsFragment;
import vn.co.taxinet.mobile.slidemenu.ProfileFragment;
import vn.co.taxinet.mobile.slidemenu.SettingFragment;
import vn.co.taxinet.mobile.slidemenu.TaxiCompanyFragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		ActionBar.OnNavigationListener {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	private Fragment fragment;
	// action bar
	private ActionBar actionBar;

	// Title navigation Spinner data
	private ArrayList<SpinnerNavItem> navSpinner;

	// Navigation adapter
	private TitleNavigationAdapter adapterNav;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentManager = getFragmentManager();
		createSpinnerMenu();

		createSlideMenu(savedInstanceState);

	}

	public void createSpinnerMenu() {
		actionBar = getActionBar();
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Spinner title navigation data
		navSpinner = new ArrayList<SpinnerNavItem>();
		navSpinner.add(new SpinnerNavItem("Taxi", R.drawable.ic_launcher));
		navSpinner.add(new SpinnerNavItem("Truck", R.drawable.ic_launcher));
		navSpinner.add(new SpinnerNavItem("Place", R.drawable.ic_launcher));
		navSpinner.add(new SpinnerNavItem("Packing Place",
				R.drawable.ic_launcher));

		// title drop down adapter
		adapterNav = new TitleNavigationAdapter(getApplicationContext(),
				navSpinner);
	}

	public void createSlideMenu(Bundle savedInstanceState) {
		// assigning the spinner navigation
		actionBar.setListNavigationCallbacks(adapterNav, this);

		mTitle = mDrawerTitle = getTitle();

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
//				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
//				getActionBar().setTitle(mDrawerTitle);
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
//		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		menu.findItem(R.id.taxi).setVisible(!drawerOpen);
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
			break;
		case 1:
			fragment = new MapsFragment();
			break;
		case 2:
			fragment = new JourneyFragment();
			break;
		case 3:
			fragment = new JourneyFragment();
			break;
		case 5:
			fragment = new TaxiCompanyFragment();
			break;
		case 6:
			fragment = new FavoriteDriverFragment();
			break;
		case 8:
			fragment = new HistoryCallFragment();
			break;
		case 9:
			fragment = new SettingFragment();
			break;
		case 10:
			fragment = new SettingFragment();
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
			} else {

			} // update selected item and title, then close the drawer
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

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Toast.makeText(this, "Postion " + itemPosition, Toast.LENGTH_LONG)
				.show();
		return false;
	}

	@Override
	public void onBackPressed() {

	}
}