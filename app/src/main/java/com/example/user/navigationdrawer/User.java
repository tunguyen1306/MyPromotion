package com.example.user.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 8/18/2016.
 */
public class User {

        private static String PREF_NAME = "pref";
        public static String FullName;
        public static String UrlImage;
        public static boolean Login;

        public static String name;

        public static String email;

        public static String facebookID;

        public static String gender;
        public static String first_name;
        public static String last_name;
        public static SharedPreferences getPreferent(Context context)
        {
                return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        public static  void RestorReferen(Context context)
        {
                if (User.Login)
                {
                        User.Login=getPreferent(context).getBoolean("Login",true);
                        User.FullName=getPreferent(context).getString("FullName","");
                        User.UrlImage=getPreferent(context).getString("UrlImage","");
                        User.email=getPreferent(context).getString("Email","");
                        User.gender=getPreferent(context).getString("Gender","");
                        User.facebookID=getPreferent(context).getString("facebookID","");
                }else  {

                        User.Login=getPreferent(context).getBoolean("Login",false);
                        User.FullName=getPreferent(context).getString("FullName","");
                        User.UrlImage=getPreferent(context).getString("UrlImage","");
                        User.email=getPreferent(context).getString("Email","");
                        User.gender=getPreferent(context).getString("Gender","");
                        User.facebookID=getPreferent(context).getString("facebookID","");
                }

        }
        public static void  savePreferen(Context context)
        {
                SharedPreferences.Editor edit=getPreferent(context).edit();
                if (User.Login)
                {
                        edit.putBoolean("Login",User.Login);
                        edit.putString("FullName",User.FullName);
                        edit.putString("UrlImage",User.UrlImage);
                        edit.putString("Email",User.email);
                        edit.putString("Gender",User.gender);
                        edit.putString("facebookID",User.facebookID);
                }else  {
                        edit.putBoolean("Login",false);
                        edit.putString("FullName","");
                        edit.putString("UrlImage","");
                        edit.putString("Email","");
                        edit.putString("Gender","");
                        edit.putString("facebookID","");
                }
                edit.clear();
                edit.commit();
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }




}
