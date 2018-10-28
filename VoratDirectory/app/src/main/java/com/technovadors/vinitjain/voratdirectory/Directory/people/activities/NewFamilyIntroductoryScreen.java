package com.technovadors.vinitjain.voratdirectory.Directory.people.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.Scroller;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technovadors.vinitjain.voratdirectory.Directory.people.fragments.Intro1;
import com.technovadors.vinitjain.voratdirectory.Directory.people.fragments.Intro234;
import com.technovadors.vinitjain.voratdirectory.LoginRegister.Login;
import com.technovadors.vinitjain.voratdirectory.R;

import java.lang.reflect.Field;

public class NewFamilyIntroductoryScreen extends AppCompatActivity
{

    private ViewPager pager;
    private fragment_pager_adapter adapter;
    TextView action_text;
    ImageButton action_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_family_introductory_screen);

        initialize_views();
        setup_slider();
    }

    void initialize_views()
    {
        pager=findViewById(R.id.pager);
        action_text=findViewById(R.id.action_text);
        action_button=findViewById(R.id.action_button);
    }

    void setup_slider()
    {
        adapter =new fragment_pager_adapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        action_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pager.getCurrentItem()==3)
                {
                    startActivity(new Intent(NewFamilyIntroductoryScreen.this, NewFamily.class));
                }
                else pager.setCurrentItem(pager.getCurrentItem()+1);
            }
        });
    }


    class fragment_pager_adapter extends FragmentPagerAdapter{

        public fragment_pager_adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=new Fragment();
            if(position==0)
            {
                fragment=new Intro1();
            }
            else
            {
                fragment=new Intro234();
                Bundle bundle=new Bundle();
                bundle.putString("position",position+"");
                fragment.setArguments(bundle);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }



    public class NonSwipeableViewPager extends ViewPager {

        public NonSwipeableViewPager(Context context) {
            super(context);
            setMyScroller();
        }

        public NonSwipeableViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
            setMyScroller();
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent event) {
            // Never allow swiping to switch between pages
            return false;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // Never allow swiping to switch between pages
            return false;
        }

        //down one is added for smooth scrolling

        private void setMyScroller() {
            try {
                Class<?> viewpager = ViewPager.class;
                Field scroller = viewpager.getDeclaredField("mScroller");
                scroller.setAccessible(true);
                scroller.set(this, new MyScroller(getContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public class MyScroller extends Scroller {
            public MyScroller(Context context) {
                super(context, new DecelerateInterpolator());
            }

            @Override
            public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                super.startScroll(startX, startY, dx, dy, 350 /*1 secs*/);
            }
        }
    }
}
