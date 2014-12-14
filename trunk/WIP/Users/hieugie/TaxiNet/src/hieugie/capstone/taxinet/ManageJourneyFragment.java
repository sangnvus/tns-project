package hieugie.capstone.taxinet;

import hieugie.capstone.taxinet.adapter.JourneyAdapter;
import hieugie.capstone.taxinet.model.JourneyItem;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * @author hieu.daotrung
 * @date 14/12/2014
 * @lastupdate 14/12/2014
 */


public class ManageJourneyFragment extends Fragment {

	public ManageJourneyFragment() {
	}

	private ListView mJourneyList;
	private ArrayList<JourneyItem> journeyItems = new ArrayList<JourneyItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_manage_journey,
				container, false);
		mJourneyList = (ListView) rootView
				.findViewById(R.id.manage_journey_list);
		journeyItems.add(new JourneyItem("Hà nội", "Nha trang"));
		journeyItems.add(new JourneyItem("Hà nội", "Hồ chí minh"));
		final JourneyAdapter journeyAdapter = new JourneyAdapter(
				rootView.getContext(), R.layout.journey_list_item, journeyItems);
		mJourneyList.setAdapter(journeyAdapter);
		
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
