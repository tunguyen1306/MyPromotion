package com.mypromotion.mypromotion.view.activity;

import android.app.Application;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Administrator on 9/8/2016.
 */
public class BaseAplication extends Application {
    //web service url...
    public static final String BASE_URL = "http://api.vangia.net/api";

    private ServiceConnect serviceConnect;

    @Override
    public void onCreate() {
        super.onCreate();

        //the rest client...
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade requestFacade) {
                        if (BASE_URL.contains("api.vangia.net"))
                            requestFacade.addHeader("Host", "localhost");
                    }
                })
                .build();
        serviceConnect = restAdapter.create(ServiceConnect.class);

    }

    public ServiceConnect serviceConnect() {
        return serviceConnect;
    }
}
