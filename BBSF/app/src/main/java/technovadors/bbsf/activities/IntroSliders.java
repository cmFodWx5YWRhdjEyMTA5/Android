package technovadors.bbsf.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import technovadors.bbsf.R;
import technovadors.bbsf.fragments.IntroFragment;

public class IntroSliders extends AppCompatActivity {

    ViewPager pager;
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_sliders);
        initiailize_views();
    }

    void initiailize_views()
    {
        pager = findViewById(R.id.pager);
        pager_adapter adapter = new pager_adapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pager.getCurrentItem() == 2) {
                    View view = LayoutInflater.from(IntroSliders.this).inflate(R.layout.login_resgister, null);
                    TextView skip, forgot_password, login;
                    EditText login_mobile, login_password, full_name, register_mobile, confirm_password, register_password;
                    final LinearLayout login_register, register_holder, login_layout;

                    skip = view.findViewById(R.id.skip);
                    login_mobile = view.findViewById(R.id.login_mobile);
                    login_password = view.findViewById(R.id.login_password);
                    forgot_password = view.findViewById(R.id.forgot_password);
                    login = view.findViewById(R.id.login);
                    login_register = view.findViewById(R.id.login_register);
                    full_name = view.findViewById(R.id.full_name);
                    register_mobile = view.findViewById(R.id.register_mobile);
                    confirm_password = view.findViewById(R.id.confirm_password);
                    register_password = view.findViewById(R.id.register_password);
                    register_holder = view.findViewById(R.id.register_holder);
                    login_layout = view.findViewById(R.id.login_layout);

                    DialogPlus dialog = DialogPlus.newDialog(IntroSliders.this)
                            .setContentHolder(new ViewHolder(view))
                            .setGravity(Gravity.BOTTOM)
                            .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                            .create();

                    login_register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            login_layout.setVisibility(View.GONE);
                            register_holder.setVisibility(View.VISIBLE);
                        }
                    });


                    skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(IntroSliders.this, Home.class));
                        }
                    });

                    dialog.show();
                } else {
                    pager.setCurrentItem(pager.getCurrentItem() + 1);
                }
            }
        });
    }

    class pager_adapter extends FragmentPagerAdapter {

        public pager_adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment=new IntroFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("position",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

