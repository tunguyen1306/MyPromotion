package com.mypromotion.mypromotion.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.UserDto;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class Register extends ActionBarActivity {

    //photo result code...
    private static final int GALLERY_PHOTO_CODE = 1;

    Bitmap bitmap = null;
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
    ResClient resClient = new ResClient();
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
                UploadFileExecutor executor = new UploadFileExecutor();
                executor.execute(bitmap);
                Toast.makeText(getApplicationContext(), "Chọn ảnh", Toast.LENGTH_SHORT).show();
            }
        });
    }//end onCreate
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //getting the result code...
        if(resultCode == RESULT_OK && requestCode == GALLERY_PHOTO_CODE){
            Uri selectedImage = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                if(bitmap != null){
                    imgRegister_profiler.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();

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

        final UserDto userRegister = new UserDto(0,"","");
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

    private TypedFile getFileTyped(Bitmap image){
        //the image folder with content...
        File folderPath = new File(getCacheDir(),"imageUploadFolder");
        if( !folderPath.exists() ){
            folderPath.mkdir();
        }

        try {
            File file = new File(folderPath, "img-" + System.currentTimeMillis() + ".jpg");

            file.createNewFile();

            FileOutputStream out = null;

            out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 85, out);
            out.flush();

            return new TypedFile("image/jpeg", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ServiceConnect serviceConnect(){
        BaseAplication app = (BaseAplication)this.getApplication();
        return app.serviceConnect();
    }

    private class UploadFileExecutor extends AsyncTask<Bitmap, Void, String> {

        ProgressDialog progressDialog;

        public UploadFileExecutor(){
            progressDialog = new ProgressDialog(Register.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //processing the progress dialog...
//            progressDialog.setMessage(getString(R.string.uploading));
//            progressDialog.setIndeterminate(true);
//            progressDialog.show();
        }

        @Override
        protected String doInBackground(Bitmap... params) {
            //get and process the bitmap...
            Bitmap bitmap = params[0]; //check for null pointer exceptions here
            TypedFile typedFile = getFileTyped(bitmap);

            //sending values with String..
            Date date = new Date();
            String sampleSender = date.toString();

            //doing the actual sending...
            if(typedFile != null){
                try {
                    String result = serviceConnect().postValues(sampleSender, typedFile);
                    return result;
                }catch (Exception ex){

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(progressDialog.isShowing())
                progressDialog.dismiss();

            //check if result is not null, and send it..
            if(s == null){
                Toast.makeText(Register.this, "Lỗi", Toast.LENGTH_LONG).show();
                return;
            }

            //otherwise show content...
            Toast.makeText(Register.this, s, Toast.LENGTH_LONG).show();

        }
    }


}
