package technovadors.bbsf.activities;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import technovadors.bbsf.R;

public class Splash extends AppCompatActivity {

    private LinearLayout language_layout;
    private TextView hindi, english;
    private ImageView logo;
    private SharedPreferences language_preference;
    private Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language_preference = getSharedPreferences("language_data", MODE_PRIVATE);
        if (language_preference.contains("language_id")) {
            String languageToLoad = language_preference.getString("language", "en"); // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_splash);
        initialize_views();
        setup_view();
    }

    private void initialize_views() {
        language_layout = findViewById(R.id.language_layout);
        hindi = findViewById(R.id.hindi);
        logo = findViewById(R.id.logo);
        english = findViewById(R.id.english);
        ;
    }

    private void setup_view() {

        Picasso.get().load(R.drawable.shield).centerInside().resize(250, 250).into(logo);

        if (!language_preference.contains("language_id")) {
            YoYo.with(Techniques.SlideInUp)
                    .duration(3000)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            language_layout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            hindi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    set_language(0);
                                }
                            });


                            english.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    set_language(1);
                                }
                            });
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .playOn(language_layout);
        } else {
            startActivity(new Intent(Splash.this, IntroSliders.class));
        }
    }

    private void set_language(int language) {
        String lang = "";
        // 0 - Hindi | 1 - English
        if (language == 0) {
            lang = "" + language;
            language_preference.edit().putString("language_id", lang).commit();
            lang = "hi";
            language_preference.edit().putString("language", lang).commit();
        } else {
            lang = "" + language;
            language_preference.edit().putString("language_id", lang).commit();
            lang = "en";
            language_preference.edit().putString("language", lang).commit();
        }

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Splash.class);
        startActivity(refresh);
    }

}
