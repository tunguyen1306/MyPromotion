package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 8/21/2016.
 */
public class Register extends Fragment {
    private static String PREF_NAME = "pref";
    Button btnRegister;
    EditText editfull_name;
    EditText editPass;
    EditText editEmail;
    EditText editPhone;
    EditText editfirst;
    EditText editLast;
    EditText editPassCon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.nav_register,container,false);
        User.RestorReferen(getActivity());
        btnRegister=(Button)view.findViewById(R.id.btnRegister);
        editEmail=(EditText)view.findViewById(R.id.editRegisterEmail);
        editPass=(EditText)view.findViewById(R.id.editRegisterPassWord);
        editPassCon=(EditText)view.findViewById(R.id.editRegisterPassWordCon);
        editPhone=(EditText)view.findViewById(R.id.editRegisterPhone);
        editfull_name=(EditText)view.findViewById(R.id.editRegisterFullName);
        editLast=(EditText)view.findViewById(R.id.editRegisterLast);
        editfirst=(EditText)view.findViewById(R.id.editRegisterFist);
        if (User.Login==1)
        {
            editEmail.setText(User.email);
            editfull_name.setText(User.full_name);
        }
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName,passWord,passWordCon,Email,Phone,firstName,lastName,imgurl = "gtrgte";

                passWordCon=editPassCon.getText().toString();
                passWord=editPass.getText().toString();
                Email=editEmail.getText().toString();
                Phone=editPhone.getText().toString();
                firstName=editfirst.getText().toString();
                lastName=editLast.getText().toString();
                fullName=editfull_name.getText().toString();
                EventRegister(Email,Phone,firstName,lastName,1,1,passWord,imgurl,fullName);

            }
        });
        return view;
    }
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
//        UserRegister userRegister=new UserRegister();
//        userRegister.email_user_promotion="234";
//        userRegister.phone_user_promotion="324";
//        userRegister.first_name_user_promotion="324";
//        userRegister.last_name_user_promotion="324";
//        userRegister.type_role_user_promotion=1;
//        userRegister.status_user_promotion=1;
//        userRegister.pass_user_promotion="4324";
//        userRegister.img_user_promotion="234";
//        userRegister.full_name_user_promotion="";
        resClient.getService().GetRegister(userRegister
                , new Callback<List<UserDto>>() {
                    @Override
                    public void success(List<UserDto> userDtos, Response response) {

                           
                            Toast.makeText(getActivity(),"Đăng ký thành công",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    public void MTRegister() {
        ResClient resClient = new ResClient();
        UserRegister userRegister=new UserRegister();
        userRegister.email_user_promotion="234";
        userRegister.phone_user_promotion="324";
        userRegister.first_name_user_promotion="324";
        userRegister.last_name_user_promotion="324";
        userRegister.type_role_user_promotion=1;
        userRegister.status_user_promotion=1;
        userRegister.pass_user_promotion="4324";
        userRegister.img_user_promotion="234";
        userRegister.full_name_user_promotion="";
        resClient.getService().GetRegister(userRegister, new Callback<List<UserDto>>() {
            @Override
            public void success(List<UserDto> strings, Response response) {

                    Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Đăng nhập có vấn đề vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });

    }


}