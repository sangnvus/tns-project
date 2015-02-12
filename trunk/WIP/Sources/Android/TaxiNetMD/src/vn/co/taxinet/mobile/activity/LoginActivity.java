package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.bo.LoginBO;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText mEmail, mPassword;
	private static final String TAG = LoginActivity.class.getSimpleName();
	private static final String tag_json_obj = "Login authen";
	private LoginBO loginBO;
	private SharedPreferences prefs = null;
	AsyncTask<Void, Void, Void> mRegisterTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mEmail = (EditText) findViewById(R.id.et_email);
		mPassword = (EditText) findViewById(R.id.et_password);
		loginBO = new LoginBO();
		prefs = getSharedPreferences("vn.co.taxinet.mobile", MODE_PRIVATE);

	}

	public void login(View v) {

//		Intent it = new Intent(LoginActivity.this, vn.co.taxinet.mobile.newactivity.DemoActivity.class);
//		startActivity(it);

		loginBO.checkLoginInfo(LoginActivity.this,mEmail.getText().toString(),
				mPassword.getText().toString());
//		if (check.equalsIgnoreCase(Const.SUCCESS)) {
//			loginAuthen();
//		}
//		if (check.equalsIgnoreCase(Const.EMPTY_ERROR)) {
//			Toast.makeText(this,
//					getResources().getString(R.string.empty_error),
//					Toast.LENGTH_LONG).show();
//		}
//		if (check.equalsIgnoreCase(Const.EMPTY_ERROR)) {
//			Toast.makeText(this,
//					getResources().getString(R.string.account_error),
//					Toast.LENGTH_LONG).show();
//		}

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
