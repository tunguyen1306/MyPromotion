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
        public static String full_name;
        public static String UrlImage;
        public static int Login=0;//Login =1 login=facebook =2 login=email  =0 No login
        public static String email;
        public static String facebookID;
        public static String gender;
        public static String first_name;
        public static String last_name;
        public static String phone;

        public static SharedPreferences getPreferent(Context context)
        {
                return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        public static  void RestorReferen(Context context)
        {
                if (User.Login==1)
                {
                        User.Login=getPreferent(context).getInt("Login",1);
                        User.full_name=getPreferent(context).getString("full_name","");
                        User.UrlImage=getPreferent(context).getString("UrlImage","");
                        User.email=getPreferent(context).getString("Email","");
                        User.gender=getPreferent(context).getString("Gender","");
                        User.facebookID=getPreferent(context).getString("facebookID","");
                        User.first_name=getPreferent(context).getString("first_name","");
                        User.last_name=getPreferent(context).getString("last_name","");
                        User.phone=getPreferent(context).getString("phone","");
                }else  {

                        User.full_name=getPreferent(context).getString("full_name","");
                        //User.Login=getPreferent(context).getInt("Login",0);
                        User.UrlImage=getPreferent(context).getString("UrlImage","");
                        User.email=getPreferent(context).getString("Email","");
                        User.gender=getPreferent(context).getString("Gender","");
                        User.facebookID=getPreferent(context).getString("facebookID","");
                        User.first_name=getPreferent(context).getString("first_name","");
                        User.last_name=getPreferent(context).getString("last_name","");
                        User.phone=getPreferent(context).getString("phone","");
                }

        }
        public static void  savePreferen(Context context)
        {
                SharedPreferences.Editor edit=getPreferent(context).edit();
                if (User.Login==1)
                {
                        edit.putInt("Login",User.Login);
                        edit.putString("full_name",User.full_name);
                        edit.putString("UrlImage",User.UrlImage);
                        edit.putString("Email",User.email);
                        edit.putString("Gender",User.gender);
                        edit.putString("facebookID",User.facebookID);
                        edit.putString("first_name",User.first_name);
                        edit.putString("last_name",User.last_name);
                        edit.putString("phone",User.phone);
                }else  {
                        edit.putInt("Login",0);
                        edit.putString("full_name","");
                        edit.putString("UrlImage","");
                        edit.putString("Email","");
                        edit.putString("Gender","");
                        edit.putString("facebookID","");
                        edit.putString("first_name","");
                        edit.putString("last_name","");
                        edit.putString("phone","");
                }
                edit.clear();
                edit.commit();
        }

        public String getName() {
                return full_name;
        }

        public void setName(String full_name) {
                this.full_name = full_name;
        }




}
