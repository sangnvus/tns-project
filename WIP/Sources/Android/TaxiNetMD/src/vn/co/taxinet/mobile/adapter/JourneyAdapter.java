package vn.co.taxinet.mobile.adapter;

import java.util.ArrayList;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.model.Journey;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class JourneyAdapter extends ArrayAdapter<Journey> {
	private Context context;
	private int textViewResourceId;
	private ArrayList<Journey> journeyItems;
	private TextView starting_point, stopping_point;

	public JourneyAdapter(Context context, int textViewResourId,
			ArrayList<Journey> journeyItems) {
		super(context, textViewResourId, journeyItems);
		this.context = context;
		this.textViewResourceId = textViewResourId;
		this.journeyItems = journeyItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(textViewResourceId, null);
		}
		initialize(v);
		Journey journeyItem = journeyItems.get(position);
		starting_point.setText(journeyItem.getStarting_point());
		stopping_point.setText(journeyItem.getStopping_point());
		return v;
	}

	public void initialize(View v) {
		starting_point = (TextView) v.findViewById(R.id.starting_point);
		stopping_point = (TextView) v.findViewById(R.id.stopping_point);
	}
}
