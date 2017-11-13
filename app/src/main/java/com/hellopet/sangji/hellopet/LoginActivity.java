package com.hellopet.sangji.hellopet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

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

                Log.i("로그인 결과",receiveData.toString());

                if(receiveData != null)
                {
                    SharedPreferences pre = getSharedPreferences("memberInfo",0);
                    SharedPreferences.Editor editor = pre.edit();

                    editor.putString("memberId",(String)receiveData.get("id"));
                    editor.putString("memberEmail",(String)receiveData.get("email"));
                    editor.putString("memberPWd",(String)receiveData.get("pwd"));
                    editor.putString("memberName",(String)receiveData.get("name"));
                    editor.putString("memberNickname",(String)receiveData.get("nickname"));
                    editor.putString("memberPhone",(String)receiveData.get("phone"));

                    editor.commit();

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else
                {
                    // 로그인 실패시 코드
                    Log.i("로그인 실패",receiveData.toString());
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


/*
AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>

doInBackground()의 변수 종류 : 우리가 정의한 AsyncTask를 execute할 때 전해줄 값의 종류
onProgressUpdate()에서 사용할 변수 종류 : 진행상황을 업데이트할 때 전달할 값의 종류
onPostExecute()에서 사용할 변수종류 : AsyncTask가 끝난 뒤 결과값의 종류

 */


/*
액티비티 간에 데이터 주고 받기
https://m.blog.naver.com/PostView.nhn?blogId=eominsuk55&logNo=220228053631&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
 */