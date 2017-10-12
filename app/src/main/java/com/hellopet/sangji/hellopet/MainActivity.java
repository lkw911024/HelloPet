package com.hellopet.sangji.hellopet;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import adapter.HelloPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메인 뷰 페이저, 페이저 어탭터 생성 후 연결
        HelloPagerAdapter mainAdapter = new HelloPagerAdapter(getSupportFragmentManager());
        ViewPager mainViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mainViewPager.setAdapter(mainAdapter);

        TabLayout mainTab = (TabLayout) findViewById(R.id.main_tab);
        mainTab.setupWithViewPager(mainViewPager);


    }
}
