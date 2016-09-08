package com.mypromotion.mypromotion.view.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.model.UserDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends ActionBarActivity {
    private CallbackManager callbackManager;
    LoginButton loginButton;
    EditText editEmail, editPass;
    Button btnLogin;
    //control
    private Toolbar toolbar;
    private FacebookCallback<LoginResult> loginResultFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                String id = object.getString("id");
                                String full_name = object.getString("name");
                                String Email = object.getString("email");
                                String gender = object.getString("gender");

                                String imgUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
                                UserDto.login = true;
                                UserDto.UserName = full_name;
                                UserDto.UserUrl = imgUrl;
                                UserDto.UserEmail = Email;
//                                UserDto.U = gender;
                                UserDto.FacebookId = id;
                                Preference.savePreference(getApplicationContext());
                                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                                UserRegister userRegister =new UserRegister();
//                                userRegister.EventRegister(Email,"","","",2,1,"",imgUrl,full_name);
//                                Intent login= new Intent(getActivity(),MainActivity.class);
//                                startActivity(login);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,gender,email,birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editEmail = (EditText) findViewById(R.id.editEmailLogin);
        editPass = (EditText) findViewById(R.id.editPassWordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        //clear focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail=null,txtPass=null;
                txtEmail=editEmail.getText().toString();
                txtPass=editPass.getText().toString();
                GetLogin(txtEmail,txtPass);
            }
        });

    }

    public void GetLogin(String txtEmail, String txtPass){

    ResClient resClient =new ResClient();
    resClient.GetService().GetLogin(txtEmail, txtPass, new Callback<List<UserDto>>() {
        @Override
        public void success(List<UserDto> userDtos, Response response) {
            if (userDtos.get(0).IDout==1){
                UserDto.UserEmail=userDtos.get(0).email_user_promotion;
                UserDto.UserName=userDtos.get(0).full_name_user_promotion;
                UserDto.UserUrl=userDtos.get(0).img_user_promotion;
                UserDto.login=true;
                Preference.savePreference(getApplicationContext());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show();
                finish();
            }
            if (userDtos.get(0).IDout==0){
                UserDto.UserEmail=userDtos.get(0).email_user_promotion;
                UserDto.UserName=userDtos.get(0).full_name_user_promotion;
                UserDto.UserUrl=userDtos.get(0).img_user_promotion;
                UserDto.login=false;
                Preference.savePreference(getApplicationContext());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_login_error), Toast.LENGTH_SHORT).show();

            }
            if (userDtos.get(0).IDout==-1){
                UserDto.UserEmail=userDtos.get(0).email_user_promotion;
                UserDto.UserName=userDtos.get(0).full_name_user_promotion;
                UserDto.UserUrl=userDtos.get(0).img_user_promotion;
                UserDto.login=false;
                Preference.savePreference(getApplicationContext());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_login_deactive), Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void failure(RetrofitError error) {

        }
    });

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
}
