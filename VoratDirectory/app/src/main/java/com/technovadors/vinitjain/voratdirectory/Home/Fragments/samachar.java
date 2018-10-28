package com.technovadors.vinitjain.voratdirectory.Home.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technovadors.vinitjain.voratdirectory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class samachar extends Fragment {


    RecyclerView recyclerView;

    public samachar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_samachar_fragment, container, false);
    }

}
