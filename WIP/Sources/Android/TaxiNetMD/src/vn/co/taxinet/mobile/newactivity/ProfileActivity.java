package vn.co.taxinet.mobile.newactivity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.alert.AlertDialogManager;
import vn.co.taxinet.mobile.bo.ProfileBO;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends Activity {

	private EditText mEmail, mPhone, mFirstName, mLastName;
	private MenuItem mSaveMenu, mEditMenu, mCancelMenu;
	private String email, phone, firstName, lastName;
	private ProfileBO bo;
	private ActionBar actionBar;
	private DatabaseHandler handler;
	private Button mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		initialize();
		disableEdittext();
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_profile, menu);

		mSaveMenu = menu.findItem(R.id.save);
		mEditMenu = menu.findItem(R.id.edit);
		mCancelMenu = menu.findItem(R.id.cancel);
		disableEdit();
		super.onCreateOptionsMenu(menu);
		return true;
	}

	public void initialize() {

		mEmail = (EditText) findViewById(R.id.et_email);
		mPhone = (EditText) findViewById(R.id.et_phone);
		mFirstName = (EditText) findViewById(R.id.et_first_name);
		mLastName = (EditText) findViewById(R.id.et_last_name);
		mPassword = (Button) findViewById(R.id.bt_password);
		bo = new ProfileBO();
		handler = new DatabaseHandler(this);

		Driver driver = handler.findDriver();
		mEmail.setText(driver.getEmail());
		mPhone.setText(driver.getPhoneNumber());
		mFirstName.setText(driver.getFirstName());
		mLastName.setText(driver.getLastName());

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.edit) {
			enableEdit();
			enableEdittext();
			return true;
		}
		if (id == R.id.save) {

			getInfo();
			updateProfile();
			return true;
		}

		if (id == R.id.cancel) {
			disableEdit();
			disableEdittext();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void updateProfile() {
		if (Utils.isConnectingToInternet(this)) {
			String result = bo.checkProfile(ProfileActivity.this, firstName,
					lastName, email, phone);
			if (result.equalsIgnoreCase(Constants.SUCCESS)) {
				disableEdittext();
				disableEdit();

			} else {
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			}
		} else {
			AlertDialogManager manager = new AlertDialogManager();
			manager.showAlertDialog(this, "Connection Errer",
					"Please connect to the Internet before update", false);
		}
	}

	public void disableEdittext() {
		mEmail.setFocusable(false);
		mPhone.setFocusable(false);
		mPassword.setFocusable(false);
		mFirstName.setFocusable(false);
		mLastName.setFocusable(false);
	}

	public void enableEdittext() {
		mEmail.setFocusable(true);
		mEmail.setFocusableInTouchMode(true);
		mPhone.setFocusable(true);
		mPhone.setFocusableInTouchMode(true);
		mPassword.setFocusable(true);
		mPassword.setFocusableInTouchMode(true);
		mFirstName.setFocusable(true);
		mFirstName.setFocusableInTouchMode(true);
		mLastName.setFocusable(true);
		mLastName.setFocusableInTouchMode(true);
	}

	public void disableEdit() {
		mEditMenu.setVisible(true);
		mSaveMenu.setVisible(false);
		mCancelMenu.setVisible(false);
	}

	public void enableEdit() {
		mEditMenu.setVisible(false);
		mSaveMenu.setVisible(true);
		mCancelMenu.setVisible(true);
	}

	public void getInfo() {
		email = mEmail.getText().toString();
		phone = mPhone.getText().toString();
		firstName = mFirstName.getText().toString();
		lastName = mLastName.getText().toString();
	}

	public void changePassword(View v) {
		Intent it = new Intent(ProfileActivity.this,
				ChangPasswordActivity.class);
		startActivityForResult(it, 1);
	}

	public void logout(View v) {
		deleteAndLogout();
	}

	public void deleteAndLogout() {
		// delete database
		handler.deleteDriverById();
		// clear shared preferences
		clearRegistrationId();
		// move to login screen
		Intent it = new Intent(ProfileActivity.this, LoginActivity.class);
		startActivity(it);
		finish();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(),
						getString(R.string.change_password_success_message),
						Toast.LENGTH_LONG).show();
			}
			if (resultCode == RESULT_CANCELED) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
				alertDialog.setTitle(getString(R.string.error));
				alertDialog
						.setMessage(getString(R.string.change_password_fail_message));
				alertDialog.setPositiveButton(getString(R.string.accept),
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								deleteAndLogout();
							}
						});
			}
		}
	}

	private void clearRegistrationId() {
		final SharedPreferences prefs = getSharedPreferences(
				MapActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.commit();
	}
}
