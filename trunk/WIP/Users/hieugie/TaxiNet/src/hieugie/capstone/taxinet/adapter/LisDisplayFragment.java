/**
 * 
 */
package hieugie.capstone.taxinet.adapter;

import hieugie.capstone.taxinet.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Hieu-Gie
 *
 */
public class LisDisplayFragment extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_call_history, container, false);
         
        return rootView;
    }
}
