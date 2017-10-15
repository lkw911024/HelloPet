package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hellopet.sangji.hellopet.R;

import java.util.ArrayList;

import VO.SimpleReportVO;
import adapter.ReportRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Protect extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;

    public Protect() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_protect, container, false);


        // 실종/보호의 리사이클 뷰 연결
        recyclerView = (RecyclerView) v.findViewById(R.id.protect_recycler_view);
        swipe = (SwipeRefreshLayout) v.findViewById(R.id.protect_swipe);
        swipe.setOnRefreshListener(this);

        initData();

        return v;
    }

    public static Protect newInstance(){

        Bundle args = new Bundle();

        Protect fragment = new Protect();
        fragment.setArguments(args);

        return fragment;
    }

    private void initData(){

        //출력할 데이터를 초기화 하는 부분
        ArrayList<SimpleReportVO> reportList = requestSimpleProtectList();

        ReportRecyclerAdapter reportAdapter = new ReportRecyclerAdapter(reportList);


        // 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
        // setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
        // 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        reportAdapter.notifyDataSetChanged();
        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행한다
    }

    private ArrayList<SimpleReportVO> requestSimpleProtectList(){
        ArrayList<SimpleReportVO> reportList = new ArrayList<SimpleReportVO>();

        //테스트 데이터 입력
        //추후에 이 부분에 서버에서 데이터를 받아오는 부분을 입력해야한다
        reportList.add(new SimpleReportVO("7", "2", "경기도 광주시 송정동ㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "2017-09-12", "이경원 닮음", "강아지", "스피치", "토리", "수컷"));
        reportList.add(new SimpleReportVO("8", "3", "경기도 광주시 초월읍 학동리", "2017-09-13", "박세훈 닮음ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "강아지", "포메라니안", "삼순이", "암컷"));
        reportList.add(new SimpleReportVO("9", "2", "강원도 원주시 상지대학교", "2017-10-12", "이천우 닮음", "고양이", "고양이", "솜이", "암컷"));
        reportList.add(new SimpleReportVO("10", "3", "원주 시외버스 터미널", "2017-10-15", "김형근 닮음", "고양이", "고양이", "토리", "수컷"));
        reportList.add(new SimpleReportVO("11", "2", "서울 중랑구 면목동", "2017-10-24", "방지현 닮음", "강아지", "불독", "방지", "수컷"));
        reportList.add(new SimpleReportVO("12", "3", "서울 고속버스터미널", "2017-11-12", "민수 닮음", "고양이", "페르시안", "냐옹이", "수컷"));

        return reportList;
    }

    @Override
    public void onRefresh() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText (getContext(), "refresh success", Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);
            }
        },500);
    }
    // Refresh가 시작되면 SnakBar 를 표시해주고, 0.5 초후 Refresh가 완료되도록 하였습니다.
    // setRefreshing(false) 메서드가 호출 되면 새로고침이 완료됩니다. 특정 작업이 완료되는 시점에 사용해주시면 됩니다.
}
