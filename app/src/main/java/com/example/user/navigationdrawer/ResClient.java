package com.example.user.navigationdrawer;

/**
 * Created by Administrator on 8/22/2016.
 */
import android.app.Service;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ResClient {
    public static final String UrlApi="http://localhost:2208/api/promotion/";
    private retrofit.RestAdapter restAdapter;
    private Service service;
    public ResClient(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);

        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(UrlApi)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient))
                .build();

        service = restAdapter.create(Service.class);
    }
    public  Service getService()
    {
        return service;
    }

}
