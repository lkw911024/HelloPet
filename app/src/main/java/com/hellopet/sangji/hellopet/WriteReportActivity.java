package com.hellopet.sangji.hellopet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import VO.DisappearVO;
import VO.ProtectVO;
import VO.WitnessVO;

public class WriteReportActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;

    private int imageSelectNum;

    private Uri firstImageUri;
    private Uri secondImageUri;
    private Uri thirdImageUri;
    private String absoultePath;

    private ImageButton writeReport_back_btn;
    private Button writeReport_save_btn;
    private LinearLayout writeReport_window_ll;
    private Spinner writeReport_state_sp;
    private Spinner writeReport_time_sp;
    private Spinner writeReport_location_sp;
    private Spinner writeReport_petType_sp;
    private Spinner writeReport_petGender_sp;
    private Spinner writeReport_petNeutralization_sp;
    private EditText writeReport_place_et;
    private EditText writeReport_petRace_et;
    private EditText writeReport_petName_et;
    private EditText writeReport_petHairColor_et;
    private EditText writeReport_petAge_et;
    private EditText writeReport_petAttribute_et;
    private EditText writeReport_petDetails_et;
    private ImageView writeReport_imageSelect1_iv;
    private ImageView writeReport_imageSelect2_iv;
    private ImageView writeReport_imageSelect3_iv;

    private DisappearVO disappearVO;
    private ProtectVO protectVO;
    private WitnessVO witnessVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_report);

        initId();
        initListener();
        initSpinner();

    }
    public void initId()
    {
        // 아이디 값을 가져오기
        writeReport_back_btn = (ImageButton)findViewById(R.id.writeReport_back_btn);
        writeReport_save_btn = (Button)findViewById(R.id.writeReport_save_btn);
        writeReport_window_ll = (LinearLayout)findViewById(R.id.writeReport_window_ll);
        writeReport_state_sp = (Spinner)findViewById(R.id.writeReport_state_sp);
        writeReport_time_sp = (Spinner)findViewById(R.id.writeReport_time_sp);
        writeReport_location_sp = (Spinner)findViewById(R.id.writeReport_location_sp);
        writeReport_petType_sp = (Spinner)findViewById(R.id.writeReport_petType_sp);
        writeReport_petGender_sp = (Spinner)findViewById(R.id.writeReport_petGender_sp);
        writeReport_petNeutralization_sp = (Spinner)findViewById(R.id.writeReport_petNeutralization_sp);
        writeReport_place_et = (EditText)findViewById(R.id.writeReport_place_et);
        writeReport_petRace_et = (EditText)findViewById(R.id.writeReport_petRace_et);
        writeReport_petName_et = (EditText)findViewById(R.id.writeReport_petName_et);
        writeReport_petHairColor_et = (EditText)findViewById(R.id.writeReport_petHairColor_et);
        writeReport_petAge_et = (EditText)findViewById(R.id.writeReport_petAge_et);
        writeReport_petAttribute_et = (EditText)findViewById(R.id.writeReport_petAttribute_et);
        writeReport_petDetails_et = (EditText)findViewById(R.id.writeReport_petDetails_et);
        writeReport_imageSelect1_iv = (ImageView)findViewById(R.id.writeReport_imageSelect1_iv);
        writeReport_imageSelect2_iv = (ImageView)findViewById(R.id.writeReport_imageSelect2_iv);
        writeReport_imageSelect3_iv = (ImageView)findViewById(R.id.writeReport_imageSelect3_iv);

    }
    public void initListener()
    {
        // 리스너
        writeReport_back_btn.setOnClickListener(this);
        writeReport_save_btn.setOnClickListener(this);
        writeReport_window_ll.setOnClickListener(this);
        writeReport_imageSelect1_iv.setOnClickListener(this);
        writeReport_imageSelect2_iv.setOnClickListener(this);
        writeReport_imageSelect3_iv.setOnClickListener(this);
    }
    public void initSpinner()
    {
        // 상태 스피너
        ArrayAdapter stateAdapter = ArrayAdapter.createFromResource(this, R.array.state, R.layout.spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writeReport_state_sp.setAdapter(stateAdapter);

        // 시간 스피너
        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(this, R.array.time, R.layout.spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writeReport_time_sp.setAdapter(timeAdapter);

        // 지역 스피너
        ArrayAdapter locationAdapter = ArrayAdapter.createFromResource(this, R.array.location, R.layout.spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writeReport_location_sp.setAdapter(locationAdapter);

        // 품종 스피너
        ArrayAdapter petTypeAdapter = ArrayAdapter.createFromResource(this, R.array.petType, R.layout.spinner_item);
        petTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writeReport_petType_sp.setAdapter(petTypeAdapter);

        // 성별 스피너
        ArrayAdapter petGenderAdapter = ArrayAdapter.createFromResource(this, R.array.petGender, R.layout.spinner_item);
        petGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writeReport_petGender_sp.setAdapter(petGenderAdapter);

        // 중성화여부 스피너
        ArrayAdapter neutralizationAdapter = ArrayAdapter.createFromResource(this,R.array.petNeutralization,R.layout.spinner_item);
        neutralizationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writeReport_petNeutralization_sp.setAdapter(neutralizationAdapter);
    }

    public void onClick(View view)
    {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(writeReport_window_ll.getWindowToken(),0);

        switch (view.getId()) {
            // 창의 리스너
            case R.id.writeReport_back_btn:
                Log.i("writeReport_back_btn", "뒤로가기 버튼을 눌렀다.");
                finish();
                break;
            case R.id.writeReport_imageSelect1_iv:
                Log.i("imageSelect1","첫번째 사진 버틀을 눌렀다.");
                imageSelectNum = 1;

                DialogInterface.OnClickListener cameraListener1 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };
                DialogInterface.OnClickListener albumListener1 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };
                DialogInterface.OnClickListener cancelListener1 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("동물사진 선택")
                        .setPositiveButton("사진촬영", cameraListener1)
                        .setNeutralButton("앨범선택", albumListener1)
                        .setNegativeButton("취소", cancelListener1)
                        .show();
                break;
            case R.id.writeReport_imageSelect2_iv:
                imageSelectNum = 2;

                Log.i("imageSelect2","두번째 사진 버튼을 눌렀다");
                DialogInterface.OnClickListener cameraListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };
                DialogInterface.OnClickListener albumListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };
                DialogInterface.OnClickListener cancelListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("동물사진 선택")
                        .setPositiveButton("사진촬영", cameraListener2)
                        .setNeutralButton("앨범선택", albumListener2)
                        .setNegativeButton("취소", cancelListener2)
                        .show();
                break;
            case R.id.writeReport_imageSelect3_iv:

                imageSelectNum = 3;
                Log.i("imageSelect3","세번째 사진 버튼을 눌렀다.");
                DialogInterface.OnClickListener cameraListener3 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };
                DialogInterface.OnClickListener albumListener3 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };
                DialogInterface.OnClickListener cancelListener3 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("동물사진 선택")
                        .setPositiveButton("사진촬영", cameraListener3)
                        .setNeutralButton("앨범선택", albumListener3)
                        .setNegativeButton("취소", cancelListener3)
                        .show();
                break;
            case R.id.writeReport_save_btn:
                Log.i("writeReport_save_btn","저장 버튼을 눌렀다.");
                ReportTypeSelect();
                /*
                // SharedPreference 환경 변수 사용

                SharedPreferences prefs = getSharedPreferences("login", 0);

                // prefs.getString() return값이 null이라면 2번째 함수를 대입한다.

                String login = prefs.getString("USER_LOGIN", "LOGOUT");

                String facebook_login = prefs.getString("FACEBOOK_LOGIN", "LOGOUT");

                String user_id = prefs.getString("USER_ID","");

                String user_name = prefs.getString("USER_NAME", "");

                String user_password = prefs.getString("USER_PASSWORD", "");

                String user_phone = prefs.getString("USER_PHONE", "");

                String user_email = prefs.getString("USER_EMAIL", "");

                dbmanger.select(user_id,user_name,user_password, user_phone, user_email);

                dbmanger.selectPhoto(user_name, mImageCaptureUri, absoultePath);


                Intent mainIntent = new Intent(SignUpPhotoActivity.this, LoginActivity.class);

                SignUpPhotoActivity.this.startActivity(mainIntent);

                SignUpPhotoActivity.this.finish();

                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                */
                break;
        }
    }
    public void ReportTypeSelect()
    {
        String reportType = writeReport_state_sp.getSelectedItem().toString();

        if(reportType.equals("실종"))
        {
            disappearReport();
        }
        else if(reportType.equals("보호"))
        {
            protectReport();
        }
        else if(reportType.equals("목격"))
        {
            witnessReport();
        }
    }
    public void disappearReport()
    {
        disappearVO = new DisappearVO();

        disappearVO.setDisappearId("걍");
        disappearVO.setDisappearType("0");
        disappearVO.setDisappearPlace(writeReport_location_sp.getSelectedItem().toString() + " " + writeReport_place_et.getText().toString());
        disappearVO.setDisappearTime(writeReport_time_sp.getSelectedItem().toString());
        disappearVO.setDisappearWrittenDate(getTime());
        disappearVO.setPetId("걍");
        disappearVO.setPetType(writeReport_petType_sp.getSelectedItem().toString());
        disappearVO.setPetRace(writeReport_petRace_et.getText().toString());
        disappearVO.setPetName(writeReport_petName_et.getText().toString());
        disappearVO.setPetAge(writeReport_petAge_et.getText().toString());
        disappearVO.setPetGender(writeReport_petGender_sp.getSelectedItem().toString());
        disappearVO.setPetHairColor(writeReport_petHairColor_et.getText().toString());
        disappearVO.setPetWeight(writeReport_petAge_et.getText().toString());
        disappearVO.setPetNeutralization(writeReport_petNeutralization_sp.getSelectedItem().toString());
        disappearVO.setPetAttribute(writeReport_petAttribute_et.getText().toString());
        disappearVO.setPetDetails(writeReport_petDetails_et.getText().toString());
        disappearVO.setMemberId("걍");
        disappearVO.setMemberPhone("111-1111-1111");
        disappearVO.setMemberNickName("테스트맨");

        Log.i("실종데이터 확인 : ","" + disappearVO.toString());


    }
    public void protectReport()
    {

    }
    public void witnessReport()
    {

    }
    public String getTime()
    {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = sdf.format(date);

        return getTime;
    }
    public void doTakePhotoAction() // 카메라 촬영 후 이미지 가져오기

    {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        switch(imageSelectNum)
        {
            case 1:
                firstImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, firstImageUri);
                break;
            case 2:
                secondImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, secondImageUri);
                break;
            case 3:
                thirdImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, thirdImageUri);
                break;
        }
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }
    /**

     * 앨범에서 이미지 가져오기

     */

    public void doTakeAlbumAction() // 앨범에서 이미지 가져오기
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode != RESULT_OK)
            return;

        switch(requestCode)
        {
            case PICK_FROM_ALBUM:
            {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.
                switch (imageSelectNum)
                {
                    case 1:
                        firstImageUri = data.getData();
                        Log.d("SmartWheel",firstImageUri.getPath().toString());
                        break;
                    case 2:
                        secondImageUri = data.getData();
                        Log.d("SmartWheel",secondImageUri.getPath().toString());
                        break;
                    case 3:
                        thirdImageUri = data.getData();
                        Log.d("SmartWheel",thirdImageUri.getPath().toString());
                        break;
                }
            }

            case PICK_FROM_CAMERA: {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.
                Intent intent = new Intent("com.android.camera.action.CROP");
                switch (imageSelectNum)
                {
                    case 1:
                        intent.setDataAndType(firstImageUri, "image/*");
                        break;
                    case 2:
                        intent.setDataAndType(secondImageUri, "image/*");
                        break;
                    case 3:
                        intent.setDataAndType(thirdImageUri, "image/*");
                }

                // CROP할 이미지를 200*200 크기로 저장
                intent.putExtra("outputX", 200); // CROP한 이미지의 x축 크기
                intent.putExtra("outputY", 200); // CROP한 이미지의 y축 크기
                intent.putExtra("aspectX", 1); // CROP 박스의 X축 비율
                intent.putExtra("aspectY", 1); // CROP 박스의 Y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_IMAGE); // CROP_FROM_CAMERA case문 이동
                break;
            }
            case CROP_FROM_IMAGE:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                if(resultCode != RESULT_OK) {
                    return;
                }

                final Bundle extras = data.getExtras();
                // CROP된 이미지를 저장하기 위한 FILE 경로
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+
                        "/SmartWheel/"+System.currentTimeMillis()+".jpg";
                if(extras != null)
                {
                    Bitmap photo = extras.getParcelable("data"); // CROP된 BITMAP
                    switch(imageSelectNum) {
                        case 1:
                            writeReport_imageSelect1_iv.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                            break;
                        case 2:
                            writeReport_imageSelect2_iv.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                            break;
                        case 3:
                            writeReport_imageSelect3_iv.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                            break;
                    }
                    storeCropImage(photo, filePath); // CROP된 이미지를 외부저장소, 앨범에 저장한다.
                    absoultePath = filePath;
                    break;
                }
                // 임시 파일 삭제
                File f = null;
                switch (imageSelectNum)
                {
                    case 1:
                        f = new File(firstImageUri.getPath());
                        break;
                    case 2:
                        f = new File(secondImageUri.getPath());
                        break;
                    case 3:
                        f = new File(thirdImageUri.getPath());
                        break;
                }
                if(f.exists())
                {
                    f.delete();
                }
            }
        }

    } private void storeCropImage(Bitmap bitmap, String filePath) {
        // SmartWheel 폴더를 생성하여 이미지를 저장하는 방식이다.
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel";
        File directory_SmartWheel = new File(dirPath);
        if(!directory_SmartWheel.exists()) // SmartWheel 디렉터리에 폴더가 없다면 (새로 이미지를 저장할 경우에 속한다.)
        {
            directory_SmartWheel.mkdir();
        }
        File copyFile = new File(filePath);
        BufferedOutputStream out = null;
        try {
            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            // sendBroadcast를 통해 Crop된 사진을 앨범에 보이도록 갱신한다.
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(copyFile)));

            out.flush();
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
