package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.bo.DriderProfileBO;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class DriverProfileActivity extends Activity {

	private EditText mEmail, mPhone, mPassword, mFirstName, mLastName;
	private Menu menu;
	private MenuItem mSaveMenu, mEditMenu, mCancelMenu;
	private String email, phone, password, firstName, lastName;
	private DriderProfileBO bo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_profile);
		initialize();
	}

	public void initialize() {
		mEmail = (EditText) findViewById(R.id.et_email);
		mPhone = (EditText) findViewById(R.id.et_phone);
		mPassword = (EditText) findViewById(R.id.et_password);
		mFirstName = (EditText) findViewById(R.id.et_first_name);
		mLastName = (EditText) findViewById(R.id.et_last_name);
		mSaveMenu = menu.findItem(R.id.save_menu);
		mEditMenu = menu.findItem(R.id.edit_menu);
		mCancelMenu = menu.findItem(R.id.cancel_menu);
		mSaveMenu.setVisible(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.edit_menu) {
			setEditEnable();
			return true;
		}
		if (id == R.id.save_menu) {
			setEditDisable();
			getParmas();
			bo.editProfile(firstName, lastName, email, phone, password);
			return true;
		}

		if (id == R.id.cancel_menu) {
			setEditDisable();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setEditDisable() {
		mEmail.setEnabled(false);
		mPhone.setEnabled(false);
		mPassword.setEnabled(false);
		mFirstName.setEnabled(false);
		mLastName.setEnabled(false);
		mEditMenu.setVisible(true);
		mSaveMenu.setVisible(false);
		mCancelMenu.setVisible(false);
	}

	public void setEditEnable() {
		mEmail.setEnabled(true);
		mPhone.setEnabled(true);
		mPassword.setEnabled(true);
		mFirstName.setEnabled(true);
		mLastName.setEnabled(true);
		mEditMenu.setVisible(false);
		mSaveMenu.setVisible(true);
		mCancelMenu.setVisible(true);
	}

	public void getParmas() {
		email = mEmail.getText().toString();
		phone = mPhone.getText().toString();
		password = mPassword.getText().toString();
		firstName = mFirstName.getText().toString();
		lastName = mLastName.getText().toString();
	}
}
