package com.hellopet.sangji.hellopet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import server.RequestHttpURLConnection;

public class ViewReportActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton viewReport_back_btn;
    private ImageButton viewReport_update_btn;
    private TextView viewReport_stateDisappear_tv;
    private TextView viewReport_stateProtect_tv;
    private TextView viewReport_stateWitness_tv;
    private TextView viewReport_petType_tv;
    private LinearLayout viewReport_nameTab_ll;
    private TextView viewReport_petName_tv;
    private TextView viewReport_petGender_tv;
    private TextView viewReport_petAge_tv;
    private TextView viewReport_petWeight_tv;
    private TextView viewReport_petHairColor_tv;
    private TextView viewReport_petNeutralization_tv;
    private TextView viewReport_petAttribute_tv;
    private TextView viewReport_petDetails_tv;
    private TextView viewReport_time_tv;
    private TextView viewReport_place_tv;
    private TextView viewReport_nickname_tv;
    private TextView viewReport_phone_tv;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        initId();
        initListener();


        ViewReportActivity.ViewReportTask viewReportTaskTask = new ViewReportActivity.ViewReportTask();
        viewReportTaskTask.execute();
    }
    public void initId()
    {
        viewReport_back_btn = (ImageButton)findViewById(R.id.viewReport_back_btn);
        viewReport_update_btn = (ImageButton)findViewById(R.id.viewReport_update_btn);
        viewReport_stateDisappear_tv = (TextView)findViewById(R.id.viewReport_stateDisappear_tv);
        viewReport_stateProtect_tv = (TextView)findViewById(R.id.viewReport_stateProtect_tv);
        viewReport_stateWitness_tv = (TextView)findViewById(R.id.viewReport_stateWitness_tv);
        viewReport_petType_tv = (TextView)findViewById(R.id.viewReport_petType_tv);
        viewReport_nameTab_ll = (LinearLayout)findViewById(R.id.viewReport_nameTab_ll);
        viewReport_petName_tv = (TextView)findViewById(R.id.viewReport_petName_tv);
        viewReport_petGender_tv = (TextView)findViewById(R.id.viewReport_petGender_tv);
        viewReport_petAge_tv = (TextView)findViewById(R.id.viewReport_petAge_tv);
        viewReport_petWeight_tv = (TextView)findViewById(R.id.viewReport_petWeight_tv);
        viewReport_petHairColor_tv = (TextView)findViewById(R.id.viewReport_petHairColor_tv);
        viewReport_petNeutralization_tv = (TextView)findViewById(R.id.viewReport_petNeutralization_tv);
        viewReport_petAttribute_tv = (TextView)findViewById(R.id.viewReport_petDetails_tv);
        viewReport_petDetails_tv = (TextView)findViewById(R.id.viewReport_petDetails_tv);
        viewReport_time_tv = (TextView)findViewById(R.id.viewReport_time_tv);
        viewReport_place_tv = (TextView)findViewById(R.id.viewReport_place_tv);
        viewReport_nickname_tv = (TextView)findViewById(R.id.viewReport_nickname_tv);
        viewReport_phone_tv = (TextView)findViewById(R.id.viewReport_phone_tv);

    }
    public void initListener()
    {
        viewReport_back_btn.setOnClickListener(this);
        viewReport_update_btn.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            // 창의 리스너
            case R.id.viewReport_back_btn:
                Log.i("viewReport_back_btn", "뒤로가기 버튼을 눌렀다.");
                finish();
                break;
            case R.id.viewReport_update_btn:
                Log.i("viewReport_update_btn", "수정하기 버튼을 눌렀다.");
                break;
        }
    }
    private class ViewReportTask extends AsyncTask<String, Integer, String>
    {

        Intent intent = getIntent();
        String reportId = intent.getExtras().getString("reportId");
        String reportType = intent.getExtras().getString("reportType");

        @Override
        protected String doInBackground(String... strings) {

            Log.i("게시글 정보","게시글 id : " + reportId + "\n게시글 type : " + reportType);


            RequestHttpURLConnection connect = new RequestHttpURLConnection("reportdetail.do");
            HttpURLConnection conn = connect.getConn();

            JSONObject sendData = new JSONObject();


            try{
                // json 객체에 보내줄 데이터 삽입
                sendData.put("reportId",reportId);
                sendData.put("reportType",reportType);


                OutputStream os = conn.getOutputStream();
                os.write(sendData.toString().getBytes("UTF-8"));
                os.flush();

                if(conn.getResponseCode() != HttpURLConnection.HTTP_OK)
                {
                    Log.i("데이터 전송 결과","실패니??????????");
                    return null;
                }

                InputStream is = conn.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));

                String line = bf.readLine();

                JSONObject receiveData = new JSONObject(line);

                if(reportType.equals("1"))
                {
                    viewReport_stateDisappear_tv.setVisibility(View.VISIBLE);
                    viewReport_stateProtect_tv.setVisibility(View.GONE);
                    viewReport_stateWitness_tv.setVisibility(View.GONE);
                    viewReport_nameTab_ll.setVisibility(View.VISIBLE);
                    viewReport_petType_tv.setText("["+receiveData.getString("petType")+"]" + " " + receiveData.getString("petRace"));
                    viewReport_petName_tv.setText(receiveData.getString("petName"));
                    viewReport_petAge_tv.setText(receiveData.getString("petAge"));
                    viewReport_petGender_tv.setText(receiveData.getString("petGender"));
                    viewReport_petHairColor_tv.setText(receiveData.getString("petHairColor"));
                    viewReport_petWeight_tv.setText(receiveData.getString("petWeight"));
                    viewReport_petNeutralization_tv.setText(receiveData.getString("petNeutralization"));
                    viewReport_petAttribute_tv.setText(receiveData.getString("petAttribute_tv"));
                    viewReport_petDetails_tv.setText(receiveData.getString("petDetails"));
                    viewReport_place_tv.setText(receiveData.getString("reportPlace") + " " +receiveData.getString("reportDetails"));
                    viewReport_time_tv.setText(receiveData.getString("reporttime"));
                    viewReport_nickname_tv.setText(receiveData.getString("memNickname"));
                    viewReport_phone_tv.setText(receiveData.getString("memPhone"));
                }
                else if(reportType.equals("2"))
                {
                    viewReport_stateDisappear_tv.setVisibility(View.GONE);
                    viewReport_stateProtect_tv.setVisibility(View.VISIBLE);
                    viewReport_stateWitness_tv.setVisibility(View.GONE);
                    viewReport_nameTab_ll.setVisibility(View.GONE);
                    viewReport_petType_tv.setText("["+receiveData.getString("petType")+"]" + " " + receiveData.getString("petRace"));
                    viewReport_petAge_tv.setText(receiveData.getString("petAge"));
                    viewReport_petGender_tv.setText(receiveData.getString("petGender"));
                    viewReport_petHairColor_tv.setText(receiveData.getString("petHairColor"));
                    viewReport_petWeight_tv.setText(receiveData.getString("petWeight"));
                    viewReport_petNeutralization_tv.setText(receiveData.getString("petNeutralization"));
                    viewReport_petAttribute_tv.setText(receiveData.getString("petAttribute_tv"));
                    viewReport_petDetails_tv.setText(receiveData.getString("petDetails"));
                    viewReport_place_tv.setText(receiveData.getString("reportPlace") + " " +receiveData.getString("reportDetails"));
                    viewReport_time_tv.setText(receiveData.getString("reporttime"));
                    viewReport_nickname_tv.setText(receiveData.getString("memNickname"));
                    viewReport_phone_tv.setText(receiveData.getString("memPhone"));
                }
                else  if(reportType.equals("3"))
                {
                    viewReport_stateDisappear_tv.setVisibility(View.GONE);
                    viewReport_stateProtect_tv.setVisibility(View.GONE);
                    viewReport_stateWitness_tv.setVisibility(View.VISIBLE);
                    viewReport_nameTab_ll.setVisibility(View.GONE);
                    viewReport_petType_tv.setText("["+receiveData.getString("petType")+"]" + " " + receiveData.getString("petRace"));
                    viewReport_petAge_tv.setText(receiveData.getString("petAge"));
                    viewReport_petGender_tv.setText(receiveData.getString("petGender"));
                    viewReport_petHairColor_tv.setText(receiveData.getString("petHairColor"));
                    viewReport_petWeight_tv.setText(receiveData.getString("petWeight"));
                    viewReport_petNeutralization_tv.setText(receiveData.getString("petNeutralization"));
                    viewReport_petAttribute_tv.setText(receiveData.getString("petAttribute_tv"));
                    viewReport_petDetails_tv.setText(receiveData.getString("petDetails"));
                    viewReport_place_tv.setText(receiveData.getString("reportPlace") + " " +receiveData.getString("reportDetails"));
                    viewReport_time_tv.setText(receiveData.getString("reporttime"));
                    viewReport_nickname_tv.setText(receiveData.getString("memNickname"));
                    viewReport_phone_tv.setText(receiveData.getString("memPhone"));
                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            //백그라운드 스레드로 동작해야 하는 작업을 실행한다.
            // execute메서드로 전달한 data tye이 params 인수로 전달되는데 여러개의 인수를 전달할 수 있으므로 배열 타입으로 되어 있습니다.
            // 그래서 하나의 인수만 필요하다면 params[0]만 사용하면 됩니다.
            // 작업 중에 publishProgress 메소드를 호출하여 작업 경과를 UI스레드로 display할 수 있으며
            // 작업결과는 Result타입으로 리턴됩니다.

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground에서드의 작업 결과를 UI반영하는 역할을 담당하는 메소드입니다.


            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            //tv_outPut.setText(s);
        }

    }
}
