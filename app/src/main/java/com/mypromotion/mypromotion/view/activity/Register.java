package com.mypromotion.mypromotion.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.UserDto;

import java.io.File;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class Register extends ActionBarActivity {

    //control
    private Toolbar toolbar;
    private EditText
            editRegisterEmail,
            editRegisterPassWord,
            editRegisterPassWordCon,
            editRegisterPhone,
            editRegisterFist,
            editRegisterLast,
            editRegisterFullName;

    private Button btnRegister;
    ImageButton imgRegister_profiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //clear focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();
        editRegisterEmail =(EditText)findViewById(R.id.editRegisterEmail);
                editRegisterPassWord=(EditText)findViewById(R.id.editRegisterPassWord);
        editRegisterPassWordCon=(EditText)findViewById(R.id.editRegisterPassWordCon);
        editRegisterPhone=(EditText)findViewById(R.id.editRegisterPhone);
        editRegisterFist=(EditText)findViewById(R.id.editRegisterFist);
        editRegisterLast=(EditText)findViewById(R.id.editRegisterLast);
        editRegisterFullName=(EditText)findViewById(R.id.editRegisterFullName);
        imgRegister_profiler=(ImageButton)findViewById(R.id.imgRegister_profiler);

        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName, passWord, passWordCon, Email, Phone, firstName, lastName, imgurl = "gtrgte";
                UserDto userRegister = new UserDto();
                passWordCon = editRegisterPassWordCon.getText().toString();
                passWord = editRegisterPassWord.getText().toString();
                Email = editRegisterEmail.getText().toString();
                Phone = editRegisterPhone.getText().toString();
                firstName = editRegisterFist.getText().toString();
                lastName = editRegisterLast.getText().toString();
                fullName = editRegisterFullName.getText().toString();
                EventRegister(Email, Phone, firstName, lastName, 1, 1, passWord, imgurl, fullName);

            }
        });
        imgRegister_profiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Chọn ảnh", Toast.LENGTH_SHORT).show();
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

    public void EventRegister(String Email, String Phone, String firstName, String lastName, int type_role, int status, String passWord, String imgUrl, String fullName) {
        ResClient resClient = new ResClient();
        final UserDto userRegister = new UserDto();
        userRegister.email_user_promotion = Email;
        userRegister.phone_user_promotion = Phone;
        userRegister.first_name_user_promotion = firstName;
        userRegister.last_name_user_promotion = lastName;
        userRegister.type_role_user_promotion = type_role;
        userRegister.status_user_promotion = status;
        userRegister.pass_user_promotion = passWord;
        userRegister.img_user_promotion = imgUrl;
        userRegister.full_name_user_promotion = fullName;
        resClient.GetService().GetRegister(userRegister
                , new Callback<List<UserDto>>() {
                    @Override
                    public void success(List<UserDto> userDtos, Response response) {
                        if (userDtos.get(0).IDout == 0) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_register_success), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        if (userDtos.get(0).IDout == 1) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_register_already), Toast.LENGTH_SHORT).show();
                        }
                        userRegister.IDout = userDtos.get(0).IDout;

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }

    public void Upload()
    {
//        ResClient service = ResClient.createService(ResClient.class);
//        TypedFile typedFile = new TypedFile("multipart/form-data", new File("path/to/your/file"));
//        String description = "hello, this is description speaking";
//
//        service.upload(typedFile, description, new Callback<String>() {
//            @Override
//            public void success(String s, Response response) {
//                Log.e("Upload", "success");
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.e("Upload", "error");
//            }
//        });
    }
}
