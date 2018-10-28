package com.technovadors.vinitjain.voratdirectory.Home.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.technovadors.vinitjain.voratdirectory.Home.Fragments.gaav_list;
import com.technovadors.vinitjain.voratdirectory.Home.Fragments.our_services;
import com.technovadors.vinitjain.voratdirectory.Home.Fragments.samachar;
import com.technovadors.vinitjain.voratdirectory.R;

/**
 * Created by vinitjain on 30/01/18.
 */

public class TabsAdapter extends FragmentPagerAdapter{

    String[] titles=null;

    public TabsAdapter(FragmentManager fm,Context context) {
        super(fm);
        titles=context.getResources().getStringArray(R.array.home_tabs_titles);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
          return new samachar();
        }
        else if(position==1)
        {
            return new gaav_list();
        }
        else return new our_services();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
