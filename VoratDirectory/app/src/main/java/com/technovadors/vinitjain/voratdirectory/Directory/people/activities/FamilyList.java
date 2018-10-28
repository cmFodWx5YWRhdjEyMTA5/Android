package com.technovadors.vinitjain.voratdirectory.Directory.people.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.technovadors.vinitjain.voratdirectory.Directory.people.adapters.family_list_adapter_recyclerview;
import com.technovadors.vinitjain.voratdirectory.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class FamilyList extends AppCompatActivity {

    RecyclerView family_list;
    ImageButton back, filter;
    JSONArray families;
    family_list_adapter_recyclerview adapter;
    LinearLayout filter_layout, filter_by_samaj, filter_by_area, filter_by_gaav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_list_activity);
        initialize_views();
        set_click_events();
        get_data();
    }


    void initialize_views() {
        family_list = findViewById(R.id.family_list);
        back = findViewById(R.id.back);
        filter = findViewById(R.id.filter);
        filter_layout = findViewById(R.id.filter_layout);
        filter_by_samaj = findViewById(R.id.filter_by_samaj);
        filter_by_area = findViewById(R.id.filter_by_area);
        filter_by_gaav = findViewById(R.id.filter_by_gaav);
        families = new JSONArray();
    }

    void set_click_events() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (families.length() < 1) {

                } else {
                    setup_filter();
                }
            }
        });
    }


    void setup_filter() {
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter_layout.getVisibility() == View.GONE) {
                    filter_layout.setVisibility(View.VISIBLE);
                } else filter_layout.setVisibility(View.GONE);
            }
        });

        filter_by_samaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        filter_by_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        filter_by_gaav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void get_data()
    {

        families = new JSONArray();

        JSONObject object = new JSONObject();
        try
        {
            object.put("hof_name", "Vinod Kachhara");
            object.put("gaav_area", "Matunga, Mumbai | Rinched");
            object.put("family_name", "Kachhara Parivar");
            object.put("gaav_area", "Matunga, Mumbai | Rinched");
            object.put("hof_id", "001");
            object.put("family_id", "001");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        families.put(object);

        setup_list();
    }

    void setup_list() {
        adapter = new family_list_adapter_recyclerview(FamilyList.this, families);
        family_list.setLayoutManager(new LinearLayoutManager(FamilyList.this));
        family_list.setAdapter(adapter);
    }
}
