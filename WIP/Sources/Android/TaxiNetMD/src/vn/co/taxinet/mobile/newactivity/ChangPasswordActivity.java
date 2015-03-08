package vn.co.taxinet.mobile.newactivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.drive.Drive;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.alert.AlertDialogManager;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangPasswordActivity extends Activity {

	private EditText mOldPassword, mNewPassword, mConfirmPassword;
	private DatabaseHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		inialize();
	}

	public void inialize() {
		mOldPassword = (EditText) findViewById(R.id.et_old_password);
		mNewPassword = (EditText) findViewById(R.id.et_new_password);
		mConfirmPassword = (EditText) findViewById(R.id.et_confirm_password);
		handler = new DatabaseHandler(this);
	}

	public void changePassword(View v) {
		if (Utils.isConnectingToInternet(this)) {
			Driver driver = handler.findDriver();
			AlertDialogManager alert = new AlertDialogManager();
			String oldPassword = mOldPassword.getText().toString();
			String newPassword = mNewPassword.getText().toString();
			String confirmPassword = mConfirmPassword.getText().toString();
			// if (!driver.getPassword().equals(oldPassword)) {
			// alert.showAlertDialog(this, getString(R.string.error),
			// getString(R.string.error), true);
			// }

			// check 2 password
			if (!newPassword.equals(confirmPassword)) {
				alert.showAlertDialog(this, getString(R.string.error),
						getString(R.string.two_password_error), true);
				return;
			}

			// check length
			if (oldPassword.equalsIgnoreCase("")) {
				alert.showAlertDialog(this, getString(R.string.error),
						getString(R.string.error), true);
				return;
			}

			if (oldPassword.length() < 6) {
				alert.showAlertDialog(this, getString(R.string.error),
						getString(R.string.error), true);
				return;
			}

			// send new password to server
			String params[] = { driver.getId(), oldPassword, newPassword };
			new ChangePassword().execute(params);

		}
	}

	public class ChangePassword extends AsyncTask<String, Void, String> {

		private ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(getApplicationContext());
			pd.setTitle(getString(R.string.change_password));
			pd.setMessage(getString(R.string.change_password));
			pd.setCancelable(false);
			pd.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData(params);
		}

		public String postData(String[] params) {
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Constants.URL.UPDATE_DRIVER);
			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("id", params[0]));
				nameValuePairs.add(new BasicNameValuePair("oldpassword",
						params[1]));
				nameValuePairs.add(new BasicNameValuePair("newpassword",
						params[2]));
				// httppost.setHeader("Content-Type","application/json;charset=UTF-8");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
						"UTF-8"));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				int respnseCode = response.getStatusLine().getStatusCode();
				if (respnseCode == 200) {
					HttpEntity entity = response.getEntity();
					return EntityUtils.toString(entity);
				}
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
			}
			try {
				JSONObject object = new JSONObject(result);
				if (object.getString("message").equalsIgnoreCase(
						Constants.SUCCESS)) {
					Intent returnIntent = new Intent();
					setResult(RESULT_OK, returnIntent);
					finish();
				}
				if (object.getString("message").equalsIgnoreCase(
						Constants.PASSWORD_ERROR)) {
					Intent returnIntent = new Intent();
					setResult(RESULT_CANCELED, returnIntent);
					finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
