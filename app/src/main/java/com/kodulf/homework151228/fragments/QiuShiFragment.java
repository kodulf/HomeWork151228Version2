package com.kodulf.homework151228.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;

import com.kodulf.homework151228.ItemAdapter;
import com.kodulf.homework151228.R;
import com.kodulf.homework151228.adapter.QiuShiLittleFragmentPagerAdapter;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-12-28.
 */
public class QiuShiFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public QiuShiFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.qiushi_fragment,container,false);
        viewPager = (ViewPager) ret.findViewById(R.id.qiushi_viewpager);
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new QiuShiZhuanXiangFragment());
        fragments.add(new QiuShiZhuanXiangFragment());
        fragments.add(new QiuShiZhuanXiangFragment());

        QiuShiLittleFragmentPagerAdapter adapter = new QiuShiLittleFragmentPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) ret.findViewById(R.id.qiushi_tablayout);
        tabLayout.setupWithViewPager(viewPager);
//        return super.onCreateView(inflater, container, savedInstanceState);
        return ret;
    }

}
