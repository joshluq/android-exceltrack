package pe.exceltransport.exceltrack.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.exceltransport.exceltrack.R;

public class TripListFragment extends Fragment {

    public static TripListFragment newInstance() {
        return new TripListFragment();
    }

    public TripListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_list, container, false);
    }


}
