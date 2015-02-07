package vn.co.taxinet.mobile.adapter;

import java.util.ArrayList;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.model.NavDrawerItem;
import vn.co.taxinet.mobile.utils.CircleImage;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private CircleImage circleImage;

	public NavDrawerListAdapter(Context context,
			ArrayList<NavDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.item_drawer, null);
		}

		RelativeLayout list_selector_title = (RelativeLayout) convertView
				.findViewById(R.id.list_selector_title);
		RelativeLayout list_selector_item = (RelativeLayout) convertView
				.findViewById(R.id.list_selector_item);
		LinearLayout profile = (LinearLayout) convertView
				.findViewById(R.id.profile);

		if (navDrawerItems.get(position).getType() == 1) {
			list_selector_item.setVisibility(View.GONE);
			profile.setVisibility(View.GONE);
			TextView txtTitle = (TextView) convertView
					.findViewById(R.id.list_title);
			txtTitle.setText(navDrawerItems.get(position).getTitle());
		} else if (navDrawerItems.get(position).getType() == 2) {
			list_selector_title.setVisibility(View.GONE);
			list_selector_item.setVisibility(View.GONE);
			ImageView profileImage = (ImageView) convertView
					.findViewById(R.id.profile_image);
			TextView profileName = (TextView) convertView
					.findViewById(R.id.profile_name);
			profileImage.setImageResource(R.drawable.ic_hieugie);
			profileName.setText(navDrawerItems.get(position).getTitle());
		} else {
			list_selector_title.setVisibility(View.GONE);
			profile.setVisibility(View.GONE);
			ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			TextView txtItem = (TextView) convertView
					.findViewById(R.id.list_item);
			TextView txtCount = (TextView) convertView
					.findViewById(R.id.counter);

			imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
			txtItem.setText(navDrawerItems.get(position).getTitle());

			// displaying count
			// check whether it set visible or not
			if (navDrawerItems.get(position).isCounterVisible()) {
				txtCount.setText(navDrawerItems.get(position).getCount());
			} else {
				// hide the counter view
				txtCount.setVisibility(View.GONE);
			}
		}

		return convertView;
	}
}
