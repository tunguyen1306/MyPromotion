package com.mypromotion.mypromotion.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.model.UserDto;
import com.mypromotion.mypromotion.model.data_list;
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

    int PICK_IMAGE_REQUEST = 1;
    int CAMERA_REQUEST = 0;
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
    ImageView imgRegister_profiler;
    ResClient resClient = new ResClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //clear focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();
        editRegisterEmail = (EditText) findViewById(R.id.editRegisterEmail);
        editRegisterPassWord = (EditText) findViewById(R.id.editRegisterPassWord);
        editRegisterPassWordCon = (EditText) findViewById(R.id.editRegisterPassWordCon);
        editRegisterPhone = (EditText) findViewById(R.id.editRegisterPhone);
        editRegisterFist = (EditText) findViewById(R.id.editRegisterFist);
        editRegisterLast = (EditText) findViewById(R.id.editRegisterLast);
        editRegisterFullName = (EditText) findViewById(R.id.editRegisterFullName);
        imgRegister_profiler = (ImageView) findViewById(R.id.imgRegister_profiler);

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
                imgurl = UserDto.UserUrl;
                EventRegister(Email, Phone, firstName, lastName, 1, 1, passWord, imgurl, fullName);

            }
        });

        imgRegister_profiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowSelect();
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
        Preference.restorePreference(getApplicationContext());
        final UserDto userRegister = new UserDto(0, "", "");
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

    //////////////////Upload Image//////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(getApplicationContext(), photo);

            TypedFile photoTypedFile = new TypedFile("image/*", new File(getRealPathFromURI(uri)));
            uploadFile(photoTypedFile);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                TypedFile photoTypedFile = new TypedFile("image/*", new File(getRealPathFromURI(uri)));
                uploadFile(photoTypedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void ShowSelect() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Register.this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                Register.this,
                android.R.layout.simple_list_item_1);
        arrayAdapter.add("Dùng máy ảnh");
        arrayAdapter.add("Thư viện ảnh");

        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                Register.this);
                        if (strName.contentEquals("Dùng máy ảnh")) {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        } else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                        }

                    }
                });
        builderSingle.show();
    }

    public String getRealPathFromURI(Uri contentUri) {

        // can post image
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG,100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void uploadFile(TypedFile typedFile) {
        ResClient restClient1 = new ResClient();
        restClient1.GetService().UploadFile(typedFile,
                new Callback<data_list>() {
                    @Override
                    public void success(data_list data_list, Response response) {
                        UserDto.UserUrl = data_list.Message;
                        Picasso.with(getApplicationContext()).load(UserDto.UserUrl).error(R.drawable.ic_image).into(imgRegister_profiler);
                        Preference.savePreference(getApplicationContext());
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }
    //////////////////End Upload Image//////////////////////////
}
