package com.example.foodrecipeprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipeprice.model.BodyForLostGoodDetail;
import com.example.foodrecipeprice.model.LostGoodsDetail;
import com.example.foodrecipeprice.model.LostGoodsDetailResponse;
import com.example.foodrecipeprice.retrofit.RetroInstance;
import com.example.foodrecipeprice.retrofit.RetrofitService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostGoodsDetailActivity extends AppCompatActivity {
    private LostGoodsDetail lgdList;
    private TextView detailView;
    private ImageView iv_pic;
    private TextView tv_IstPrdtNm, tv_IstSbjt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_goods_detail);

        iv_pic = findViewById(R.id.iv_pic);
        tv_IstPrdtNm = findViewById(R.id.tv_lstPrdtNm);
        tv_IstSbjt = findViewById(R.id.tv_lstSbjt);

        Intent intent = getIntent();
        String actid = intent.getStringExtra(Utiles.ATC_ID);

        getData(actid);
        Toast.makeText(this, actid, Toast.LENGTH_SHORT).show();
    }

    private void getData(String actId){

        RetrofitService rs = RetroInstance.getRetroService();
        Call<LostGoodsDetailResponse> callDetail = rs.getLostGoodsDetail(Utiles.SERVICE_KEY, actId);
        callDetail.enqueue(new Callback<LostGoodsDetailResponse>() {
            @Override
            public void onResponse(Call<LostGoodsDetailResponse> call, Response<LostGoodsDetailResponse> response) {
                if (response != null){
                    LostGoodsDetailResponse lgs = response.body();
                    BodyForLostGoodDetail body = lgs.getBody();
                    LostGoodsDetail lgd = body.getItem();
                    Picasso.get().load(lgd.getLstFilePathImg()).into(iv_pic);
                    tv_IstPrdtNm.setText(lgd.getLstPrdtNm());
                    tv_IstSbjt.setText(lgd.getLstSbjt());
                }
            }

            @Override
            public void onFailure(Call<LostGoodsDetailResponse> call, Throwable t) {
                if(call != null) {

                }
            }
        });

    }
}
