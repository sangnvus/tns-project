package hieugie.capstone.taxinet.adapter;

import hieugie.capstone.taxinet.R;
import hieugie.capstone.taxinet.model.Driver;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DriverAdapter extends ArrayAdapter<Driver> {
	private Context context;
	private int textViewResourceId;
	private ArrayList<Driver> favoriteDriverItems;
	private TextView name;

	public DriverAdapter(Context context, int textViewResourId,
			ArrayList<Driver> favoriteDriverItems) {
		super(context, textViewResourId, favoriteDriverItems);
		this.context = context;
		this.textViewResourceId = textViewResourId;
		this.favoriteDriverItems = favoriteDriverItems;
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
		Driver favoriteDriverItem = favoriteDriverItems
				.get(position);
		name.setText(favoriteDriverItem.getName());
		return v;
	}

	public void initialize(View v) {
		name = (TextView) v.findViewById(R.id.name_of_taxi_driver);
	}
}
