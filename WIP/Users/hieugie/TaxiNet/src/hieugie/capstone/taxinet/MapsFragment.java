package hieugie.capstone.taxinet;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapsFragment extends Fragment {
	
	public MapsFragment(){}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }

}
