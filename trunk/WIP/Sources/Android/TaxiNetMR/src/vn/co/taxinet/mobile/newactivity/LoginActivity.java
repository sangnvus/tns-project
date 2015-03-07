package vn.co.taxinet.mobile.newactivity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.bo.LoginBO;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText mEmail, mPassword;
	private LoginBO loginBO;
	private SharedPreferences prefs = null;
	private DatabaseHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mEmail = (EditText) findViewById(R.id.et_email);
		mPassword = (EditText) findViewById(R.id.et_password);
		loginBO = new LoginBO();
		prefs = getSharedPreferences("vn.co.taxinet.mobile", MODE_PRIVATE);
		handler = new DatabaseHandler(this);

		// check database

		Rider rider = handler.findRider();
		if (rider != null) {

			AppController.setRiderId(rider.getId());
			
			Intent it = new Intent(LoginActivity.this, MapActivity.class);
			startActivity(it);
		}

	}

	public void login(View v) {

		// check internet
		if (Utils.isConnectingToInternet(this)) {
			loginBO.checkLoginInfo(LoginActivity.this, mEmail.getText()
					.toString(), mPassword.getText().toString());
		} else {
			// alert internet connection error
			Toast.makeText(this, getString(R.string.no_internet_connection),
					Toast.LENGTH_LONG).show();
		}
		// Intent it = new Intent(LoginActivity.this, MapActivity.class);
		// startActivity(it);

	}

	public void getPassword(View v) {
		Intent it = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
		startActivityForResult(it, 2);
	}

	public void register(View v) {
		Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(it);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (prefs.getBoolean("firstrun", true)) {
			Intent it = new Intent(LoginActivity.this, StartActivity.class);
			startActivity(it);
			finish();
			prefs.edit().putBoolean("firstrun", false).commit();
		}
	}

}
