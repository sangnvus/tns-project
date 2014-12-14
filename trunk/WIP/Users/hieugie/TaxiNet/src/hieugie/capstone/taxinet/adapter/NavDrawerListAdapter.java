package hieugie.capstone.taxinet.adapter;

import hieugie.capstone.taxinet.R;
import hieugie.capstone.taxinet.model.NavDrawerItem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;

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
			convertView = mInflater.inflate(R.layout.driver_list_item, null);
		}

		RelativeLayout list_selector_title = (RelativeLayout) convertView
				.findViewById(R.id.list_selector_title);
		RelativeLayout list_selector_item = (RelativeLayout) convertView
				.findViewById(R.id.list_selector_item);

		if (navDrawerItems.get(position).isTitle()) {
			list_selector_item.setVisibility(View.GONE);
			TextView txtTitle = (TextView) convertView
					.findViewById(R.id.list_title);
			txtTitle.setText(navDrawerItems.get(position).getTitle());
		} else {
			list_selector_title.setVisibility(View.GONE);
			ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			TextView txtItem = (TextView) convertView
					.findViewById(R.id.list_item);
			TextView txtCount = (TextView) convertView
					.findViewById(R.id.counter);

			imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
			txtItem.setText(navDrawerItems.get(position).getTitle());

			// displaying count
			// check whether it set visible or not
			if (navDrawerItems.get(position).getCounterVisibility()) {
				txtCount.setText(navDrawerItems.get(position).getCount());
			} else {
				// hide the counter view
				txtCount.setVisibility(View.GONE);
			}
		}

		return convertView;
	}

}
