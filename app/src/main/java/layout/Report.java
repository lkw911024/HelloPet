package layout;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hellopet.sangji.hellopet.R;

import adapter.HelloPagerAdapter;
import adapter.ReportPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Report extends Fragment {

    public Button writeBtn;
    public Button searchBtn;
    public Button replaceBtn;

    public Report() {
        // Required empty public constructor55
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_report, container, false);
        /*
         findViewById() 함수는 android.view.View 또는 android.app.Activity 클래스에서 제공하는 함수.
         그래서 Fragment에서는 바로 호출할 수 없고, View 또는 Activity의 참조를 통해 사용해야 한다.
        */

        writeBtn = (Button) v.findViewById(R.id.report_write_btn);
        searchBtn = (Button) v.findViewById(R.id.report_search_btn);
        replaceBtn = (Button) v.findViewById(R.id.report_replace_btn);

        //각 버튼 리스너 연결
        writeBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText (getContext(), "글쓰기 버튼", Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText (getContext(), "검색 버튼", Toast.LENGTH_SHORT).show();
            }
        });

        replaceBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText (getContext(), "내 위치 버튼", Toast.LENGTH_SHORT).show();
            }
        });

        // 실종/보호의 뷰 페이저, 페이저 어탭터 생성 후 연결
        ReportPagerAdapter reportAdapter = new ReportPagerAdapter(((FragmentActivity) v.getContext()).getSupportFragmentManager());
        ViewPager reportViewPager = (ViewPager) v.findViewById(R.id.report_view_pager);
        reportViewPager.setAdapter(reportAdapter);

        TabLayout mainTab = (TabLayout) v.findViewById(R.id.report_tab);
        mainTab.setupWithViewPager(reportViewPager);

        //Log.i("TEST","여기 실행되니?");

        return v;
    }

    public static Report newInstance(){

        Bundle args = new Bundle();

        Report fragment = new Report();
        fragment.setArguments(args);

        return fragment;
    }

}
