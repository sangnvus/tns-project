package vn.co.taxinet.mobile.adapter;

import java.util.ArrayList;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.model.TaxiCompany;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaxiCompanyAdapter extends ArrayAdapter<TaxiCompany> {
	private Context context;
	private int textViewResourceId;
	private ArrayList<TaxiCompany> taxiCompanyItems;
	private TextView name_of_taxi_company;

	public TaxiCompanyAdapter(Context context, int textViewResourId,
			ArrayList<TaxiCompany> taxiCompanyItems) {
		super(context, textViewResourId, taxiCompanyItems);
		this.context = context;
		this.textViewResourceId = textViewResourId;
		this.taxiCompanyItems = taxiCompanyItems;
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
		TaxiCompany taxiCompanyItem = taxiCompanyItems.get(position);
		name_of_taxi_company.setText(taxiCompanyItem.getName());
		return v;
	}

	public void initialize(View v) {
		name_of_taxi_company = (TextView) v
				.findViewById(R.id.name_of_taxi_company);
	}
}
