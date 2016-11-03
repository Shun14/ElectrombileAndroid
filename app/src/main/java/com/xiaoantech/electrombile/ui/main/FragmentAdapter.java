package com.xiaoantech.electrombile.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangxu on 2016/11/1.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment>      mfragments;

    public FragmentAdapter(FragmentManager fragmentManager,List<Fragment> fragments){
        super(fragmentManager);
        this.mfragments = fragments;
    }

    public Fragment getItem(int fragment){
        return mfragments.get(fragment);
    }

    public int getCount(){
        return mfragments.size();
    }
}
