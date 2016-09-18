package com.mypromotion.mypromotion.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 9/6/2016.
 */
public class Preference {
    private static String PREF_NAME = "pref";
    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);}

    public static void savePreference(Context context) {

        //SharedPreferences pre = getSharedPreferences(Prefname,MODE_PRIVATE);
        SharedPreferences.Editor edit = getPref(context).edit();
        edit.putInt("IdAdvert", ListingDto.IdAdvert);
        edit.putString("NameAdvert", ListingDto.NameAdvert);
        //////Brand///////
        edit.putInt("IdBrand", BrandDto.idBrandPromotiom);
        edit.putInt("IdCategory", BrandDto.idCategory);
        edit.putString("NameBrand", BrandDto.NameBrandPromotiom);
        edit.putInt("IdAdvertBrand", ListingDto.IdAdvertBrand);
        edit.putInt("IdAdvertCategory", ListingDto.IdAdvertCategory);
        edit.putString("AdvertNameBrand", ListingDto.AdvertNameBrand);
        /////End Brand////
        if(UserDto.login) {
            edit.putBoolean("login", UserDto.login);
            edit.putString("UserUrl",UserDto. UserUrl);
            edit.putString("UserName", UserDto.UserName);
            edit.putString("UserEmail", UserDto.UserEmail);
            edit.putString("UserFirst", UserDto.UserFirst);
            edit.putString("UserLast", UserDto.UserLast);
            edit.putString("UserPhone", UserDto.UserPhone);
            edit.putInt("UserId", UserDto.UserId);
            edit.putInt("UserStatus", UserDto.UserStatus);
            edit.putInt("UserType", UserDto.UserType);
            edit.putInt("UserRole", UserDto.UserRole);
            edit.putInt("IDout", UserDto.UserIDout);
            edit.putString("FacebookId", UserDto.FacebookId);
            edit.putString("UserPass", UserDto.UserPass);


        }else{
            edit.putBoolean("login", false);
            edit.putString("UserUrl",null);
            edit.putString("UserName", null);
            edit.putString("UserEmail", null);
            edit.putString("UserFirst", null);
            edit.putString("UserLast", null);
            edit.putString("UserPhone", null);
            edit.putInt("UserId", 0);
            edit.putInt("UserStatus",0);
            edit.putInt("UserType",0);
            edit.putInt("UserRole",0);
            edit.putInt("IDout",-1);
            edit.putString("FacebookId",null);
            edit.putString("UserPass",null);

        }
        edit.clear();
        edit.commit();
    }
    public static void restorePreference(Context context) {
        // SharedPreferences pref = getActivity().getSharedPreferences(Prefname,MODE_PRIVATE);
        UserDto.login = getPref(context).getBoolean("login", false);
        UserDto.UserUrl=getPref(context).getString("UserUrl","");
        UserDto.UserName=getPref(context).getString("UserName","");
        UserDto.UserEmail=getPref(context).getString("UserEmail", "");
        UserDto.UserFirst=getPref(context).getString("UserFirst", "");
        UserDto.UserLast=getPref(context).getString("UserLast", "");
        UserDto.UserPhone=getPref(context).getString("UserPhone", "");
        UserDto.UserId=getPref(context).getInt("UserId",0);
        UserDto.UserStatus=getPref(context).getInt("UserStatus", 0);
        UserDto.UserType=getPref(context).getInt("UserType", 0);
        UserDto.UserRole=getPref(context).getInt("UserRole", -1);
        UserDto.UserLast=getPref(context).getString("FacebookId", "");
        UserDto.UserPass=getPref(context).getString("UserPass", "");
        ListingDto.IdAdvert=getPref(context).getInt("IdAdvert",0);
        ListingDto.NameAdvert=getPref(context).getString("NameAdvert", "");
        BrandDto.idBrandPromotiom=getPref(context).getInt("IdBrand",0);
        BrandDto.NameBrandPromotiom=getPref(context).getString("NameBrand", "");
        BrandDto.idCategory=getPref(context).getInt("IdCategory",0);

        ListingDto.IdAdvertBrand=getPref(context).getInt("IdAdvertBrand",0);
        ListingDto.AdvertNameBrand=getPref(context).getString("AdvertNameBrand", "");
        ListingDto.IdAdvertCategory=getPref(context).getInt("IdAdvertCategory",0);

    }
}
