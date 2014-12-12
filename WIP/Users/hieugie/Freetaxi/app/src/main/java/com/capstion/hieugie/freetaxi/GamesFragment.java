package com.capstion.hieugie.freetaxi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.capstion.hieugie.freetaxi.R.layout.fragment_top_rated;

/**
 * Created by Administrator on 12/12/2014.
 */
public class GamesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(fragment_top_rated, container, false);

        return rootView;
    }
}
