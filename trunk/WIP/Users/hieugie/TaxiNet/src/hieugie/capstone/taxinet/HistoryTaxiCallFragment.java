package hieugie.capstone.taxinet;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryTaxiCallFragment extends Fragment {
	
	public HistoryTaxiCallFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_history_taxi_call, container, false);
         
        return rootView;
    }
}
