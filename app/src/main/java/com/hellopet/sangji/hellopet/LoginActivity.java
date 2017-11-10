package com.hellopet.sangji.hellopet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
}
