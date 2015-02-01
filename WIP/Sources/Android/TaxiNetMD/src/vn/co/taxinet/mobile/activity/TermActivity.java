package vn.co.taxinet.mobile.activity;

import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class TermActivity extends Activity {

	private String tag_json_obj = "get_term";
	private ProgressDialog pDialog;
	private String TAG = TermActivity.class.getSimpleName();
	private String term;
	private TextView mTerm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms);
		mTerm = (TextView) findViewById(R.id.term);
//		getTerm();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_term, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent returnIntent = new Intent();
		switch (item.getItemId()) {
		case R.id.accept:
			returnIntent.putExtra("result", "OK");
			setResult(RESULT_OK, returnIntent);
			finish();
			return true;
		case R.id.deni:
			returnIntent.putExtra("result", "DENI");
			setResult(RESULT_CANCELED, returnIntent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * @author Hieu-Gie
	 * 
	 * @return chính sách sử dụng
	 */
	public void getTerm() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Const.URL_GET_TERM, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						parseJson(response);
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				}) {

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * @author Hieu-Gie
	 * 
	 * @param respone
	 */

	public void parseJson(JSONObject respone) {
		try {
			term = respone.getString("term");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
