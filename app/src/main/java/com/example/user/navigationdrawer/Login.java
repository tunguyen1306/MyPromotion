package com.example.user.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private static String PREF_NAME = "pref";
    View view;
    private CallbackManager callbackManager;
    LoginButton loginButton;

    private FacebookCallback<LoginResult> loginResultFacebookCallback=new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken=loginResult.getAccessToken();
            Profile profile=Profile.getCurrentProfile();
            if (profile!=null)
            {

            }


            String userId = loginResult.getAccessToken().getUserId();

            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback(){
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response){
                            try
                            {
                                String id = object.getString("id");
                                String   full_name = object.getString("name");
                                String   Email = object.getString("email");
                                String gender = object.getString("gender");
                                String imgUrl="https://graph.facebook.com/" + id + "/picture?type=large";
                                User user=new User();
                                User.Login=1;
                                User.full_name=full_name;
                                User.UrlImage = imgUrl;

                                savePreference(getApplicationContext());
                                finish();
                            }
                            catch (JSONException e)
                            {
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
        FacebookSdk.sdkInitialize(Login.this);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        loginButton =(LoginButton)findViewById(R.id.login_button);
        callbackManager=CallbackManager.Factory.create();
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                savePreference(getApplicationContext());
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback(){
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response){
                                try
                                {
                                    String id = object.getString("id");
                                    String   full_name = object.getString("name");
                                    String   Email = object.getString("email");
                                    String gender = object.getString("gender");
                                    String imgUrl="https://graph.facebook.com/" + id + "/picture?type=large";
                                    User user=new User();
                                    user.Login=1;
                                    User.full_name=full_name;
                                    user.UrlImage = imgUrl;
                                    savePreference(getApplicationContext());
                                    finish();
                                }
                                catch (JSONException e)
                                {
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
                Toast.makeText(getApplicationContext(),"cancle",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

            }
        });



    }//end onCreate



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private SharedPreferences getPreferent(Context context)
    {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    private void  savePreference(Context context)
    {
        SharedPreferences.Editor edit=getPreferent(context).edit();
        if (User.Login==1)
        {
            edit.putInt("Login",User.Login);
            edit.putString("full_name",User.full_name);
            edit.putString("UrlImage",User.UrlImage);
        }else  {
            edit.putInt("Login",0);
            edit.putString("full_name","");
            edit.putString("UrlImage","");
        }
        edit.clear();
        edit.commit();
    }
    private  void RestorReference(Context context)
    {

        User.full_name=getPreferent(context).getString("full_name","");
        User.UrlImage=getPreferent(context).getString("UrlImage","");
    }


}
