package com.mypromotion.mypromotion.view.activity;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;



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
    }

    public  ServiceConnect GetService()
    {
        return serviceman;
    }
    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(URL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> Service) {
        String credentials = "admin" + ":" + "123456";
        // create Base64 encodet string
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", basic);
            }
        });
        RestAdapter adapter = builder.build();
        return adapter.create(Service);
    }

}
