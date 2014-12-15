package hieugie.capstone.taxinet;

import hieugie.capstone.taxinet.adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapsFragment extends Fragment {

	public MapsFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_maps, container,
				false);
		TabsPagerAdapter adapter = new TabsPagerAdapter();
		ViewPager myPager = (ViewPager) rootView.findViewById(R.id.pager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);

		
		return rootView;
	}



}
