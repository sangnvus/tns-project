package vn.co.taxinet.mobile.fragment;

import vn.co.taxinet.mobile.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapsFragment extends Fragment {

	public MapsFragment() {
	}

	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		return rootView;
	}
	


}
