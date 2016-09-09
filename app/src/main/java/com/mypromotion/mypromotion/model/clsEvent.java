package com.mypromotion.mypromotion.model;

import android.util.Log;
import com.mypromotion.mypromotion.view.activity.ResClient;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Administrator on 9/9/2016.
 */
public class clsEvent {
    public static void EventRegister(String Email, String Phone, String firstName, String lastName, int type_role, int status, String passWord, String imgUrl, String fullName) {

        ResClient resClient=new ResClient();
        final UserDto userRegister = new UserDto(0, "", "");
        userRegister.email_user_promotion = Email;
        userRegister.phone_user_promotion = Phone;
        userRegister.first_name_user_promotion = firstName;
        userRegister.last_name_user_promotion = lastName;
        userRegister.type_role_user_promotion = type_role;
        userRegister.status_user_promotion = status;
        userRegister.pass_user_promotion = passWord;
        userRegister.img_user_promotion = imgUrl;
        userRegister.full_name_user_promotion = fullName;
        resClient.GetService().GetRegister(userRegister
                , new Callback<List<UserDto>>() {
                    @Override
                    public void success(List<UserDto> userDtos, Response response) {
                        if (userDtos.get(0).IDout == 0) {


                        }
                        if (userDtos.get(0).IDout == 1) {

                        }
                        userRegister.IDout = userDtos.get(0).IDout;

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
