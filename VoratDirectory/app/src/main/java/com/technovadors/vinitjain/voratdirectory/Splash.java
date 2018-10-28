package com.technovadors.vinitjain.voratdirectory;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.technovadors.vinitjain.voratdirectory.LoginRegister.Login;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class Splash extends AppCompatActivity {

    ImageView logo;
    Locale myLocale;
    String currentLanguage = "en", currentLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);
        initialize_views();
        if (isFirstTime())
        {
            ask_language_selection();
        }
        else
        {
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this, Login.class));
                }
            },2000);
        }

    }


    void ask_language_selection()
    {
        String[] languages = new String[]{"English", "हिन्दी"};
        ArrayAdapter adapter = new ArrayAdapter(Splash.this, R.layout.selectable_list_item_big,R.id.text, languages);
        DialogPlus dialog = DialogPlus.newDialog(Splash.this)
                .setGravity(Gravity.BOTTOM)
                .setAdapter(adapter).setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        if(position==0)
                        {
                            getSharedPreferences("generic", MODE_PRIVATE).edit().putString("locale", "en").commit();
                            setLocale("en");
                        }
                        else {
                            getSharedPreferences("generic", MODE_PRIVATE).edit().putString("locale", "hi").commit();
                            setLocale("hi");
                        }
                        startActivity(new Intent(Splash.this, Login.class));
                    }
                })
                .setHeader(R.layout.language_selection_header)
                .setCancelable(false)
                .create();

        dialog.show();
    }

    boolean isFirstTime() {
        if (getSharedPreferences("generic", MODE_PRIVATE).getString("first_time_flag", "0").equals("0")) {
            getSharedPreferences("generic", MODE_PRIVATE).edit().putString("first_time_flag", "1").commit();
            return true;
        }
        return false;
    }

    void initialize_views() {
        logo = findViewById(R.id.logo);
        Picasso picasso=Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.get().load(R.drawable.splash_logo).resize(logo.getMaxWidth(),logo.getMaxHeight()).into(logo);
    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, Splash.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(Splash.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
}
