package com.mypromotion.mypromotion.view.activity;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mypromotion.mypromotion.Adapter.ExpandableTextView;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.Preference;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
//import com.viewpagerindicator.UnderlinePageIndicator;
/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertDetail extends ActionBarActivity {
    private Toolbar toolbar;
    TextView detail_price,detail_address,detail_advertId;
    ExpandableTextView info;
    String title_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpActionBar();
        Preference.restorePreference(getApplicationContext());
        GetAdvertDetail(ListingDto.IdAdvert);
        ////Create Control
        detail_price=(TextView)findViewById(R.id.detail_price);
        detail_address=(TextView)findViewById(R.id.detail_address);
        detail_advertId=(TextView)findViewById(R.id.detail_advertId);
        info = (ExpandableTextView) findViewById(R.id.detail_description);
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
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Preference.restorePreference(getApplicationContext());
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_title, null);
        LinearLayout ln_back = (LinearLayout) mCustomView.findViewById(R.id.ln_back);
        //TextView tv_title = (TextView) mCustomView.findViewById(R.id.tv_title);
        TextView tv_title_name = (TextView) mCustomView.findViewById(R.id.tv_title_name);
       // tv_title.setText(getResources().getString(R.string.title_activity_advert_deatil));
        tv_title_name.setText(ListingDto.NameAdvert);
        ln_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
    public void GetAdvertDetail(final int idAvert)
    {
        ResClient resClient=new ResClient();
        resClient.GetService().GetAdvertDetail(idAvert
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        detail_price.setText(userDtos.get(0).AdvertPrice.toString());
                        detail_address.setText(userDtos.get(0).AdvertStreet);
                        info.setText(userDtos.get(0).AdvertDescription);
                        //detail_advertId.setText(userDtos.get(0).AdvertId);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
