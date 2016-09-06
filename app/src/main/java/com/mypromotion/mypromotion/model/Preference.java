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
        if(UserDto.login) {
            edit.putBoolean("login", UserDto.login);
            edit.putString("UserUrl",UserDto. UserUrl);
            edit.putString("UserName", UserDto.UserName);
            edit.putString("UserEmail", UserDto.UserEmail);

        }else{
            edit.putBoolean("login", false);
            edit.putString("UserUrl",null);
            edit.putString("UserName", null);
            edit.putString("UserEmail", null);

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

    }
}
