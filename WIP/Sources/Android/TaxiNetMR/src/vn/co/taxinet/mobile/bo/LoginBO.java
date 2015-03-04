package vn.co.taxinet.mobile.bo;

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

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.newactivity.MapActivity;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

public class LoginBO {

	private Activity activity;
	private String account, password;
	private ProgressDialog pd;

	public void checkLoginInfo(Activity activity, String account,
			String password) {
		this.activity = activity;
		this.account = account;
		this.password = password;

		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
			// return Const.EMPTY_ERROR;
			Toast.makeText(activity,
					this.activity.getString(R.string.empty_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		// if (Utils.validateEmail(account)) {
		//
		// Toast.makeText(activity,
		// this.activity.getString(R.string.account_error),
		// Toast.LENGTH_LONG).show();
		// return;
		// }
		new LoginAsyncTask().execute();
		// loginAuthen(activity);
	}

	public String parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			// get message from json
			System.out.println(response);
			String id = jsonObject.getString("id");
			// success
			// move to mapactivity and save to database offline
			if (!id.equalsIgnoreCase("null")) {

				// parse json
				Driver driver = new Driver();
				driver.setId(id);
				driver.setFirstName(jsonObject.getString("firstName"));
				driver.setLastName(jsonObject.getString("lastName"));
				driver.setEmail(jsonObject.getString("email"));
				driver.setPassword(jsonObject.getString("password"));
				driver.setPhoneNumber(jsonObject.getString("phoneNumber"));
				driver.setImage(jsonObject.getString("image"));

				// save to database offline
				DatabaseHandler handler = new DatabaseHandler(activity);
				handler.createDriver(driver);
				// move to map activity
				Intent it = new Intent(activity, MapActivity.class);
				activity.startActivity(it);

				// save to global id
//				AppController.setDriverId(driver.getId());

			} else {
				Toast.makeText(activity,
						activity.getString(R.string.wrong_email_or_password),
						Toast.LENGTH_LONG).show();
			}
			if (pd.isShowing()) {
				pd.dismiss();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//
		return null;

	}

	public class LoginAsyncTask extends AsyncTask<Void, Void, String> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(activity);
			pd.setTitle("Login");
			pd.setMessage("Please wait until we check your infomation");
			pd.setCancelable(false);
			pd.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			return postData();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				parseJson(result);
			}
		}
	}

	public String postData() {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.LOGIN_AUTHEN);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("username", account));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			int respnseCode = response.getStatusLine().getStatusCode();
			if (respnseCode == 200) {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return null;
	}
}
