package vn.co.taxinet.mobile.activity;

import java.util.ArrayList;

import vn.co.taxinet.mobile.MainActivity;
import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.TitleNavigationAdapter;
import vn.co.taxinet.mobile.model.SpinnerNavItem;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DriverLoginActivity extends Activity implements ActionBar.OnNavigationListener{

	 // action bar
    private ActionBar actionBar;
 
    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;
     
    // Navigation adapter
    private TitleNavigationAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_login);
		actionBar = getActionBar();

		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(false);

		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Spinner title navigation data
		navSpinner = new ArrayList<SpinnerNavItem>();
		navSpinner.add(new SpinnerNavItem("Local", R.drawable.ic_launcher));
		navSpinner
				.add(new SpinnerNavItem("My Places", R.drawable.ic_launcher));
		navSpinner.add(new SpinnerNavItem("Checkins", R.drawable.ic_launcher));
		navSpinner.add(new SpinnerNavItem("Latitude", R.drawable.ic_launcher));

		// title drop down adapter
		adapter = new TitleNavigationAdapter(getApplicationContext(),
				navSpinner);

		// assigning the spinner navigation
		actionBar.setListNavigationCallbacks(adapter, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.taxi:
			Toast.makeText(this, "fuck", Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void login(View v) {
		Intent it = new Intent(DriverLoginActivity.this, MainActivity.class);
		startActivity(it);
	}

	public void getPassword(View v) {
		Intent it = new Intent(DriverLoginActivity.this,
				DriverForgotPasswordActivity.class);
		startActivityForResult(it, 2);
	}

	public void register(View v) {
		Intent it = new Intent(DriverLoginActivity.this,
				DriverRegisterActivity.class);
		startActivity(it);
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}
