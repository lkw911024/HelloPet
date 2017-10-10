package com.hellopet.sangji.hellopet;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelloPagerAdapter mAdapter = new HelloPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mViewPager.setAdapter(mAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.main_tab);
        mTab.setupWithViewPager(mViewPager);
    }
}
