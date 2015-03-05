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
import vn.co.taxinet.mobile.bo.RegisterBO.RegisterAsyncTask;
import vn.co.taxinet.mobile.database.DatabaseHandler;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.newactivity.MapActivity;
import vn.co.taxinet.mobile.utils.Constants;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class TripBO {

	private Activity activity;
	private String account, password;
	private ProgressDialog pd;

	public void CreateTrip(Activity context, String driverid, String riderid,
			String start_lat, String start_lng, String end_lat, String end_lng, String status) {
		this.activity = context;
		String[] params = {driverid,riderid,start_lat,start_lng,end_lng};
		new CreateTripAsyncTask().execute(params);

	}

	public class CreateTripAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(activity);
			pd.setTitle("Sending Request");
			pd.setMessage("Please wait!");
			pd.setCancelable(false);
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
			if (result != null) {
				parseJson(result);
			}
			if (pd.isShowing()) {
				pd.dismiss();
			}
		}

	}

	public String parseJson(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			// get message from json
			System.out.println(response);
			String message = jsonObject.getString("mesage");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//
		return null;

	}

	public String postData(String[] params) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.CREATE_TRIP);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("driverId", params[0]));
			nameValuePairs.add(new BasicNameValuePair("riderId", params[1]));
//			nameValuePairs.add(new BasicNameValuePair("start_lat", params[2]));
//			nameValuePairs.add(new BasicNameValuePair("start_lng", params[3]));
//			nameValuePairs.add(new BasicNameValuePair("end_lng", params[4]));
//			nameValuePairs.add(new BasicNameValuePair("end_lng", params[5]));
			nameValuePairs.add(new BasicNameValuePair("latitude", params[2]));
			nameValuePairs.add(new BasicNameValuePair("longitude", params[3]));
			nameValuePairs.add(new BasicNameValuePair("status", params[4]));
			
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
