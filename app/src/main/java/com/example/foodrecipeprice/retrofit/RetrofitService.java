package com.example.foodrecipeprice.retrofit;

import com.example.foodrecipeprice.Utiles;
import com.example.foodrecipeprice.model.LocationCodeResponse;
import com.example.foodrecipeprice.model.LostGoodsDetailResponse;
import com.example.foodrecipeprice.model.LostGoodsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("LostGoodsInfoInqireService/getLostGoodsInfoAccToClAreaPd")
    Call<LostGoodsResponse> getLostGoodsList(
            @Query("serviceKey") String serviceKey,
            @Query("start_ymd") String start_ymd,
            @Query("end_ymd") String end_ymd,
            @Query("PRDT_CL_CD_01") String prdt_cl_cd_01,
            @Query("PRDT_CL_CD_02") String Prdt_cl_cd_02,
            @Query("LST_LCT_CD") String lst_lct_cd,
            @Query("pageNo") int pageNo,
            @Query("numOfRows") int numOfRows);

    @GET("LostGoodsInfoInqireService/getLostGoodsDetailInfo")
    Call<LostGoodsDetailResponse> getLostGoodsDetail(@Query("serviceKey")String serviceKey, @Query(Utiles.ATC_ID) String act_id);

    @GET("CmmnCdService/getCmmnCd")
    Call<LocationCodeResponse> getLocationList(@Query("serviceKey")String serviceKey, @Query(Utiles.GRP_NM) String grp_nm);

    //@GET("CmmnCdService/getThngClCd")
    //Call<ThngClCd> getThngClcdList(@Query("serviceKey")String serviceKey);

}
