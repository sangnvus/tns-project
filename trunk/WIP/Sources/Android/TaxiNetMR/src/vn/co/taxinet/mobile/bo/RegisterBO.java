package vn.co.taxinet.mobile.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import vn.co.taxinet.mobile.newactivity.LoginActivity;
import vn.co.taxinet.mobile.utils.Constants;
import vn.co.taxinet.mobile.utils.Utils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

public class RegisterBO {

	private Activity activity;
	private String driver = "driver";

	public void checkRegisterInfo(Activity context, String email,
			String password, String confirmPassword, String firstName,
			String lastName, String phoneNumber) {
		this.activity = context;
		if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
				|| TextUtils.isEmpty(confirmPassword)
				|| TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)
				|| TextUtils.isEmpty(phoneNumber)) {
			Toast.makeText(context,
					context.getString(R.string.register_blank_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		if (!Utils.validateEmail(email)) {
			Toast.makeText(context, context.getString(R.string.email_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		if (password.length() < 6) {
			Toast.makeText(context,
					context.getString(R.string.password_length_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		if (!password.equals(confirmPassword)) {
			Toast.makeText(context,
					context.getString(R.string.two_password_error),
					Toast.LENGTH_LONG).show();
			return;
		}

		if (Utils.validatePhoneNumber(phoneNumber)) {
			Toast.makeText(context,
					context.getString(R.string.phonenumber_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		String countryCode = Locale.getDefault().getCountry();
		String languageCode = Locale.getDefault().getLanguage();
		String[] params = { email, password, firstName, lastName, phoneNumber,
				languageCode, Constants.UserGroup.DRIVER, countryCode };
		new RegisterAsyncTask().execute(params);

	}

	public void parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			// get message from json
			System.out.println(response);
			String message = jsonObject.getString("mesage");
			// success
			// move to mapactivity and save to database offline
			if (!response.equalsIgnoreCase("")) {
				// save to database offline

				// move to map activity
				Intent it = new Intent(activity, LoginActivity.class);
				activity.startActivity(it);
				activity.finish();
			}
		} catch (JSONException e) {
			Toast.makeText(activity,
					activity.getString(R.string.wrong_email_or_password),
					Toast.LENGTH_LONG).show();
		}
	}

	public class RegisterAsyncTask extends AsyncTask<String, Void, String> {

		private ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(activity);
			pd.setTitle(activity.getString(R.string.register));
			pd.setMessage(activity.getString(R.string.register));
			pd.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData(params);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			System.out.println(result);
			if (result != null) {
				parseJson(result);
			}
			if (pd.isShowing()) {
				pd.dismiss();
			}
		}
	}

	public String postData(String[] params) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.REGISTER_DRIVER);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("email", params[0]));
			nameValuePairs.add(new BasicNameValuePair("password", params[1]));
			nameValuePairs.add(new BasicNameValuePair("firstname", params[2]));
			nameValuePairs.add(new BasicNameValuePair("lastname", params[3]));
			nameValuePairs
					.add(new BasicNameValuePair("phonenumber", params[4]));
			nameValuePairs.add(new BasicNameValuePair("language", params[5]));
			nameValuePairs.add(new BasicNameValuePair("usergroup", params[6]));
			nameValuePairs
					.add(new BasicNameValuePair("countrycode", params[7]));

			nameValuePairs.add(new BasicNameValuePair("role", driver));
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
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return null;
	}

}
