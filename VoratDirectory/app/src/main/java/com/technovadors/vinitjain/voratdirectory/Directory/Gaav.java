package com.technovadors.vinitjain.voratdirectory.Directory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technovadors.vinitjain.voratdirectory.Directory.people.activities.FamilyList;
import com.technovadors.vinitjain.voratdirectory.R;

import java.util.Random;

public class Gaav extends AppCompatActivity
{
    TextView family_count;
    ImageButton back;
    LinearLayout about_us, view_parivar, register_family, mitra_mandals, contact_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaav_activity);
        initialize_views();
        get_data();
        set_click_events();
    }

    void initialize_views() {
        family_count = findViewById(R.id.family_count);
        back = findViewById(R.id.back);
        about_us = findViewById(R.id.about_us);
        view_parivar = findViewById(R.id.view_parivar);
        register_family = findViewById(R.id.register_family);
        mitra_mandals = findViewById(R.id.mitra_mandals);
        contact_us = findViewById(R.id.contact_us);

        view_parivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gaav.this, FamilyList.class));
            }
        });
    }


    void get_data() {
        family_count.setText(new Random().nextInt(1000)+"");
    }


    void set_click_events() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
