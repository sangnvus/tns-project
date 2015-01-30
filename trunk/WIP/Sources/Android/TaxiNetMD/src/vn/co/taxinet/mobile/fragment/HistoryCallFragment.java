package vn.co.taxinet.mobile.fragment;

import java.util.ArrayList;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.adapter.DriverAdapter;
import vn.co.taxinet.mobile.model.Driver;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HistoryCallFragment extends Fragment {

	public HistoryCallFragment() {
	}

	private ListView mFavouriteDriverList;
	private ArrayList<Driver> drivers = new ArrayList<Driver>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_history_call,
				container, false);
		mFavouriteDriverList = (ListView) rootView
				.findViewById(R.id.history_call_list);
		drivers.add(new Driver(1, "Đào Trung hiếu",
				"01683449693", true));
		drivers.add(new Driver(2, "Đinh Quang Dương",
				"043113", false));
		final DriverAdapter driverAdapter = new DriverAdapter(
				rootView.getContext(), R.layout.item_history_call,
				drivers);
		mFavouriteDriverList.setAdapter(driverAdapter);


		return rootView;
	}
}
