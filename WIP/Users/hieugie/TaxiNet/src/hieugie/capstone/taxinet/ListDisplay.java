package hieugie.capstone.taxinet;

import java.util.ArrayList;

import hieugie.capstone.taxinet.adapter.DriverAdapter;
import hieugie.capstone.taxinet.model.Driver;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListDisplay extends Activity {

	private ListView mDriverList;
	private ArrayList<Driver> favoriteDriverItems = new ArrayList<Driver>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_driver_list_display);
		mDriverList = (ListView) findViewById(R.id.list);
		favoriteDriverItems.add(new Driver(1, "Đào Trung hiếu", "01683449693",
				true));
		favoriteDriverItems.add(new Driver(2, "Đinh Quang Dương", "043113",
				true));
		final DriverAdapter driverAdapter = new DriverAdapter(this,
				R.layout.driver_list_item, favoriteDriverItems);
		mDriverList.setAdapter(driverAdapter);

		mDriverList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long duration) {
				Intent it = new Intent(ListDisplay.this,
						FavoriteDriverDetails.class);
				it.putExtra("favorite driver item",
						favoriteDriverItems.get(position));
				startActivity(it);
			}

		});
	}
}
