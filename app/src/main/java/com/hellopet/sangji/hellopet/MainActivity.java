package com.hellopet.sangji.hellopet;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

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


/*
    ** 어플리케이션의 전체적인 색 고민** 어플리케이션의 전체적인 색 고민

    찾아야할 이미지
    - 글쓰기 버튼
    - 검색 버튼
    - 내 위치 동기화 버튼
    - 좋아요 버튼 / 좋아요 누른 버튼
    - 공유 버튼
    - 잃어버린 시간
    - 잃어버린 장소
    - 강아지 고양이
    - 성별
    - 사진추가 버튼
    - 로딩 화면 이미지

 */

/*
    실종 보호 목격 화면 다 만들고
    상세 데이터 화면 만들고
    글쓰기 화면 만들고

 */

