package com.example.foodrecipeprice;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

public class CustomCircleProgressDialog extends Dialog {

    public CustomCircleProgressDialog(Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // No Title
        setContentView(R.layout.custom_circle_progress);
    }

    public static CustomCircleProgressDialog getNewInstance(Context context){
        CustomCircleProgressDialog dialog = new CustomCircleProgressDialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
}
