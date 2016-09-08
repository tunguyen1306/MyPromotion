package com.mypromotion.mypromotion.model;

import com.mypromotion.mypromotion.view.activity.ResClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 9/6/2016.
 */
public class UserDto {
    public static  String UserEmail,UserName,UserUrl,UserFirst,UserLast,UserPhone,FacebookId;
    public static boolean login;
    public static int UserId,UserStatus,UserRole,UserType,UserIDout;

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

    public String getLast_name_user_promotion() {
        return last_name_user_promotion;
    }

    public void setLast_name_user_promotion(String last_name_user_promotion) {
        this.last_name_user_promotion = last_name_user_promotion;
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

    public int getId_user_promotion() {
        return id_user_promotion;
    }

    public void setId_user_promotion(int id_user_promotion) {
        this.id_user_promotion = id_user_promotion;
    }

    public int id_user_promotion;

//    public String getName() {
//        return full_name;
//    }
//    public void setName(String full_name) {
//        this.full_name = full_name;
//    }
//    public String full_name;
//    public int getId() {
//        return id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//    public int id ;
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPhone() {
//        return phone;
//    }
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//    public String getFirst_name() {
//        return first_name;
//    }
//    public void setFirst_name(String first_name) {
//        this.first_name = first_name;
//    }
//    public String getLast_name() {
//        return last_name;
//    }
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//    public int getType_role() {
//        return type_role;
//    }
//    public void setType_role(int type_role) {
//        this.type_role = type_role;
//    }
//    public int getStatus() {
//        return status;
//    }
//    public void setStatus(int status) {
//        this.status = status;
//    }
//    public String getPass() {
//        return pass;
//    }
//    public void setPass(String pass) {
//        this.pass = pass;
//    }
//    public String email;
//    public String phone ;
//    public String first_name ;
//    public String last_name ;
//    public int type_role ;
//    public int status ;
//    public String pass ;
//    public String getUrl_img() {
//        return url_img;
//    }
//    public void setUrl_img(String url_img) {
//        this.url_img = url_img;
//    }
//    public String url_img ;

    public UserDto(int UserId,String AvatarData,String AvatarName){
        this.UserId=UserId;
        this.img_user_promotion=AvatarData;

    }
    public class Model_data_string {
        public int Code;
        public String Message;
        public String Data;
    }

}

