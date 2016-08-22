package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by TuNguyen on 8/21/2016.
 */
public class Register extends Fragment {
    Button btnRegister;
    EditText editUserName;
    EditText editPass;
    EditText editEmail;
    EditText editPhone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View  view=inflater.inflate(R.layout.nav_register,container,false);
        btnRegister=(Button)view.findViewById(R.id.btnRegister);
        editUserName=(EditText)view.findViewById(R.id.editUserName);
        editPass=(EditText)view.findViewById(R.id.editPassWord);
        editEmail=(EditText)view.findViewById(R.id.editEmail);
        editPhone=(EditText)view.findViewById(R.id.editPhone);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String userName,passWord,Email,Phone;
                userName=editUserName.getText().toString();
                passWord=editPass.getText().toString();
                Email=editEmail.getText().toString();
                Phone=editPhone.getText().toString();
                Toast.makeText(getActivity(),"Đăng ký thành công",Toast.LENGTH_SHORT);

            }
        });
        return view;
    }
}
