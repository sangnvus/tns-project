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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.newactivity.MapActivity;
import vn.co.taxinet.mobile.newactivity.PaymentActivity;
import vn.co.taxinet.mobile.utils.Constants;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class PaymentBO extends AsyncTask<String, Void, String> {

	private Activity activity;
	private ProgressDialog pd;
	private String action;

	public PaymentBO(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd = new ProgressDialog(activity);
		pd.setTitle(activity.getString(R.string.response_request));
		pd.setMessage(activity.getString(R.string.response_request_message));
		pd.setCancelable(false);
		pd.show();
	}

	@Override
	protected String doInBackground(String... params) {
		return pay(params);
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

	public String pay(String params[]) {
		// Create a new HttpClient and Post Header

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.UPDATE_TRIP);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("requestId", params[0]));
			nameValuePairs.add(new BasicNameValuePair("driverId", params[1]));
			nameValuePairs.add(new BasicNameValuePair("cost", params[2]));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			int respnseCode = response.getStatusLine().getStatusCode();
			if (respnseCode == 200) {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			Log.e(Constants.TAG, e.getMessage());
		} catch (IOException e) {
			Log.e(Constants.TAG, e.getMessage());
		}
		return null;
	}

	public void parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			String message = jsonObject.getString("message");
			if (message.equalsIgnoreCase(Constants.SUCCESS)) {
				Intent it = new Intent(activity, MapActivity.class);
				activity.startActivity(it);
				return;
			}

		} catch (JSONException e) {
			Toast.makeText(activity, activity.getString(R.string.error),
					Toast.LENGTH_LONG).show();
		}
	}

}
