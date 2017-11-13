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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import server.RequestHttpURLConnection;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton signup_back_btn;
    private EditText signup_email_et;
    private EditText signup_password_et;
    private EditText signup_passwordCheck_et;
    private EditText signup_nickName_et;
    private EditText signup_name_et;
    private EditText signup_phone_et;
    private TextView signup_tos_tv;
    private TextView signup_privacyInfo_tv;
    private Button signup_signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_back_btn = (ImageButton)findViewById(R.id.signup_back_btn);
        signup_email_et = (EditText)findViewById(R.id.signup_email_et);
        signup_password_et = (EditText)findViewById(R.id.signup_password_et);
        signup_passwordCheck_et = (EditText)findViewById(R.id.signup_passwordCheck_et);
        signup_nickName_et = (EditText)findViewById(R.id.signup_nickName_et);
        signup_name_et = (EditText)findViewById(R.id.signup_name_et);
        signup_phone_et = (EditText)findViewById(R.id.signup_phone_et);
        signup_tos_tv = (TextView)findViewById(R.id.signup_tos_tv);
        signup_privacyInfo_tv = (TextView)findViewById(R.id.signup_privacyInfo_tv);
        signup_signup_btn = (Button)findViewById(R.id.signup_signup_btn);

        signup_back_btn.setOnClickListener(this);
        signup_tos_tv.setOnClickListener(this);
        signup_privacyInfo_tv.setOnClickListener(this);
        signup_signup_btn.setOnClickListener(this);

    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            // 창의 리스너
            case R.id.signup_back_btn:
                Log.i("signup_back_btn", "뒤로가기 버튼을 눌렀다.");
                finish();
                break;
            case R.id.signup_tos_tv:
                Log.i("signup_tos_tv","이용약관 버튼을 눌렀다.");
                break;
            case R.id.signup_privacyInfo_tv:
                Log.i("signup_privacyInfo_tv","개인정보 취급방침 버튼을 눌렀다.");
                break;
            case R.id.signup_signup_btn:
                Log.i("signup_signup_btn","회원가입 버튼을 눌렀다.");
                // 비밀번호랑 비밀번호 확인이랑 맞는지 안맞는지 검사하는것 아직 미구현
                if(checkPassword() == true)
                {
                    SignUpActivity.SignUpTask signUpTask = new SignUpActivity.SignUpTask();
                    signUpTask.execute();
                    Toast.makeText (getApplicationContext(), "회원가입 완료.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText (getApplicationContext(), "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }

                //Log.i("저장된 데이터","\n이메일 : " + memberVO.getMemEmail() + "\n비밀번호 : " + memberVO.getMemPassword() +
                //"\n닉네임 : " + memberVO.getMemNickName() + "\n이름 : " + memberVO.getMemName() + "\n번호 : " + memberVO.getMemPhone());
                // 이후 db에 접근해 저장할 메소드 구현하면 될듯?
                break;
        }
    }

    public boolean checkPassword()
    {
        if(signup_password_et.getText().toString().equals(signup_passwordCheck_et.getText().toString()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private class SignUpTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            RequestHttpURLConnection connect = new RequestHttpURLConnection("join.do");
            HttpURLConnection conn = connect.getConn();

            JSONObject sendData = new JSONObject();


            try{
                // json 객체에 보내줄 데이터 삽입
                sendData.put("userEmail",signup_email_et.getText());
                sendData.put("userPwd",signup_password_et.getText());
                sendData.put("userNickName",signup_nickName_et.getText());
                sendData.put("userName",signup_name_et.getText());
                sendData.put("userPhone",signup_phone_et.getText());

                Log.i("회원가입 정보",sendData.toString());

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

                Log.i("회원가입 결과",receiveData.toString());

                if(receiveData != null)
                {
                    SharedPreferences pre = getSharedPreferences("memberInfo",0);
                    SharedPreferences.Editor editor = pre.edit();

                    editor.putString("memberRes",(String)receiveData.get("res"));
                    editor.putString("memberId",(String)receiveData.get("id"));
                    editor.putString("memberEmail",(String)receiveData.get("email"));
                    editor.putString("memberPWd",(String)receiveData.get("pwd"));
                    editor.putString("memberName",(String)receiveData.get("name"));
                    editor.putString("memberNickname",(String)receiveData.get("nickname"));
                    editor.putString("memberPhone",(String)receiveData.get("phone"));

                    editor.commit();

                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                }
                else
                {
                    // 로그인 실패시 코드
                    Log.i("회원가입 실패",receiveData.toString());
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
