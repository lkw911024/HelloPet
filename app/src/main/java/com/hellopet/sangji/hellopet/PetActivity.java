package com.hellopet.sangji.hellopet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

import VO.SimpleInterestPetVO;
import VO.SimplePetVO;
import adapter.InterestPetGridViewAdapter;
import adapter.PetGridViewAdapter;

public class PetActivity extends AppCompatActivity {

    private ImageButton petAddBtn;
    private GridView petListView;
    private GridView interestpetGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        bindView();

    }

    protected void bindView()
    {
        this.petListView = (GridView) findViewById(R.id.pet_gridview);
        this.petAddBtn = (ImageButton) findViewById(R.id.pet_add_btn);
        this.interestpetGridView = (GridView) findViewById(R.id.interpet_gridview);


        ArrayList<SimplePetVO> data = new ArrayList<SimplePetVO>();
        data.add(new SimplePetVO("1","토리","수컷"));
        data.add(new SimplePetVO("2","솜이","암컷"));
        data.add(new SimplePetVO("3","해피","수컷"));
        data.add(new SimplePetVO("4","아지","수컷"));

        PetGridViewAdapter adapter = new PetGridViewAdapter(getApplicationContext(),data);
        this.petListView.setAdapter(adapter);


        ArrayList<SimpleInterestPetVO> interestPetData = new ArrayList<SimpleInterestPetVO>();
        interestPetData.add(new SimpleInterestPetVO("1","강아지","수컷"));
        interestPetData.add(new SimpleInterestPetVO("2","고양이","암컷"));
        interestPetData.add(new SimpleInterestPetVO("3","강아지","수컷"));
        interestPetData.add(new SimpleInterestPetVO("4","고양이","암컷"));
        interestPetData.add(new SimpleInterestPetVO("5","강아지","수컷"));
        interestPetData.add(new SimpleInterestPetVO("6","고양이","암컷"));

        InterestPetGridViewAdapter interestpetAdapter = new InterestPetGridViewAdapter(getApplicationContext(), interestPetData);
        this.interestpetGridView.setAdapter(interestpetAdapter);

    }

}
