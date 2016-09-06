package com.mypromotion.mypromotion.model;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
interface ServicePromotion {
    @GET("/GetLogin/")
    void GetLogin(@Query("userName")String userName,
                  @Query("passWord")  String passWord,
                  Callback<List<UserDto>> items);

    @POST("/Register")
    void GetRegister(
            @Body UserDto item,
             Callback<List<UserDto>> items);
    @GET("/GetAdvert/")
    void GetAdvert(@Query("pageNum")int pageNum,
                   Callback<List<ListingDto>> items);
}
