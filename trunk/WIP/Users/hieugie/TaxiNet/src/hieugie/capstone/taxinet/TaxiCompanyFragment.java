package hieugie.capstone.taxinet;

import hieugie.capstone.taxinet.adapter.TaxiCompanyAdapter;
import hieugie.capstone.taxinet.model.TaxiCompanyItem;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TaxiCompanyFragment extends Fragment {

	public TaxiCompanyFragment() {
	}
	
	private ListView mTaxiCompanyList;
	private ArrayList<TaxiCompanyItem> taxiCompanyItems = new ArrayList<TaxiCompanyItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_taxi_company,
				container, false);
		mTaxiCompanyList = (ListView) rootView
				.findViewById(R.id.taxi_company_list);
		taxiCompanyItems.add(new TaxiCompanyItem("Hà nội", "Nha trang"));
		taxiCompanyItems.add(new TaxiCompanyItem("Hà nội", "Hồ chí minh"));
		final TaxiCompanyAdapter taxiCompanyAdapter = new TaxiCompanyAdapter(
				rootView.getContext(), R.layout.taxi_company_list_item, taxiCompanyItems);
		mTaxiCompanyList.setAdapter(taxiCompanyAdapter);
		return rootView;
	}
}
