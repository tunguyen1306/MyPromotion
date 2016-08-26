package com.example.user.navigationdrawer;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by TuNguyen on 8/22/2016.
 */
interface ServicePromotion {
    @GET("/GetLogin/")
    void GetLogin(@Query("userName")String userName,
                   @Query("passWord")  String passWord,
                   Callback<List<UserDto>> items);
}
