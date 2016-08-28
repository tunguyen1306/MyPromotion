package com.example.user.navigationdrawer;

import android.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 08/27/2016.
 */
public class UserRegister {

    public String getLast_name_user_promotion() {
        return last_name_user_promotion;
    }

    public void setLast_name_user_promotion(String last_name_user_promotion) {
        this.last_name_user_promotion = last_name_user_promotion;
    }

    public String getEmail_user_promotion() {
        return email_user_promotion;
    }

    public void setEmail_user_promotion(String email_user_promotion) {
        this.email_user_promotion = email_user_promotion;
    }

    public String getPhone_user_promotion() {
        return phone_user_promotion;
    }

    public void setPhone_user_promotion(String phone_user_promotion) {
        this.phone_user_promotion = phone_user_promotion;
    }

    public String getFirst_name_user_promotion() {
        return first_name_user_promotion;
    }

    public void setFirst_name_user_promotion(String first_name_user_promotion) {
        this.first_name_user_promotion = first_name_user_promotion;
    }

    public int getType_role_user_promotion() {
        return type_role_user_promotion;
    }

    public void setType_role_user_promotion(int type_role_user_promotion) {
        this.type_role_user_promotion = type_role_user_promotion;
    }

    public int getStatus_user_promotion() {
        return status_user_promotion;
    }

    public void setStatus_user_promotion(int status_user_promotion) {
        this.status_user_promotion = status_user_promotion;
    }

    public String getPass_user_promotion() {
        return pass_user_promotion;
    }

    public void setPass_user_promotion(String pass_user_promotion) {
        this.pass_user_promotion = pass_user_promotion;
    }

    public String getImg_user_promotion() {
        return img_user_promotion;
    }

    public void setImg_user_promotion(String img_user_promotion) {
        this.img_user_promotion = img_user_promotion;
    }

    public String getFull_name_user_promotion() {
        return full_name_user_promotion;
    }

    public void setFull_name_user_promotion(String full_name_user_promotion) {
        this.full_name_user_promotion = full_name_user_promotion;
    }

    public int getIDout() {
        return IDout;
    }

    public void setIDout(int IDout) {
        this.IDout = IDout;
    }
    public String email_user_promotion;
    public String phone_user_promotion;
    public String first_name_user_promotion;
    public String last_name_user_promotion;
    public int type_role_user_promotion;
    public int status_user_promotion;
    public String pass_user_promotion;
    public String img_user_promotion;
    public String full_name_user_promotion;
    public  int IDout;
    public void EventRegister(String Email,String Phone,String firstName,String lastName,int type_role,int status,String passWord,String imgUrl,String fullName)
    {
        ResClient resClient=new ResClient();
        UserRegister userRegister=new UserRegister();
        userRegister.email_user_promotion=Email;
        userRegister.phone_user_promotion=Phone;
        userRegister.first_name_user_promotion=firstName;
        userRegister.last_name_user_promotion=lastName;
        userRegister.type_role_user_promotion=type_role;
        userRegister.status_user_promotion=status;
        userRegister.pass_user_promotion=passWord;
        userRegister.img_user_promotion=imgUrl;
        userRegister.full_name_user_promotion=fullName;
        resClient.getService().GetRegister(userRegister
                , new Callback<List<UserDto>>() {
                    @Override
                    public void success(List<UserDto> userDtos, Response response) {
                        IDout=userDtos.get(0).IDout;

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }

}
