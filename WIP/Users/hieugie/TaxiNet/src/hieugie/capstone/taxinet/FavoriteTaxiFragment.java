package hieugie.capstone.taxinet;

import hieugie.capstone.taxinet.adapter.DriverAdapter;
import hieugie.capstone.taxinet.model.FavoriteDriverItem;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FavoriteTaxiFragment extends Fragment {

	public FavoriteTaxiFragment() {
	}

	private ListView mFavouriteDriverList;
	private ArrayList<FavoriteDriverItem> favoriteDriverItems = new ArrayList<FavoriteDriverItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_favorite_taxi,
				container, false);
		mFavouriteDriverList = (ListView) rootView
				.findViewById(R.id.favorite_taxi_list);
		favoriteDriverItems.add(new FavoriteDriverItem(1, "Đào Trung hiếu",
				"01683449693", true));
		favoriteDriverItems.add(new FavoriteDriverItem(2, "Đinh Quang Dương",
				"043113", false));
		final DriverAdapter driverAdapter = new DriverAdapter(
				rootView.getContext(), R.layout.favorite_driver_list_item,
				favoriteDriverItems);
		mFavouriteDriverList.setAdapter(driverAdapter);

		mFavouriteDriverList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long duration) {
				Intent it = new Intent(rootView.getContext(),
						FavoriteDriverDetails.class);
				it.putExtra("favorite driver item",
						favoriteDriverItems.get(position));
				startActivity(it);
			}

		});

		return rootView;
	}
}
