package com.mypromotion.mypromotion.view.activity;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * Created by TuNguyen on 09/06/2016.
 */
public class ResClient {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    // private static final String URL = "http://instinctcoder.com/wp-content/uploads/2015/08/";
    private static final String URL = "http://api.vangia.net/api";
    public static final String BASE_URL = "http://api.vangia.net/api/base-url";
    private retrofit.RestAdapter restAdapter;
    private ServiceConnect serviceman;

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
        serviceman =restAdapter.create(ServiceConnect.class);
        Gson localGson = new GsonBuilder().create();

    }

    public  ServiceConnect GetService()
    {
        return serviceman;
    }
}
