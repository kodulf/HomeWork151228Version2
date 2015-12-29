package com.kodulf.homework151228;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kodulf.homework151228.adapter.QiuShiFragmentPagerAdapter;
import com.kodulf.homework151228.fragments.FaXianFragment;
import com.kodulf.homework151228.fragments.QiuShiFragment;
import com.kodulf.homework151228.fragments.QiuYouCircleFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, View.OnClickListener, SlidingPaneLayout.PanelSlideListener, NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private TextView menu;
//    private SlidingPaneLayout sliding;
    private MySlidingPaneLayout sliding;
    private ViewPager viewPager;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    //    private MyNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        sliding = (SlidingPaneLayout) findViewById(R.id.home_page_drawer);
        sliding = (MySlidingPaneLayout) findViewById(R.id.home_page_drawer);
        sliding.setPanelSlideListener(this);
        navigationView = (NavigationView)findViewById(R.id.home_page_navigationView);
        viewPager = (ViewPager) findViewById(R.id.home_page_viewpager);

        List<Fragment> fragments = new ArrayList<Fragment>();
        QiuShiFragment qiuShiFragment = new QiuShiFragment();
        QiuYouCircleFragment qiuYouCircleFragment= new QiuYouCircleFragment();
        FaXianFragment faXianFragment = new FaXianFragment();

        fragments.add(qiuShiFragment);
        fragments.add(qiuYouCircleFragment);
        fragments.add(faXianFragment);
        QiuShiFragmentPagerAdapter adapter = new QiuShiFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
            sliding.closePane();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {

    }

    @Override
    public void onPanelClosed(View panel) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.title_qiushi:
                viewPager.setCurrentItem(0);
                sliding.closePane();
                break;
            case R.id.title_qiuyoucircle:
                viewPager.setCurrentItem(1);
                sliding.closePane();
                break;
            case R.id.title_faxian:
                viewPager.setCurrentItem(2);
                sliding.closePane();
                break;
        }
        return false;
    }
}
