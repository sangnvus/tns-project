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
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * @author Hieu-Gie
 * 
 * @createDate 20/1/2014
 */

public class ProfileBO {

	private Activity activity;
	private DatabaseHandler handler;
	private ProgressDialog pd;
	private Driver driver;

	/**
	 * @author Hieu-Gie
	 * 
	 *         kiểm tra dữ liệu ,gửi dữ liệu lên server và lưu dữ liệu offline
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param password
	 */

	public String checkProfile(Activity activity, String firstName,
			String lastName, String email, String phone, String password) {
		this.activity = activity;
		handler = new DatabaseHandler(activity);
		driver = handler.findDriver();

		// check null
		if (firstName.equalsIgnoreCase("")) {
			return Constants.FIRST_NAME_ERROR;
		}
		if (lastName.equalsIgnoreCase("")) {
			return Constants.LAST_NAME_ERROR;
		}
		if (email.equalsIgnoreCase("")) {
			return Constants.EMAIL_ERROR;
		}
		if (phone.equalsIgnoreCase("")) {
			return Constants.PHONE_NUMBER_ERROR;
		}
		if (password.equalsIgnoreCase("")) {
			return Constants.PASSWORD_ERROR;
		}

		// check length
		if (password.length() < 6) {
			Toast.makeText(activity, Constants.PASSWORD_ERROR,
					Toast.LENGTH_LONG).show();
			return Constants.PASSWORD_ERROR;
		}

		// check email
		if (!Utils.validateEmail(email)) {
			Toast.makeText(activity, Constants.EMAIL_ERROR, Toast.LENGTH_LONG)
					.show();
			return Constants.EMAIL_ERROR;
		}
		// check phone
		if (!Utils.validatePhoneNumber(phone)) {
			return Constants.EMAIL_ERROR;
		}

		// check if no info change
		if (!driver.getEmail().equalsIgnoreCase(email)
				|| !driver.getPassword().equalsIgnoreCase(password)
				|| !driver.getFirstName().equalsIgnoreCase(firstName)
				|| !driver.getLastName().equalsIgnoreCase(lastName)
				|| !driver.getPhoneNumber().equalsIgnoreCase(phone)) {
			String param[] = { firstName, lastName, email, password, phone };
			driver.setFirstName(firstName);
			driver.setLastName(lastName);
			driver.setEmail(email);
			driver.setPassword(password);
			driver.setPhoneNumber(phone);
			new UpdateDriverAsyncTask().execute(param);
		}
		return Constants.SUCCESS;
	}

	public String parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			// get message from json
			System.out.println(response);
			String message = jsonObject.getString("message");
			// success
			if (message != null && message.equalsIgnoreCase(Constants.SUCCESS)) {
				Toast.makeText(activity, activity.getString(R.string.success),
						Toast.LENGTH_LONG).show();
				// save to database offline
				DatabaseHandler handler = new DatabaseHandler(activity);
				handler.updateDriver(driver);

			} else {
				Toast.makeText(activity, "Driver not found", Toast.LENGTH_LONG)
						.show();
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

	public class UpdateDriverAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(activity);
			pd.setTitle("Login");
			pd.setMessage("Please wait until we check your infomation");
			pd.setCancelable(false);
			pd.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData(params);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				parseJson(result);
			}
		}
	}

	public String postData(String[] params) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.UPDATE_DRIVER);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("id", AppController
					.getDriverId()));
			nameValuePairs.add(new BasicNameValuePair("firstname", params[0]));
			nameValuePairs.add(new BasicNameValuePair("lastname", params[1]));
			nameValuePairs.add(new BasicNameValuePair("email", params[2]));
			nameValuePairs.add(new BasicNameValuePair("password", params[3]));
			nameValuePairs
					.add(new BasicNameValuePair("phoneNumber", params[4]));
			// httppost.setHeader("Content-Type","application/json;charset=UTF-8");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

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
}
