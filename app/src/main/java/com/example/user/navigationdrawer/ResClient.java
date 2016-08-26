package com.example.user.navigationdrawer;

/**
 * Created by Administrator on 8/22/2016.
 */

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.client.OkClient;


public class ResClient {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    // private static final String URL = "http://instinctcoder.com/wp-content/uploads/2015/08/";
    private static final String URL = "http://api.vangia.net/api";
    private retrofit.RestAdapter restAdapter;
    private ServicePromotion serviceman;

    public ResClient()
    {

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);

        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient))
                .build();
        serviceman =restAdapter.create(ServicePromotion.class);
    }

    public  ServicePromotion getService()
    {
        return serviceman;
    }
}
