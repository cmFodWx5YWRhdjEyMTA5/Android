package com.technovadors.vinitjain.voratdirectory.Home.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technovadors.vinitjain.voratdirectory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class our_services extends Fragment {

    ImageView service_1_toggle,service_2_toggle,service_3_toggle,service_4_toggle,service_5_toggle;
    LinearLayout service_1,service_2,service_3,service_4,service_5;
    TextView service_1_description,service_2_description,service_3_description,service_4_description,service_5_description;
    public our_services() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.home_our_services_fragment, container, false);

        service_1_toggle=view.findViewById(R.id.service_1_toggle);
        service_2_toggle=view.findViewById(R.id.service_2_toggle);
        service_3_toggle=view.findViewById(R.id.service_3_toggle);
        service_4_toggle=view.findViewById(R.id.service_4_toggle);
        service_5_toggle=view.findViewById(R.id.service_5_toggle);

        service_1=view.findViewById(R.id.service_1);
        service_2=view.findViewById(R.id.service_2);
        service_3=view.findViewById(R.id.service_3);
        service_4=view.findViewById(R.id.service_4);
        service_5=view.findViewById(R.id.service_5);

        service_1_description=view.findViewById(R.id.service_1_description);
        service_2_description=view.findViewById(R.id.service_2_description);
        service_3_description=view.findViewById(R.id.service_3_description);
        service_4_description=view.findViewById(R.id.service_4_description);
        service_5_description=view.findViewById(R.id.service_5_description);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup_click_events();
    }

    void setup_click_events()
    {
        service_1_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service_1_description.getVisibility()== View.GONE)
                {
                    service_1_description.setVisibility(View.VISIBLE);
                    service_1_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.up_normal));
                }
                else {
                    service_1_description.setVisibility(View.GONE);
                    service_1_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.next_normal));
                }
            }
        });

        service_2_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service_2_description.getVisibility()== View.GONE)
                {
                    service_2_description.setVisibility(View.VISIBLE);
                    service_2_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.up_normal));
                }
                else {
                    service_2_description.setVisibility(View.GONE);
                    service_2_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.next_normal));
                }
            }
        });

        service_3_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service_3_description.getVisibility()== View.GONE)
                {
                    service_3_description.setVisibility(View.VISIBLE);
                    service_3_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.up_normal));
                }
                else {
                    service_3_description.setVisibility(View.GONE);
                    service_3_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.next_normal));
                }
            }
        });

        service_4_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service_4_description.getVisibility()== View.GONE)
                {
                    service_4_description.setVisibility(View.VISIBLE);
                    service_4_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.up_normal));
                }
                else {
                    service_4_description.setVisibility(View.GONE);
                    service_4_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.next_normal));
                }
            }
        });

        service_5_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service_5_description.getVisibility()== View.GONE)
                {
                    service_5_description.setVisibility(View.VISIBLE);
                    service_5_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.up_normal));
                }
                else {
                    service_5_description.setVisibility(View.GONE);
                    service_5_toggle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.next_normal));
                }
            }
        });
    }
}
