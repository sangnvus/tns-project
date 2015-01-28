package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DriverLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_login);

	}

	public void login(View v) {
		Intent it = new Intent(DriverLoginActivity.this,
				DriverHomeActivity.class);
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
}
