package hieugie.capstone.taxinet;

import hieugie.capstone.taxinet.model.FavoriteDriverItem;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FavoriteDriverDetails extends Activity {

	private TextView name, phone_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_driver_item_details);

		initalize();

		Bundle bd = getIntent().getExtras();
		if (bd.getSerializable("favorite driver item") != null) {
			FavoriteDriverItem favoriteDriverItem = (FavoriteDriverItem) bd
					.getSerializable("favorite driver item");
			name.setText(favoriteDriverItem.getName());
			phone_number.setText(favoriteDriverItem.getPhone_number());

		}
	}

	public void initalize() {
		name = (TextView) findViewById(R.id.name_of_taxi_driver);
		phone_number = (TextView) findViewById(R.id.phone_number_value);
	}

}
