package com.technovadors.vinitjain.voratdirectory.Home.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technovadors.vinitjain.voratdirectory.Home.Adapters.gaavListRecyclerAdapter;
import com.technovadors.vinitjain.voratdirectory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class gaav_list extends Fragment {


    RecyclerView list;
    JSONArray gaavs=null;


    public gaav_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_gaav_list_fragment, container, false);
        list = view.findViewById(R.id.list);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        get_data();
        setup_list();
    }


    void get_data() {

        try
        {
            gaavs = new JSONArray();
            JSONObject object = new JSONObject();
            object.put("gaav", "Rinched");
            object.put("active","1");
            object.put("abb","Ri");
            gaavs.put(object);

            object = new JSONObject();
            object.put("gaav", "Jheelwara");
            object.put("active","1");
            object.put("abb","Jh");
            gaavs.put(object);


            object = new JSONObject();
            object.put("gaav", "Majera");
            object.put("active","0");
            object.put("abb","Ma");
            gaavs.put(object);


            object = new JSONObject();
            object.put("gaav", "Nathudwara");
            object.put("active","1");
            object.put("abb","Na");
            gaavs.put(object);


            object = new JSONObject();
            object.put("gaav", "Sema");
            object.put("active","1");
            object.put("abb","Se");
            gaavs.put(object);


            object = new JSONObject();
            object.put("gaav", "Saloda");
            object.put("active","1");
            object.put("abb","Sa");
            gaavs.put(object);


            object = new JSONObject();
            object.put("gaav", "Shishoda");
            object.put("active","1");
            object.put("abb","Sh");
            gaavs.put(object);

            object = new JSONObject();
            object.put("gaav", "Gomati");
            object.put("active","0");
            object.put("abb","Go");
            gaavs.put(object);


            object = new JSONObject();
            object.put("gaav", "Charbuja");
            object.put("active","1");
            object.put("abb","Ch");
            gaavs.put(object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setup_list()
    {
        list.setLayoutManager(new GridLayoutManager(getActivity(),2));
        gaavListRecyclerAdapter adapter=new gaavListRecyclerAdapter(getActivity(),gaavs);
        list.setAdapter(adapter);
    }
}
