package com.mypromotion.mypromotion.view.activity;


import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.UserDto;

import java.util.List;
import java.util.Objects;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

interface ServiceConnect {

    @GET("/GetLogin/")
    void GetLogin(@Query("email")String email,
                  @Query("passWord")  String passWord,
                  Callback<List<UserDto>> items);
    @POST("/Register")
    void GetRegister(
            @Body UserDto item,
             Callback<List<UserDto>> items);
    @GET("/GetAdvert/")
    void GetAdvert(@Query("pageNum")int pageNum,
                   Callback<List<ListingDto>> items);
    @Multipart
    @POST("/upload")
    void upload(@Part("myfile") TypedFile file,
                Callback<String> cb);
}
