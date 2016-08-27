package com.example.user.navigationdrawer;

import java.util.List;
import java.util.Objects;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by TuNguyen on 8/22/2016.
 */
interface ServicePromotion {
    @GET("/GetLogin/")
    void GetLogin(@Query("email")String email,
                  @Query("passWord")  String passWord,
                  Callback<List<UserDto>> items);

    @POST("/Register")
    void GetRegister(
            @Body UserRegister item,
            Callback<List<UserDto>> items);
}
