package hieugie.capstone.taxinet.adapter;

import hieugie.capstone.taxinet.R;
import hieugie.capstone.taxinet.model.FavoriteDriverItem;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DriverAdapter extends ArrayAdapter<FavoriteDriverItem> {
	private Context context;
	private int textViewResourceId;
	private ArrayList<FavoriteDriverItem> favoriteDriverItems;
	private TextView name, status;
	private ImageView status_icon;

	public DriverAdapter(Context context, int textViewResourId,
			ArrayList<FavoriteDriverItem> favoriteDriverItems) {
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
		FavoriteDriverItem favoriteDriverItem = favoriteDriverItems
				.get(position);
		name.setText(favoriteDriverItem.getName());
		if (favoriteDriverItem.isStatus()) {
			status.setText(R.string.avalible);
			status.setTextColor(Color.GREEN);
			status_icon.setImageResource(R.drawable.ic_avalible);
		} else {
			status.setText(R.string.busy);
			status.setTextColor(Color.RED);
			status_icon.setImageResource(R.drawable.ic_busy);

		}

		return v;
	}

	public void initialize(View v) {
		name = (TextView) v.findViewById(R.id.name_of_taxi_driver);
		status = (TextView) v.findViewById(R.id.staus_of_taxi_driver);
		status_icon = (ImageView) v
				.findViewById(R.id.icon_of_staus_of_taxi_driver);
	}
}
