package vn.co.taxinet.mobile.fragment;

import java.util.ArrayList;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.activity.FavoriteDriverDetails;
import vn.co.taxinet.mobile.adapter.DriverAdapter;
import vn.co.taxinet.mobile.model.Driver;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FavoriteDriverFragment extends Fragment {

	public FavoriteDriverFragment() {
	}

	private ListView mFavouriteDriverList;
	private ArrayList<Driver> driverItems = new ArrayList<Driver>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_favorite_driver,
				container, false);
		mFavouriteDriverList = (ListView) rootView
				.findViewById(R.id.favorite_driver_list);
		driverItems.add(new Driver(1, "Đào Trung hiếu", "01683449693", true));
		driverItems.add(new Driver(2, "Đinh Quang Dương", "043113", false));
		driverItems.add(new Driver(3, "Trương Hoàng Hà", "043114", false));
		final DriverAdapter driverAdapter = new DriverAdapter(
				rootView.getContext(), R.layout.item_driver, driverItems);
		mFavouriteDriverList.setAdapter(driverAdapter);

		mFavouriteDriverList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long duration) {
				Intent it = new Intent(rootView.getContext(),
						FavoriteDriverDetails.class);
				it.putExtra("favorite driver item", driverItems.get(position));
				startActivity(it);
			}
		});

		return rootView;
	}
}
