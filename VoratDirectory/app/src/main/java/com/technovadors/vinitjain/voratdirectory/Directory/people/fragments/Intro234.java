package com.technovadors.vinitjain.voratdirectory.Directory.people.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technovadors.vinitjain.voratdirectory.R;


public class Intro234 extends Fragment {

    ImageView image;
    TextView header, description;
    ListView requirement_set;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro2, container, false);
        image = view.findViewById(R.id.image);
        header = view.findViewById(R.id.header);
        description = view.findViewById(R.id.description);
        requirement_set = view.findViewById(R.id.requirement_set);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        switch (getArguments().getString("position"))
        {
            case "1":
            {
                header.setText(getText(R.string.home_details_needed_header));
                image.setImageResource(R.drawable.house);
                ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.home_details_needed));
                requirement_set.setAdapter(adapter);
                break;
            }

            case "2": {
                header.setText(getText(R.string.firm_details_needed_header));
                image.setImageResource(R.drawable.office_block);
                ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.firm_details_needed));
                requirement_set.setAdapter(adapter);
                requirement_set.setScrollBarSize(View.SCROLL_INDICATOR_LEFT);
                break;
            }

            case "3": {
                header.setText(getText(R.string.family_details_needed_header));
                image.setImageResource(R.drawable.big_family);
                ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.family_details_needed));
                requirement_set.setAdapter(adapter);
                break;
            }
        }
    }
}
