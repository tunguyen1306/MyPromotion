package com.example.user.navigationdrawer;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.internal.GetServiceRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Provider;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by user on 12/31/15.
 */
public class LoginFragment extends Fragment{
    private static String PREF_NAME = "pref";
    View view;
    private CallbackManager callbackManager;
    LoginButton loginButton;
    Button btnLogin;
    EditText editUserNameLogin;
    EditText editUserNamePass;
    String edUserName,edPassWord;
    TextView txt_login_success;
    //Login Facebook
    private FacebookCallback<LoginResult> loginResultFacebookCallback=new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback(){
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response){
                            try
                            {
                                String id = object.getString("id");
                             String   Username = object.getString("name");
                                String   Email = object.getString("email");
                                   String gender = object.getString("gender");
                                String imgUrl="https://graph.facebook.com/" + id + "/picture?type=large";

                                User.Login=true;
                                User.FullName=Username;
                                User.UrlImage = imgUrl;
                                User.email=Email;
                                User.gender=gender;
                                User.facebookID=id;
                                User.savePreferen(getActivity());
                                FragmentManager fragmentManager = getFragmentManager();

                 fragmentManager.beginTransaction().replace(R.id.content_frame, new Register())
                 .commit();



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
    //End Login Facebook
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.nav_login,container,false);
         loginButton =(LoginButton)view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("email");
        btnLogin=(Button)view.findViewById(R.id.btnLogin);
        editUserNameLogin=(EditText)view.findViewById(R.id.editUserNameLogin) ;
        editUserNamePass=(EditText)view.findViewById(R.id.editPassWordLogin) ;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               edUserName=editUserNameLogin.getText().toString();
                edPassWord=editUserNamePass.getText().toString();
                MTGetLogin();

            }
        });

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton =(LoginButton)view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager,loginResultFacebookCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode,data);
    }
    public void MTGetLogin(){
        ResClient resClient=new ResClient();
        txt_login_success=(TextView)view.findViewById(R.id.login_success);
        resClient.getService().GetLogin(edUserName, edPassWord, new Callback<List<UserDto>>() {
            @Override
            public void success(List<UserDto> strings, Response response) {
                for (int i=0;i <strings.size();i++)
                {
                    txt_login_success.setText(strings.get(i).getEmail());

                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }


}