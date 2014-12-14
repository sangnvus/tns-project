package hieugie.capstone.taxinet;

import hieugie.capstone.taxinet.adapter.DriverAdapter;
import hieugie.capstone.taxinet.model.FavoriteDriverItem;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FavoriteTaxiFragment extends Fragment {

	public FavoriteTaxiFragment() {
	}

	private ListView mFavouriteDriverList;
	private ArrayList<FavoriteDriverItem> driverItems = new ArrayList<FavoriteDriverItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_favorite_taxi,
				container, false);
		mFavouriteDriverList = (ListView) rootView
				.findViewById(R.id.favorite_taxi_list);
		driverItems.add(new FavoriteDriverItem(1, "Đào Trung hiếu", true));
		driverItems.add(new FavoriteDriverItem(2, "Đinh Quang Dương", false));
		final DriverAdapter driverAdapter = new DriverAdapter(
				rootView.getContext(), R.layout.favorite_driver_list_item,
				driverItems);
		mFavouriteDriverList.setAdapter(driverAdapter);
		return rootView;
	}
}
