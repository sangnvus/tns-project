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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.newactivity.PaymentActivity;
import vn.co.taxinet.mobile.utils.Constants;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.Toast;

public class MapBO extends AsyncTask<String, Void, String> {

	private Activity activity;
	private ProgressDialog pd;
	private String action;
	private boolean flag;

	public MapBO(Activity activity, boolean flag) {
		this.activity = activity;
		this.flag = flag;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pd = new ProgressDialog(activity);
		if (flag) {
			pd.setTitle(activity.getString(R.string.response_request));
			pd.setMessage(activity.getString(R.string.response_request_message));
			pd.setCancelable(false);
			pd.show();
		}

	}

	@Override
	protected String doInBackground(String... params) {
		action = params[0];
		if (action.equalsIgnoreCase(Constants.RESPONSE_REQUEST)) {
			return responseRequest(params[1], params[2], params[3]);
		}
		if (action.equalsIgnoreCase(Constants.UPDATE_CURRENT_STATUS)) {
			return updateCurrentStatus(params[1], params[2], params[3],
					params[4], params[5]);
		}
		return null;

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (pd.isShowing()) {
			pd.dismiss();
		}
		if (result != null) {
			parseJson(result);
		}
	}

	public String responseRequest(String requestId, String status,
			String driverId) {
		// Create a new HttpClient and Post Header

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.UPDATE_TRIP);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("requestId", requestId));
			nameValuePairs.add(new BasicNameValuePair("status", status));
			nameValuePairs.add(new BasicNameValuePair("userId", driverId));

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

	public String updateCurrentStatus(String latitude, String longitude,
			String address, String status, String id) {
		Location location = new Location("location");
		location.setLongitude(Double.parseDouble(longitude));
		location.setLatitude(Double.parseDouble(latitude));
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		System.out.println(address);
		HttpPost httppost = new HttpPost(Constants.URL.UPDATE_CURRENT_STATUS);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("driverId", id));
			nameValuePairs.add(new BasicNameValuePair("longitude", longitude));
			nameValuePairs.add(new BasicNameValuePair("latitude", latitude));
			nameValuePairs.add(new BasicNameValuePair("status", status));
			nameValuePairs.add(new BasicNameValuePair("location", address));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8));
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
		// Toast.makeText(activity, activity.getString(R.string.error),
		// Toast.LENGTH_LONG).show();
		return null;
	}

	public void parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			String message = jsonObject.getString("message");
			if (message.equalsIgnoreCase(Constants.TripStatus.PICKED)) {
				Intent it = new Intent(activity, PaymentActivity.class);
				activity.startActivity(it);
				return;
			}
			if (message.equalsIgnoreCase(Constants.TripStatus.PICKING)) {
				Toast.makeText(activity, activity.getString(R.string.success),
						Toast.LENGTH_LONG).show();
			}

		} catch (JSONException e) {
			Toast.makeText(activity, activity.getString(R.string.error),
					Toast.LENGTH_LONG).show();
		}
	}

}
