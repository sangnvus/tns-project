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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.bo.TripBO.CreateTripAsyncTask;
import vn.co.taxinet.mobile.model.Driver;
import vn.co.taxinet.mobile.utils.Constants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetDriverBO {

	private Activity activity;
	private String account, password;
	private ProgressDialog pd;
	ArrayList<Driver> listDriver;
	private GoogleMap googleMap;

	public GetDriverBO(Activity context, String lat,
			String lng, GoogleMap googleMap) {
		this.activity = context;
		this.googleMap = googleMap;
		String[] params = { lat, lng };
		new getDriverAsyncTask().execute(params);
	}

	public class getDriverAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(activity);
			pd.setTitle("Sending Request");
			pd.setMessage("Please wait!");
			pd.setCancelable(false);
			// pd.show();
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
			if (pd.isShowing()) {
				pd.dismiss();
			}
		}

	}

	public void parseJson(String response) {
		try {
			listDriver = new ArrayList<Driver>();
			// get message from json
			System.out.println("response : " + response);
			JSONArray jsonArray = new JSONArray(response);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String id = jsonObject.getString("id");
				double longitude = Double.parseDouble(jsonObject
						.getString("longitude"));
				double latitude = Double.parseDouble(jsonObject
						.getString("latitude"));
				String firstname = jsonObject.getString("firstName");
				String lastname = jsonObject.getString("lastName");
				String image = jsonObject.getString("image");
				Driver x = new Driver(id, firstname, lastname, image,
						longitude, latitude);
				listDriver.add(x);
			}
			for (int i = 0; i < listDriver.size(); i++) {
				Driver driver = listDriver.get(i);
				System.out.println(driver.getFirstName());
				LatLng latLng = new LatLng(driver.getLatitude(),
						driver.getLongitude());
				MarkerOptions markerOptions = new MarkerOptions();
				markerOptions.title(driver.getFirstName() + " "
						+ driver.getLastName());
				//markerOptions.snippet(distance);
				markerOptions.position(latLng);
				googleMap.addMarker(markerOptions);
				AppController.setListDrivers(listDriver);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		//

	}

	public String postData(String[] params) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Constants.URL.GET_DRIVER);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("latitude", params[0]));
			nameValuePairs.add(new BasicNameValuePair("longitude", params[1]));
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
