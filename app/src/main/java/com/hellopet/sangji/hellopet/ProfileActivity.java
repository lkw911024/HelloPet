package com.hellopet.sangji.hellopet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import VO.MemberVO;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton profile_back_btn;
    private Button profile_save_btn;
    private EditText profile_nickName_et;
    private EditText profile_email_et;
    private EditText profile_name_et;
    private EditText profile_phone_et;
    private MemberVO memberVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_back_btn = (ImageButton) findViewById(R.id.profile_back_btn);
        profile_save_btn = (Button) findViewById(R.id.profile_save_btn);
        profile_nickName_et = (EditText) findViewById(R.id.profile_nickName_btn);
        profile_email_et = (EditText) findViewById(R.id.profile_email_et);
        profile_name_et = (EditText) findViewById(R.id.profile_name_et);
        profile_phone_et = (EditText) findViewById(R.id.profile_phone_et);
        profile_back_btn.setOnClickListener(this);
        profile_save_btn.setOnClickListener(this);

        initData();

    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            // 창의 리스너
            case R.id.profile_back_btn:
                Log.i("close_btn", "닫기 버튼이 눌렸다.");
                finish();
                break;
            case R.id.profile_save_btn:
                Log.i("save_btn","저장 버튼이 눌렸다.");
                memberVO = updateMemberInfo();
                Log.i("저장된 데이터", "\n이메일 : " + memberVO.getMemEmail().toString() + "\n닉네임 :  "+ memberVO.getMemNickName().toString() +
                "\n이름 : " + memberVO.getMemName().toString() + "\n번호 : " + memberVO.getMemPhone().toString());
                // 이후 db에 접근해 저장할 메소드 구현하면 될듯?
                finish();
                break;
        }
    }
    public void initData()
    {
        MemberVO memberInfo = requestMemberInfo();

        this.profile_email_et.setText(memberInfo.getMemEmail());
        this.profile_nickName_et.setText(memberInfo.getMemNickName());
        this.profile_name_et.setText(memberInfo.getMemName());
        this.profile_phone_et.setText(memberInfo.getMemPhone());

    }
    private MemberVO requestMemberInfo()
    {
        MemberVO memberInfo;

        memberInfo = new MemberVO("zxca1520@gmail.com","꽃후니","박세훈","010-5516-1153");

        return memberInfo;
    }
    public  MemberVO updateMemberInfo()
    {
        MemberVO updateMemberData;
        updateMemberData = new MemberVO();
        updateMemberData.setMemEmail(profile_email_et.getText().toString());
        updateMemberData.setMemNickName(profile_nickName_et.getText().toString());
        updateMemberData.setMemName(profile_name_et.getText().toString());
        updateMemberData.setMemPhone(profile_phone_et.getText().toString());
        return updateMemberData;
    }
}
