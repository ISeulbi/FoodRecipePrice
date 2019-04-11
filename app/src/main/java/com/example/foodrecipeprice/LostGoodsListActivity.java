package com.example.foodrecipeprice;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.foodrecipeprice.model.BodyForLocation;
import com.example.foodrecipeprice.model.BodyForLostGoodsList;
import com.example.foodrecipeprice.model.LocationCodeResponse;
import com.example.foodrecipeprice.model.LocationDetail;
import com.example.foodrecipeprice.model.LostGoods;
import com.example.foodrecipeprice.model.LostGoodsResponse;
import com.example.foodrecipeprice.retrofit.RetroInstance;
import com.example.foodrecipeprice.retrofit.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostGoodsListActivity extends AppCompatActivity {
    private String cd_01, cd_02;
    private String[] locationArray;
    private String[] locationCdArray = {"LCA000", "LCA000", "LCA000"};
    private GregorianCalendar today = new GregorianCalendar();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private String strEndDate, strStartDate;
    private List<LostGoods> lgList;
    private ListView listView;
    private LostGoodsListAdapter lostGoodsListAdapter;
    private LocationListAdapter locationListAdapter;
    private List<LocationDetail> needLocationList;
    private int pageNo = 1;
    private String locationCd = "";
    private CustomCircleProgressDialog dialog;
    private int previousTotal = 0;

    AbsListView.OnScrollListener scrollChangeListener = new AbsListView.OnScrollListener() {
        private int visibleThreshold = 15;

        private boolean loading = false;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            Log.d("test","firstVisibleItem:" + firstVisibleItem +
                                    ", visibleItemCount : " + visibleItemCount +
                                    ", totalItemCount : " + totalItemCount +
                                    ", previousTotal : " + previousTotal +
                                    ", loading : " + loading +
                                    ", pageNo : " + pageNo
                    );
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                    pageNo++;
                }
            } else if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                getLostGoodsList(strStartDate, strEndDate, cd_01, cd_02, locationCd, (pageNo+1));
                Log.i("totalCont", Integer.toString(totalItemCount));
                loading = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lgList = new ArrayList();
        setContentView(R.layout.activity_lost_goods_list);
        listView = findViewById(R.id.listView);


        needLocationList = new ArrayList<>();
        locationListAdapter = new LocationListAdapter(LostGoodsListActivity.this, needLocationList);

        lostGoodsListAdapter = new LostGoodsListAdapter(LostGoodsListActivity.this,lgList);
        listView.setAdapter(lostGoodsListAdapter);

        Resources res = getResources();
        locationArray =res.getStringArray(R.array.spinner_list_item_array);

        Intent intent = getIntent();
        cd_01 = intent.getStringExtra(Utiles.CD_01);
        cd_02 = intent.getStringExtra(Utiles.CD_02);

        strEndDate = format.format(today.getTime());
        today.add(Calendar.DAY_OF_MONTH, -30);
        strStartDate = format.format(today.getTime());

        getLocationList();
        //Toast.makeText(this, cd_01 + cd_02,Toast.LENGTH_SHORT).show();
    }

    private void getLocationList(){
        RetrofitService rs = RetroInstance.getRetroService();
        Call<LocationCodeResponse> callList = rs.getLocationList(Utiles.SERVICE_KEY, Utiles.CD_VALUE);
        callList.enqueue(new Callback<LocationCodeResponse>() {
            @Override
            public void onResponse(Call<LocationCodeResponse> call, Response<LocationCodeResponse> response) {
                if(response.isSuccessful()){
                    BodyForLocation bodyData = response.body().getBody();
                    List<LocationDetail> LocationDetailList = bodyData.getItems();

                    for(LocationDetail vo : LocationDetailList){
                        String cd = vo.getCommCd();
                        if(cd.length() >= 6 && cd.substring((cd.length() - 3), cd.length()).equals("000")){
                            needLocationList.add(vo);
                        }
                    }

                    locationListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<LocationCodeResponse> call, Throwable t) {

            }
        });
    }

    private  void getLostGoodsList(String start_ymd, String end_ymd, String prdt_cd1,String prdt_cd2, String locationCd, int pageNo){
        if(pageNo == 1){
            previousTotal = 0;
            lgList.clear();
            lostGoodsListAdapter.notifyDataSetChanged();
        }

        if(dialog!=null){
            dialog.dismiss();
        }

        dialog = CustomCircleProgressDialog.getNewInstance(this);
        dialog.show();

        //Retrofit
        RetrofitService rs = RetroInstance.getRetroService();
        Call<LostGoodsResponse> callList = rs.getLostGoodsList(Utiles.SERVICE_KEY, start_ymd,end_ymd,
                prdt_cd1,prdt_cd2, locationCd, pageNo, 50);

        callList.enqueue(new Callback<LostGoodsResponse>() {
            @Override
            public void onResponse(Call<LostGoodsResponse> call, Response<LostGoodsResponse> response) {
                if (response.isSuccessful()){
                    LostGoodsResponse lgs = response.body();
                    BodyForLostGoodsList bodyForLostGoodsList = lgs.getBody();
                    lgList.addAll(bodyForLostGoodsList.getItems());
                    lostGoodsListAdapter.notifyDataSetChanged();

                }
                //이벤트 다시 연결
                listView.setOnScrollListener(scrollChangeListener);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<LostGoodsResponse> call, Throwable t) {
                 dialog.dismiss();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lost_goods_list, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setDropDownWidth(Utiles.dpToPx(this,150));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //이벤트 해제
                listView.setOnScrollListener(null);
                LocationDetail selectedVo = needLocationList.get(position);
                pageNo = 1;
                locationCd = selectedVo.getCommCd();
                getLostGoodsList(strStartDate, strEndDate, cd_01, cd_02, locationCd, pageNo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        */

        spinner.setAdapter(locationListAdapter);

        return true;
    }

}

class LocationListAdapter extends BaseAdapter{
    private Context context;
    private List<LocationDetail> list;

    LocationListAdapter(Context context, List<LocationDetail> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        if(cv == null){
            cv = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
            TextView textView = cv.findViewById(android.R.id.text1);
            textView.setTextColor(Color.WHITE);
            LocationDetail vo = list.get(position);
            textView.setText(vo.getCdNm());
        }
        return cv;
    }
}


//adapter
class LostGoodsListAdapter extends BaseAdapter {
    private List<LostGoods> list;
    private Context ctx;

    LostGoodsListAdapter(Context ctx, List<LostGoods> list){
        this.ctx = ctx;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        ViewHolder vh;
        //view 홀더
        if(cv == null){
            cv = LayoutInflater.from(ctx).inflate(R.layout.item_lost_goods_list, parent, false);
            vh = new ViewHolder(cv);
            cv.setTag(vh);
        } else {
            vh = (ViewHolder)cv.getTag();
        }

        LostGoods vo = list.get(position);
        String actId = vo.getAtcId();
        String IstPlace = vo.getLstPlace();
        String lstPrdtNm = vo.getPrdtClNm();
        String lstSbjt = vo.getLstSbjt();
        String lstYmd = vo.getLstYmd();
        String prdtClNm = vo.getPrdtClNm();
        int rnum = vo.getRnum();

        vh.tv_atcId.setText(actId);
        vh.tv_lstPlace.setText(IstPlace);
        vh.tv_lstPrdtNm.setText(lstPrdtNm);
        vh.tv_lstSbjt.setText(lstSbjt);
        vh.tv_lstYmd.setText(lstYmd);
        vh.tv_prdtClNm.setText(prdtClNm);
        //int -> String으로 바꿔주기
        vh.tv_rnum.setText(Integer.toString(rnum));

        vh.ll_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, LostGoodsDetailActivity.class);
                intent.putExtra(Utiles.ATC_ID, actId);
                ctx.startActivity(intent);
            }
        });

        return cv;
    }

    class ViewHolder {
        LinearLayout ll_container;
        TextView tv_atcId, tv_lstPlace,tv_lstPrdtNm,tv_lstSbjt,tv_lstYmd,tv_prdtClNm,tv_rnum;

        ViewHolder(View v){
            ll_container = v.findViewById(R.id.ll_container);

            tv_atcId = v.findViewById(R.id.tv_atcId);
            tv_lstPlace = v.findViewById(R.id.tv_lstPlace);
            tv_lstPrdtNm = v.findViewById(R.id.tv_lstPrdtNm);
            tv_lstSbjt = v.findViewById(R.id.tv_lstSbjt);
            tv_lstYmd = v.findViewById(R.id.tv_lstYmd);
            tv_prdtClNm = v.findViewById(R.id.tv_prdtClNm);
            tv_rnum = v.findViewById(R.id.tv_rnum);
        }
    }
}