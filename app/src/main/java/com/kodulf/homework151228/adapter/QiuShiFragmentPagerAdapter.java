package com.kodulf.homework151228.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kodulf.homework151228.fragments.QiuShiFragment;

import java.util.List;

/**
 * Created by Administrator on 15-12-28.
 */
public class QiuShiFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public QiuShiFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
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
        String ret="无标题";

        switch(position){
            case 0:
                ret="糗事";
                break;
            case 1:
                ret="糗友圈";
                break;
            case 2:
                ret="发现";
                break;
        }
        return ret;

    }
}
