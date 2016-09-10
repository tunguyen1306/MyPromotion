package com.mypromotion.mypromotion.view.activity;


import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.UserDto;
import com.mypromotion.mypromotion.model.data_list;

import java.util.List;
import java.util.Objects;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

public interface ServiceConnect {

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

    @GET("/GetAdvert/")
    void GetAdvertDetail(@Query("idAvert")int idAvert,
                   Callback<List<ListingDto>> items);

    @Multipart
    @POST("/Upload/user/PostUserImage")
    void UploadFile(@Part("file1")TypedFile file1,
                    Callback<data_list> callback);
}
