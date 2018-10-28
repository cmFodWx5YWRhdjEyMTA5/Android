package com.technovadors.vinitjain.voratdirectory.LoginRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.technovadors.vinitjain.voratdirectory.R;

public class DirectToLogin extends AppCompatActivity {

    TextView go_to_login,description;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_to_login);
        initalize_views();
        click_events();
        getIntentData();
    }

    void initalize_views() {
        image=findViewById(R.id.image);
        description=findViewById(R.id.description);
        go_to_login = findViewById(R.id.go_to_login);
    }

    void click_events() {
        go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DirectToLogin.this, Login.class));
            }
        });
    }

    void getIntentData()
    {
        description.setText(getIntent().getStringExtra("description"));
        image.setImageResource(getIntent().getIntExtra("image",0));
    }
}
