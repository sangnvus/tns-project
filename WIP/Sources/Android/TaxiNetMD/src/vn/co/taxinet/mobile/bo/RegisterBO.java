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
import vn.co.taxinet.mobile.activity.LoginActivity;
import vn.co.taxinet.mobile.newactivity.MapActivity;
import vn.co.taxinet.mobile.utils.Const;
import vn.co.taxinet.mobile.utils.Validator;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

public class RegisterBO {

	private Activity activity;
	private String email, password, firstName, lastName, phoneNumber;
	private String driver = "driver";

	public void checkRegisterInfo(Activity context, String email,
			String password, String confirmPassword, String firstName,
			String lastName, String phoneNumber) {
		this.activity = context;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;

		Validator validator = new Validator();
		if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
				|| TextUtils.isEmpty(confirmPassword)
				|| TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)
				|| TextUtils.isEmpty(phoneNumber)) {
			Toast.makeText(context,
					context.getString(R.string.register_blank_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		if (!validator.validateEmail(email)) {
			Toast.makeText(context, context.getString(R.string.email_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		if (!password.equals(confirmPassword)) {
			Toast.makeText(context,
					context.getString(R.string.two_password_error),
					Toast.LENGTH_LONG).show();
			return;
		}
		new RegisterAsyncTask().execute();
		// if (!validator.validatePhoneNumber(phoneNumber)) {
		// Toast.makeText(context,
		// context.getString(R.string.phonenumber_error),
		// Toast.LENGTH_LONG).show();
		// return null;
		// }
	}

	public void parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			// get message from json
			String message = jsonObject.getString("mesage");
			// success
			// move to mapactivity and save to database offline
			if (message.equalsIgnoreCase(Const.SUCCESS)) {
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

	public class RegisterAsyncTask extends AsyncTask<Void, Void, String> {

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
		HttpPost httppost = new HttpPost(Const.URL_LOGIN_AUTHEN);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("email", email));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("firstname", firstName));
			nameValuePairs.add(new BasicNameValuePair("lastname", lastName));
			nameValuePairs.add(new BasicNameValuePair("phonenumber",
					phoneNumber));

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
