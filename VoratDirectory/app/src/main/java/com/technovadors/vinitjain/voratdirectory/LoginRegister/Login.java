package com.technovadors.vinitjain.voratdirectory.LoginRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.technovadors.vinitjain.voratdirectory.Directory.people.activities.IntermediateSummary;
import com.technovadors.vinitjain.voratdirectory.Directory.people.activities.NewFamily;
import com.technovadors.vinitjain.voratdirectory.Directory.people.activities.NewFamilyIntroductoryScreen;
import com.technovadors.vinitjain.voratdirectory.Home.Activities.Home;
import com.technovadors.vinitjain.voratdirectory.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

public class  Login extends AppCompatActivity {

    EditText id, password;
    TextView login, forgot, about_us, contact_us,register;
    ImageButton show_password;
    boolean hidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initialize_views();
        set_click_events();
    }

    void initialize_views() {
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        forgot = findViewById(R.id.forgot);
        about_us = findViewById(R.id.about_us);
        contact_us = findViewById(R.id.contact_us);
        show_password = findViewById(R.id.show_password);
        register=findViewById(R.id.register);
    }

    void set_click_events() {
        show_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_hide_password();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_forgot_options();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginValidated()) {

                }
                startActivity(new Intent(Login.this, Home.class));
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!no_preset_data())
                startActivity(new Intent(Login.this, NewFamilyIntroductoryScreen.class));
                else startActivity(new Intent(Login.this, IntermediateSummary.class));
            }
        });

    }

    boolean no_preset_data()
    {
        try
        {
            Log.d("stuck",getSharedPreferences("family_input", MODE_PRIVATE).getString("data",""));

            JSONObject family_details=new JSONObject(getSharedPreferences("family_input", MODE_PRIVATE).getString("data",""));
            if (family_details.has("firms")) {
                return true;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    boolean loginValidated() {
        if (id.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, R.string.enter_member_id, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, R.string.enter_password, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    void show_hide_password() {
        if (hidden) {
            hidden = !hidden;
            password.setTransformationMethod(null);
            show_password.setImageResource(R.drawable.show_password_active);
            if (!password.getText().toString().isEmpty()) {
                password.setSelection(password.getText().toString().length());
            }
        } else {
            hidden = !hidden;
            password.setTransformationMethod(new PasswordTransformationMethod());
            show_password.setImageResource(R.drawable.show_password_grey);
            if (!password.getText().toString().isEmpty()) {
                password.setSelection(password.getText().toString().length());
            }
        }
    }

    void show_forgot_options() {
        PopupMenu popup = new PopupMenu(this, forgot);
        popup.getMenuInflater().inflate(R.menu.login_forgot, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id: {
                        show_recover_id_dialog();
                        break;
                    }

                    case R.id.password: {

                        break;
                    }
                }
                return true;
            }
        });
        popup.show();
    }

    void show_recover_id_dialog() {
        View view = LayoutInflater.from(Login.this).inflate(R.layout.layout_recover_id, null);

        ImageButton close, get_gaav, get_families, get_family_member;
        TextView gaav, family, user, proceed;

        close = view.findViewById(R.id.close);
        get_gaav = view.findViewById(R.id.get_gaav);
        get_families = view.findViewById(R.id.get_families);
        get_family_member = view.findViewById(R.id.get_family_member);

        gaav = view.findViewById(R.id.gaav);
        family = view.findViewById(R.id.family);
        user = view.findViewById(R.id.user);
        proceed = view.findViewById(R.id.proceed);


        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(view))
                .setCancelable(false)
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
