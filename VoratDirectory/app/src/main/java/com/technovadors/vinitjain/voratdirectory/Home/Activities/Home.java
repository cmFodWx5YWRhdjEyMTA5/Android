package com.technovadors.vinitjain.voratdirectory.Home.Activities;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technovadors.vinitjain.voratdirectory.Home.Adapters.TabsAdapter;
import com.technovadors.vinitjain.voratdirectory.R;

import io.karim.MaterialTabs;


public class Home extends AppCompatActivity {

    MaterialTabs tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initialize_views();
        setup_tabs();
    }

    void initialize_views() {
        tabs = findViewById(R.id.tabs);
        tabs.setTextColorUnselected(Color.parseColor("#CCCCCC"));
        pager = findViewById(R.id.pager);
    }

    void setup_tabs() {
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), Home.this);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }
}
