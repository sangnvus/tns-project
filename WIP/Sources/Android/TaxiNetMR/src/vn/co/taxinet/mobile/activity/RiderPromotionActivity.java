package vn.co.taxinet.mobile.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import vn.co.taxinet.mobile.R;

/**
 * 
 * @author Hieu-Gie
 * 
 * @createDate 20/01/2014
 */

public class RiderPromotionActivity extends Activity {

	private ListView mPromotionList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity_rider_promotion);

		mPromotionList = (ListView) findViewById(R.id.lv_promotion);
	}
}
