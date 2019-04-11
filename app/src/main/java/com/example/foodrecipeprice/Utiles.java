package com.example.foodrecipeprice;

import android.content.Context;
import android.util.TypedValue;

import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;

public class Utiles {
    public static final String BASE_URL = "http://apis.data.go.kr/1320000/";
    public static final String SERVICE_KEY =
            "ULOtnjZFMJ6EGXtnqQNviIs5637NovijR7v/7FjU1T6GJB1r8bvWsHfciVoat7YQwk990xkL+UNrW35ZaBzEnw==";
    public static final String CD_01 = "cd_01";
    public static final String CD_02 = "cd_02";

    public static final String ATC_ID = "ATC_ID";

    public static final String GRP_NM = "GRP_NM";
    public static final String CD_VALUE = "지역구분";

    public static int dpToPx(Context context, float dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }
}
