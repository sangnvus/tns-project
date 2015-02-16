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
import vn.co.taxinet.mobile.newactivity.MapActivity;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class MapBO extends AsyncTask<String, Void, String> {

	private Activity activity;

	public MapBO(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected String doInBackground(String... params) {
		if (params[0].equalsIgnoreCase(Const.RESPONSE_REQUEST)) {
			return responseRequest();
		}
		if (params[0].equalsIgnoreCase(Const.UPDATE_CURRENT_STATUS)) {
			return updateCurrentStatus(params[1], params[2], params[3]);
		}
		return null;

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (result != null) {
			parseJson(result);
		}
	}

	public String responseRequest() {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Const.URL_LOGIN_AUTHEN);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			nameValuePairs.add(new BasicNameValuePair("requestId", ""));
			nameValuePairs.add(new BasicNameValuePair("message", ""));
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

	public String updateCurrentStatus(String longitude, String latitude,
			String status) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Const.URL_LOGIN_AUTHEN);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			nameValuePairs.add(new BasicNameValuePair("driverId", AppController
					.getDriverId()));
			nameValuePairs.add(new BasicNameValuePair("longitude", longitude));
			nameValuePairs.add(new BasicNameValuePair("latitude", latitude));
			nameValuePairs.add(new BasicNameValuePair("status", status));
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

	public String parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			// get message from json
			String message = jsonObject.getString("role");
			// success
			// move to mapactivity and save to database offline
			if (message.equalsIgnoreCase("driver")) {
				// save to database offline

				// move to map activity
				Intent it = new Intent(activity, MapActivity.class);
				activity.startActivity(it);

			}
		} catch (JSONException e) {
			Toast.makeText(activity,
					activity.getString(R.string.wrong_email_or_password),
					Toast.LENGTH_LONG).show();
		}

		//
		return null;

	}

}
