package com.hellopet.sangji.hellopet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;

import VO.MemberVO;
import server.RequestHttpURLConnection;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login_login_btn;
    private TextView login_passwordSearch_tv;
    private TextView login_signup_tv;
    private EditText login_email_et;
    private EditText login_password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_login_btn = (Button)findViewById(R.id.login_login_btn);
        login_passwordSearch_tv = (TextView)findViewById(R.id.login_passwordSearch_tv);
        login_signup_tv = (TextView)findViewById(R.id.login_signup_tv);
        login_email_et = (EditText)findViewById(R.id.login_email_et);
        login_password_et = (EditText)findViewById(R.id.login_password_et);

        login_login_btn.setOnClickListener(this);
        login_passwordSearch_tv.setOnClickListener(this);
        login_signup_tv.setOnClickListener(this);

    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            // 창의 리스너
            case R.id.login_login_btn:
                Log.i("login_btn", "로그인 버튼을 눌렀다.");
                Log.i("로그인 정보", "\n이메일(id) : " + login_email_et.getText().toString() + "\n비밀번호 : " + login_password_et.getText().toString());
                LoginTask loginTask = new LoginTask();
                loginTask.execute();
                break;
            case R.id.login_passwordSearch_tv:
                Log.i("save_btn","비밀번호 찾기 버튼을 눌렀다.");

                break;
            case R.id.login_signup_tv:
                Log.i("login_btn","회원가입 버튼을 눌렀다.");
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                break;
        }
    }

    private class LoginTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            RequestHttpURLConnection connect = new RequestHttpURLConnection("login.do");
            HttpURLConnection conn = connect.getConn();

            JSONObject sendData = new JSONObject();


            try{
                // json 객체에 보내줄 데이터 삽입
                sendData.put("userEmail",login_email_et.getText());
                sendData.put("userPwd",login_password_et.getText());

                Log.i("로그인 정보",sendData.toString());

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

                String id = (String) receiveData.get("id");
                //데이터 받으면 공용데이터에 등록하자
                Log.i("로그인 결과",receiveData.toString());

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            //tv_outPut.setText(s);
        }
    }
}
