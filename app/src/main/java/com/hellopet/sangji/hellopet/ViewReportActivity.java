package com.hellopet.sangji.hellopet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class ViewReportActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton viewReport_back_btn;
    private ImageButton viewReport_update_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        initId();
        initListener();
    }

    public void initId()
    {
        viewReport_back_btn = (ImageButton)findViewById(R.id.viewReport_back_btn);
        viewReport_update_btn = (ImageButton)findViewById(R.id.viewReport_update_btn);
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
}
