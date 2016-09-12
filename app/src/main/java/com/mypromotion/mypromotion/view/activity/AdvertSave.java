package com.mypromotion.mypromotion.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.Adapter.AdvertSaveAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertSave extends ActionBarActivity {
    private Toolbar toolbar;
    TextView txtAdvert_name;
    CardView cardView;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_advert_save);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();
        txtAdvert_name=(TextView)findViewById(R.id.txtAdvert_name);
        cardView=(CardView)findViewById(R.id.card_view);
        list=(ListView)findViewById(R.id.lvAdvertSave);
        GetAdvert(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advert_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        tv_title.setText(getResources().getString(R.string.title_activity_advert_save));
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
    public void GetAdvert(int pageNum)
    {
        ResClient resClient=new ResClient();
        resClient.GetService().GetAdvertSave(pageNum
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        list.setAdapter(new AdvertSaveAdapter(getApplicationContext(),userDtos));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
