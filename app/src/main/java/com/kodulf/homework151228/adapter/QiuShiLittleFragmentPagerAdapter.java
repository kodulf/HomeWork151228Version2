package com.kodulf.homework151228.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 15-12-29.
 */
public class QiuShiLittleFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public QiuShiLittleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        //return super.getPageTitle(position);
        String ret="无标题";

        switch(position){
            case 0:
                ret="专享";
                break;
            case 1:
                ret="视频";
                break;
            case 2:
                ret="纯文";
                break;
        }
        return ret;
    }
}
