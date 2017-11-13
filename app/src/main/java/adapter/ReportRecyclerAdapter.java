package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellopet.sangji.hellopet.R;
import com.hellopet.sangji.hellopet.ViewReportActivity;

import java.util.ArrayList;

import VO.SimpleReportVO;

/**
 * Created by ael on 2017. 10. 15..
 */

public class ReportRecyclerAdapter extends RecyclerView.Adapter<ReportRecyclerAdapter.ItemViewHolder>{

    ArrayList<SimpleReportVO> reportList;

    public ReportRecyclerAdapter(ArrayList<SimpleReportVO> reportList){
        this.reportList = reportList;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_report_view,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        SimpleReportVO item = reportList.get(position);
        holder.reportId.setText(item.getReportId());
        holder.petType.setText(item.getPetType());
        holder.petRace.setText(item.getPetRace());
        holder.petGender.setText(item.getPetGender());
        holder.petName.setText(item.getPetName());
        holder.petDetails.setText(item.getDetails());
        holder.place.setText(item.getPlace());
        holder.time.setText(item.getTime());
        // 이미지 부분 추가

        holder.itemView.setTag(item);

    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return reportList.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView reportId;
        private TextView reportType;
        private TextView petType;
        private TextView petRace;
        private TextView petGender;
        private TextView petName;
        private TextView petDetails;
        private TextView place;
        private TextView time;
        private ImageView petImg;
        private Context context;

        public ItemViewHolder(View itemView) {
            super(itemView);
            
            reportId = (TextView) itemView.findViewById(R.id.item_report_id);
            reportType = (TextView)itemView.findViewById(R.id.item_reportType_id);
            petType = (TextView) itemView.findViewById(R.id.item_pet_type);
            petRace = (TextView) itemView.findViewById(R.id.item_pet_race);
            petGender = (TextView) itemView.findViewById(R.id.item_pet_gender);
            petName = (TextView) itemView.findViewById(R.id.item_pet_name);
            petDetails = (TextView) itemView.findViewById(R.id.item_pet_details);
            place = (TextView) itemView.findViewById(R.id.item_place);
            time = (TextView) itemView.findViewById(R.id.item_time);
            petImg = (ImageView) itemView.findViewById(R.id.item_pet_img);

            itemView.setOnClickListener(this);

            // 세훈 추가
            context = itemView.getContext();

        }

        @Override
        public void onClick(View v) {

            //Toast.makeText (v.getContext(), ""+ reportId.getText(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ViewReportActivity.class);
            // 이제 여기에 데이터를 넘겨주는 intent.putExtra();를 사용해 데이터를 ViewReportActivity에 넘겨주면 될듯?
            intent.putExtra("reportId",reportId.getText().toString());
            intent.putExtra("reportType", reportType.getText().toString());
            context.startActivity(intent);

            // 클릭시 세부 내용 화면으로 전환.
            // 클릭 이벤트를 다양하게 적용해야 하는 경우 holder에 리스너를 다는 형태로는 구현이 안될거 같음
        }

    }
}
