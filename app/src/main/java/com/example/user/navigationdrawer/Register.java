package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
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
    EditText editfull_name,editPass,editEmail,editPhone,editfirst,editLast,editPassCon;
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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName,passWord,passWordCon,Email,Phone,firstName,lastName,imgurl = "gtrgte";
                UserRegister userRegister =new UserRegister();
                passWordCon=editPassCon.getText().toString();
                passWord=editPass.getText().toString();
                Email=editEmail.getText().toString();
                Phone=editPhone.getText().toString();
                firstName=editfirst.getText().toString();
                lastName=editLast.getText().toString();
                fullName=editfull_name.getText().toString();
                userRegister.EventRegister(Email,Phone,firstName,lastName,1,1,passWord,imgurl,fullName);

            }
        });
        return view;
    }


}