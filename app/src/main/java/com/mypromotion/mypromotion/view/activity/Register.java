package com.mypromotion.mypromotion.view.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.UserDto;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.view.WindowManager.*;

public class Register extends ActionBarActivity {

    //control
    private Toolbar toolbar;
    private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //clear focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();
        btnSignUp=(Button)findViewById(R.id.btnRegister);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName,passWord,passWordCon,Email,Phone,firstName,lastName,imgurl = "gtrgte";
                UserDto userRegister =new UserDto();
//                passWordCon=editPassCon.getText().toString();
//                passWord=editPass.getText().toString();
//                Email=editEmail.getText().toString();
//                Phone=editPhone.getText().toString();
//                firstName=editfirst.getText().toString();
//                lastName=editLast.getText().toString();
//                fullName=editfull_name.getText().toString();
//                EventRegister(Email,Phone,firstName,lastName,1,1,passWord,imgurl,fullName);

                passWordCon="123";
                passWord="123";
                Email="123";
                Phone="123";
                firstName="123";
                lastName="123";
                fullName="123";
                EventRegister(Email,Phone,firstName,lastName,1,1,passWord,imgurl,fullName);
            }
        });
    }//end onCreate

    public void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_title, null);
        LinearLayout ln_back = (LinearLayout) mCustomView.findViewById(R.id.ln_back);
        TextView tv_title = (TextView) mCustomView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.title_activity_register));
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
    public void EventRegister(String Email,String Phone,String firstName,String lastName,int type_role,int status,String passWord,String imgUrl,String fullName)
    {
        ResClient resClient=new ResClient();
       final UserDto userRegister=new UserDto();
        userRegister.email_user_promotion=Email;
        userRegister.phone_user_promotion=Phone;
        userRegister.first_name_user_promotion=firstName;
        userRegister.last_name_user_promotion=lastName;
        userRegister.type_role_user_promotion=type_role;
        userRegister.status_user_promotion=status;
        userRegister.pass_user_promotion=passWord;
        userRegister.img_user_promotion=imgUrl;
        userRegister.full_name_user_promotion=fullName;
        resClient.GetService().GetRegister(userRegister
                , new Callback<List<UserDto>>() {
                    @Override
                    public void success(List<UserDto> userDtos, Response response) {
                        userRegister.IDout=userDtos.get(0).IDout;

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
