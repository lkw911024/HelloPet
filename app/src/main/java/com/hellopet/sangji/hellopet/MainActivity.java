package com.hellopet.sangji.hellopet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import adapter.HelloPagerAdapter;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

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
        // 네비게이션 바
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 여기가 마이메뉴에서 각 메뉴 클릭하는 곳인듯
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id)
        {
            // MY MENU
            case R.id.nav_profile:
                Log.i("TEST","프로필 눌렀다");
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                break;
            case R.id.nav_pet:
                Log.i("TEST","마이펫 눌렀다");
                break;
            case R.id.nav_my_InterestPet:
                Log.i("TEST","나의 관심동물 눌렀다");
                break;
            case R.id.nav_registrationPet:
                Log.i("TEST","동물등록 조회 눌렀다");
                break;
            case R.id.nav_NotificationArea:
                Log.i("TEST","알림 지역 설정 눌렀다");
                break;

            // INFORMATION
            case R.id.nav_notice:
                Log.i("TEST","공지사항 눌렀다");
                break;
            case R.id.nav_question:
                Log.i("TEST","문의하기 눌렀다");
                break;
            case R.id.nav_often_question:
                Log.i("TEST","자주하는 질문 눌렀다");
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 이 부분은 마이 메뉴에서 다른 엑티비티로 넘어갔을때 마이메뉴가 꺼지는 것을 설정하는 부분인듯.
        //drawer.closeDrawer(GravityCompat.START);
        return true;
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