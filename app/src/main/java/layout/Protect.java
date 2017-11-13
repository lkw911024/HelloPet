package layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hellopet.sangji.hellopet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import VO.SimpleReportVO;
import adapter.ReportRecyclerAdapter;
import server.RequestHttpURLConnection;


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

        //initData();

        Protect.ProtectTask ProtectTask = new Protect.ProtectTask();
        ProtectTask.execute();

        return v;
    }

    public static Protect newInstance(){

        Bundle args = new Bundle();

        Protect fragment = new Protect();
        fragment.setArguments(args);

        return fragment;
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

    private class ProtectTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            RequestHttpURLConnection connect = new RequestHttpURLConnection("Protect.do");
            HttpURLConnection conn = connect.getConn();

            try {

                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.i("데이터 전송 결과", "실패니??????????");
                    return null;
                }

                InputStream is = conn.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));

                String line = bf.readLine();


                // String 으로 들어온 값 JSONObject 로 1차 파싱
                JSONObject wrapData = new JSONObject(line);

                // JSONObject 의 키 "list" 의 값들을 JSONArray 형태로 변환
                JSONArray reciveArrayData = new JSONArray(wrapData.getString("list"));

                //출력할 데이터를 초기화 하는 부분
                ArrayList<SimpleReportVO> reportList = new ArrayList<>();
                reportList.clear();

                for (int i = 0; i < reciveArrayData.length(); i++) {
                    // Array 에서 하나의 JSONObject 를 추출
                    JSONObject reciveData = reciveArrayData.getJSONObject(i);
                    // 추출한 Object 에서 필요한 데이터를 표시할 방법을 정해서 화면에 표시
                    reportList.add(new SimpleReportVO(reciveData.getString("reportId") + i, reciveData.getString("reportType") + i, reciveData.getString("reportPlace") + i, reciveData.getString("reportTime") + i,
                            reciveData.getString("reportDetails") + i, reciveData.getString("reportPetType") + i, reciveData.getString("reportPetRace") + i, reciveData.getString("reportPetName") + i,
                            reciveData.getString("reportPetGender") + i));
                }

                ReportRecyclerAdapter reportAdapter = new ReportRecyclerAdapter(reportList);


                // 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
                // setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
                // 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
                recyclerView.setHasFixedSize(true);

                recyclerView.setAdapter(reportAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                reportAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //백그라운드 스레드로 동작해야 하는 작업을 실행한다.
            // execute메서드로 전달한 data tye이 params 인수로 전달되는데 여러개의 인수를 전달할 수 있으므로 배열 타입으로 되어 있습니다.
            // 그래서 하나의 인수만 필요하다면 params[0]만 사용하면 됩니다.
            // 작업 중에 publishProgress 메소드를 호출하여 작업 경과를 UI스레드로 display할 수 있으며
            // 작업결과는 Result타입으로 리턴됩니다.

            return null;
        }
        // Refresh가 시작되면 SnakBar 를 표시해주고, 0.5 초후 Refresh가 완료되도록 하였습니다.
        // setRefreshing(false) 메서드가 호출 되면 새로고침이 완료됩니다. 특정 작업이 완료되는 시점에 사용해주시면 됩니다.


    }
     /*
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
    */
}
