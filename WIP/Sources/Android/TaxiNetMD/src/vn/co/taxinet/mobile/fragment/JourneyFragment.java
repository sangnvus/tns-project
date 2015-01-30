package vn.co.taxinet.mobile.fragment;

import java.util.ArrayList;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.activity.JourneyDetails;
import vn.co.taxinet.mobile.adapter.JourneyAdapter;
import vn.co.taxinet.mobile.model.Journey;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


/**
 * @author hieu.daotrung
 * @date 14/12/2014
 * @lastupdate 14/12/2014
 */


public class JourneyFragment extends Fragment {

	public JourneyFragment() {
	}

	private ListView mJourneyList;
	private ArrayList<Journey> journeyItems = new ArrayList<Journey>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_manage_journey,
				container, false);
		mJourneyList = (ListView) rootView
				.findViewById(R.id.manage_journey_list);
		journeyItems.add(new Journey("Hà nội", "Nha trang"));
		journeyItems.add(new Journey("Hà nội", "Hồ chí minh"));
		final JourneyAdapter journeyAdapter = new JourneyAdapter(
				rootView.getContext(), R.layout.item_journey, journeyItems);
		mJourneyList.setAdapter(journeyAdapter);
		
		mJourneyList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long duration) {
					Intent it = new Intent(rootView.getContext(),JourneyDetails.class);
					it.putExtra("journey item details", journeyItems.get(position));
					startActivity(it);
			}
		});
		return rootView;
	}
	
	public class InternetHandler extends AsyncTask<Void, Integer, String> {


		private ProgressDialog pd;


		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			// set a Progress dialog
			pd.setMessage("Đang gửi dữ liệu");
			pd.setCancelable(false);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(Void... arg0) {

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// dismiss process dialog
			if (pd.isShowing()) {
				pd.dismiss();
			}

		}




	}

}
