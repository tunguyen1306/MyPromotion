package com.mypromotion.mypromotion.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;

public class Login extends ActionBarActivity {

    //control
    private Toolbar toolbar;
    private EditText inputEmail, inputPassword;
    private TextInputLayout  inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp,btn_showpass;

    //string
    private static String PREF_NAME = "pref";
    String UserEmail,UserName,UserUrl;
    //int


    //boolean
    boolean login;
    boolean show_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btn_showpass = (Button) findViewById(R.id.btn_showpass);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        //clear focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        btn_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_pass == false) {
                    setShow_pass();
                } else {
                    setHide_pass();
                }
            }
        });
    }

    private void submitForm() {

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        UserEmail=inputEmail.getText().toString();
        UserName=getResources().getString(R.string.temp_username);
        UserUrl=getResources().getString(R.string.temp_userurl);
        login=true;
        savePreference(getApplicationContext());
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty()  ) {
            inputLayoutEmail.setError(getString(R.string.msg_empty_email));
            requestFocus(inputEmail);
            return false;
        } else if(!isValidEmail(email)){
            inputLayoutEmail.setError(getString(R.string.msg_valid_email));
            requestFocus(inputEmail);
            return false;
        }else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty() ) {
            inputLayoutPassword.setError(getString(R.string.msg_empty_password));
            requestFocus(inputPassword);
            return false;
        }else if(inputPassword.getText().toString().length()<6){
            inputLayoutPassword.setError(getResources().getString(R.string.msg_minimum_password));
            requestFocus(inputPassword);
            return false;
        }else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        //show icon show pass
        if(!inputPassword.getText().toString().trim().isEmpty()){
            btn_showpass.setVisibility(View.VISIBLE);
        }else{
            btn_showpass.setVisibility(View.GONE);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

    public void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_title, null);
        LinearLayout ln_back = (LinearLayout) mCustomView.findViewById(R.id.ln_back);
        TextView tv_title = (TextView) mCustomView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.title_activity_login));
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);}

    private void savePreference(Context context) {
        //SharedPreferences pre = getSharedPreferences(Prefname,MODE_PRIVATE);
        SharedPreferences.Editor edit =getPref(context).edit();
        edit.putBoolean("login",login);
        edit.putString("UserUrl", UserUrl);
        edit.putString("UserName",UserName);
        edit.putString("UserEmail",UserEmail);

        edit.clear();
        edit.commit();

    }

    public void setShow_pass() {

        inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        btn_showpass.setBackgroundResource(R.drawable.ic_hide);
        show_pass = true;

    }

    public void setHide_pass() {
        inputPassword.setInputType(129); //129 is the input type set when setting android:inputType="textPassword"
        btn_showpass.setBackgroundResource(R.drawable.ic_show);
        show_pass = false;
    }
}
