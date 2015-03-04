package vn.co.taxinet.mobile.newactivity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.model.Driver;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FavoriteDriverDetails extends Activity {

	private TextView name, phone_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_driver_details);

		initalize();

		Bundle bd = getIntent().getExtras();
		if (bd.getSerializable("favorite driver item") != null) {
			Driver favoriteDriverItem = (Driver) bd
					.getSerializable("favorite driver item");
//			name.setText(favoriteDriverItem.getName());
//			phone_number.setText(favoriteDriverItem.getPhone_number());

		}
	}

	public void initalize() {
		name = (TextView) findViewById(R.id.name_of_taxi_driver);
		phone_number = (TextView) findViewById(R.id.phone_number_value);
	}

}
