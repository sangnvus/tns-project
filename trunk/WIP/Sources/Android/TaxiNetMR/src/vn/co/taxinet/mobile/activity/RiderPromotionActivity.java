package vn.co.taxinet.mobile.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.DTO.PromotionDTO;
import vn.co.taxinet.mobile.adapter.PromotionAdatpter;
import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

/**
 * 
 * @author Hieu-Gie
 * 
 * @createDate 20/01/2014
 */

public class RiderPromotionActivity extends Activity {

	private ListView mPromotionList;
	private PromotionAdatpter adapter;
	private ProgressDialog pDialog;
	private List<PromotionDTO> promotionList = new ArrayList<PromotionDTO>();
	private static final String TAG = RiderPromotionActivity.class
			.getSimpleName();
	private MenuItem refreshMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		mPromotionList = (ListView) findViewById(R.id.list);
		adapter = new PromotionAdatpter(this, promotionList);
		mPromotionList.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request

		getPromotion();
	}

	public void getPromotion() {
		JsonArrayRequest movieReq = new JsonArrayRequest(
				Const.URL_GET_PROMOTION, new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								PromotionDTO promotion = new PromotionDTO();
								promotion.setPromotion_content(obj
										.getString("title"));
								promotion.setImageUri(obj.getString("image"));

								// adding movie to movies array
								promotionList.add(promotion);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						refreshMenuItem.collapseActionView();
				        // remove the progress bar view
				        refreshMenuItem.setActionView(null);
						
						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reload_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_refresh:
			// refresh
			refreshMenuItem = item;
			// load the data from server
			
			refreshMenuItem.setActionView(R.layout.action_progressbar);
			 
            refreshMenuItem.expandActionView();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
