package vn.co.taxinet.mobile.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.utils.Const;
import vn.co.taxinet.mobile.utils.Validator;
import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class LoginBO {

	Validator validator = new Validator();

	public void checkLoginInfo(Activity activity, String account,
			String password) {

		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
			// return Const.EMPTY_ERROR;
		}
		// if (validator.validateEmail(account)
		// || validator.validatePhoneNumber(account)) {
		// return Const.ACCOUNT_ERROR;
		// }
		new a().execute();
//		loginAuthen(activity);
	}

	public String parseJson(JSONObject response) {
		try {
			String responseCode = response.getString("responseCode");
			return responseCode;
		} catch (JSONException e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

	public void loginAuthen(final Activity activity) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", "qwe");
		params.put("password", "deathknight");
		
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
				Const.URL_LOGIN_AUTHEN, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(Const.TAG, response.toString());
						String responseCode = parseJson(response);
						System.out.println(response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(Const.TAG, "Error: " + error.getMessage());
					}
				}) {

//			@Override
//			public Map<String, String> getHeaders() throws AuthFailureError {
//				HashMap<String, String> headers = new HashMap<String, String>();
//				headers.put("Content-Type", "application/json; charset=utf-8");
//				return headers;
//			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("userName", "qwe");
				params.put("password", "deathknight");

				return params;
			}

		};

		String tag_json_obj = "aaa";
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}
	public class a extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			postData();
			return null;
		}
		
	}
	
	
	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(Const.URL_LOGIN_AUTHEN);
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("userName", "Hiếu"));
	        nameValuePairs.add(new BasicNameValuePair("password", "deathknight"));
//	        httppost.setHeader("Content-Type","application/json;charset=UTF-8");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} 
}
