package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hellopet.sangji.hellopet.PetDetailActivity;
import com.hellopet.sangji.hellopet.R;

import java.util.ArrayList;

import VO.SimpleInterestPetVO;
import VO.SimplePetVO;

/**
 * Created by ael on 2017. 11. 11..
 */

public class InterestPetGridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SimpleInterestPetVO> listData = new ArrayList<SimpleInterestPetVO>();

    public InterestPetGridViewAdapter(Context context, ArrayList<SimpleInterestPetVO> listData) {
        super();
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount()
    {
        return listData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_interestpet_view, null);
        }

        final TextView interestpetId = (TextView) convertView.findViewById(R.id.item_interestpet_id);
        ImageView interestpetImg = (ImageView) convertView.findViewById(R.id.item_interestpet_img);
        TextView interestpetType = (TextView) convertView.findViewById(R.id.item_interestpet_type);
        TextView interestpetGender = (TextView) convertView.findViewById(R.id.item_interestpet_gender);


        //petImg.setTe
        interestpetId.setText(listData.get(position).getInterestPetId());
        interestpetType.setText(listData.get(position).getInterestPetType());
        interestpetGender.setText(listData.get(position).getInterestPetGender());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText (v.getContext(), ""+ interestpetId.getText(), Toast.LENGTH_SHORT).show();

                context.startActivity(new Intent(context,PetDetailActivity.class));

                // 클릭시 세부 내용 화면으로 전환.
                // 클릭 이벤트를 다양하게 적용해야 하는 경우 holder에 리스너를 다는 형태로는 구현이 안될거 같음
            }
        });

        return convertView;
    }
}
