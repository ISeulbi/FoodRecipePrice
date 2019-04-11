package com.example.foodrecipeprice.retrofit;

import com.example.foodrecipeprice.Utiles;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetroInstance {
    private static Retrofit retrofit = null; //싱글톤
    private static RetrofitService service = null;

    public static void makeInstance(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utiles.BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }

    private static OkHttpClient buildHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5,TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(interceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("key", "value").build();
                return chain.proceed(request);
            }
        });

        return builder.build();
    }

    public static RetrofitService getRetroService(){
        makeInstance();
        if(service == null){
            service = retrofit.create(RetrofitService.class);
        }
        return  service;
    }


}
