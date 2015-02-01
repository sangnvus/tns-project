package vn.co.taxinet.mobile.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import vn.co.taxinet.mobile.MainActivity;
import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.bo.LoginBO;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class LoginActivity extends Activity {

	private EditText mEmail, mPassword;
	private static final String TAG = LoginActivity.class.getSimpleName();
	private static final String tag_json_obj = "Login authen";
	private LoginBO loginBO;
	private SharedPreferences prefs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mEmail = (EditText) findViewById(R.id.et_email);
		mPassword = (EditText) findViewById(R.id.et_password);
		loginBO = new LoginBO();
		prefs = getSharedPreferences("vn.co.taxinet.mobile",
				MODE_PRIVATE);
	}

	public void login(View v) {
		
		Intent it = new Intent(LoginActivity.this,
				MainActivity.class);
		startActivity(it);

//		String check = loginBO.checkLoginInfo(mEmail.getText().toString(),
//				mPassword.getText().toString());
//		if (check.equalsIgnoreCase(Const.SUCCESS)) {
//			loginAuthen();
//		}
//		if (check.equalsIgnoreCase(Const.EMPTY_ERROR)) {
//			Toast.makeText(this,
//					getResources().getString(R.string.empty_error),
//					Toast.LENGTH_LONG).show();
//		}
//		if (check.equalsIgnoreCase(Const.ACCOUNT_ERROR)) {
//			Toast.makeText(this,
//					getResources().getString(R.string.account_error),
//					Toast.LENGTH_LONG).show();
//		}
	}

	public void getPassword(View v) {
		Intent it = new Intent(LoginActivity.this,
				ForgotPasswordActivity.class);
		startActivityForResult(it, 2);
	}

	public void register(View v) {
		Intent it = new Intent(LoginActivity.this,
				RegisterActivity.class);
		startActivity(it);
	}

	public void loginAuthen() {
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Const.URL_LOGIN_AUTHEN, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						int responseCode = loginBO.parseJson(response);
						if (responseCode == 200) {
							Intent it = new Intent(LoginActivity.this,
									MainActivity.class);
							startActivity(it);
						} else {
							Toast.makeText(
									LoginActivity.this,
									getResources().getString(
											R.string.login_error),
									Toast.LENGTH_LONG).show();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("account", mEmail.getText().toString());
				params.put("password", mPassword.getText().toString());

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (prefs.getBoolean("firstrun", true)) {
			Intent it = new Intent(LoginActivity.this,
					StartActivity.class);
			startActivity(it);
			finish();
			prefs.edit().putBoolean("firstrun", false).commit();
		}
	}

}
