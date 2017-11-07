package com.hellopet.sangji.hellopet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import VO.MemberVO;

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
    private MemberVO memberVO;

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
                memberVO = signUpMemberInfo();
                Log.i("저장된 데이터","\n이메일 : " + memberVO.getMemEmail() + "\n비밀번호 : " + memberVO.getMemPassword() +
                "\n닉네임 : " + memberVO.getMemNickName() + "\n이름 : " + memberVO.getMemName() + "\n번호 : " + memberVO.getMemPhone());
                // 이후 db에 접근해 저장할 메소드 구현하면 될듯?
                break;
        }
    }
    public MemberVO signUpMemberInfo()
    {
        MemberVO memberInfo;

        memberInfo = new MemberVO();

        memberInfo.setMemEmail(this.signup_email_et.getText().toString());
        memberInfo.setMemPassword(this.signup_password_et.getText().toString());
        memberInfo.setMemNickName(this.signup_nickName_et.getText().toString());
        memberInfo.setMemName(this.signup_name_et.getText().toString());
        memberInfo.setMemPhone(this.signup_phone_et.getText().toString());

        return memberInfo;
    }
}
