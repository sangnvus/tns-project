package vn.co.taxinet.mobile.adapter;

import vn.co.taxinet.mobile.R;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class TabsPagerAdapter extends PagerAdapter {

	@Override
	public int getCount() {
		return 2;
	}

	public Object instantiateItem(View collection, int position) {

		LayoutInflater inflater = (LayoutInflater) collection.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		int resId = 0;
		switch (position) {
		case 0:
			resId = R.layout.fragment_history_call;
			break;
		case 1:
			resId = R.layout.fragment_history_call;
			break;
		}

		View view = inflater.inflate(resId, null);

		((ViewPager) collection).addView(view, 0);

		return view;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);

	}

	@Override
	public void finishUpdate(View arg0) {

	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);

	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

}
