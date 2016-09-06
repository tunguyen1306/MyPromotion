package com.mypromotion.mypromotion.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.view.fragment.Fragment1;
import com.mypromotion.mypromotion.view.fragment.Fragment2;
import com.mypromotion.mypromotion.view.fragment.Home;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    //control
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DrawerLayout drawer;
    NavigationView navigationView;

    View header;
    TextView tv_header_name,tv_header_email;
    ImageView img_header_user;

    //boolean
    boolean login=false;
    boolean doubleBackToExitPressedOnce = false;

    //string
    private static String PREF_NAME = "pref";
    String UserUrl,UserName,UserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Add Header
        header=navigationView.inflateHeaderView(R.layout.drawer_header);


        Preference.restorePreference(getApplicationContext());
        setupViewPager(viewPager);
        setupTabLayout(tabLayout);
        setupActionBar();

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // setup item left menu
        hideItem();
    }//end Oncreate


    @Override
    public void onPause() {
        super.onPause();
        Preference.savePreference(getApplicationContext());
        hideItem();

    }
    @Override
    public void onResume() {
        super.onResume();
       Preference.restorePreference(getApplicationContext());
        hideItem();
    }
    private void setupTabLayout(TabLayout tabLayout) {
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.greenToolBarBg1));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Home(),getResources().getString(R.string.title_home));
        adapter.addFrag(new Fragment1(),getResources().getString(R.string.title_fragment1));
        adapter.addFrag(new Fragment2(),getResources().getString(R.string.title_fragment2));


        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }
    public void setupActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar, null);
        ImageView logo = (ImageView) mCustomView.findViewById(R.id.img_logo);
        logo.setImageResource(R.drawable.ic_launcher);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_login:
                Intent intent_login=new Intent(getApplicationContext(),Login.class);
                startActivity(intent_login);
                break;
            case R.id.nav_register:
                Intent register=new Intent(getApplicationContext(),Register.class);
                startActivity(register);
                break;
            case R.id.nav_manager_post:
                break;
            case R.id.nav_post:
                break;
            case R.id.nav_share_post:
                break;
            case R.id.nav_logout:
                login=false;
                Preference.savePreference(getApplicationContext());
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_logout), Toast.LENGTH_SHORT).show();
                FacebookSdk.sdkInitialize(MainActivity.this);
                LoginManager.getInstance().logOut();
                onResume();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }



        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText(this, getResources().getString(R.string.msg_exit_app), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    private void hideItem(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if(login) {
            navigationView.getMenu().setGroupVisible(R.id.gr_second, true);
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().setGroupVisible(R.id.gr_first, false);
            tv_header_name.setText(UserName);
            tv_header_email.setText(UserEmail);
            Picasso.with(getApplicationContext()).load(UserUrl).error(R.drawable.ic_image).into(img_header_user);
            //click header
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent setting= new Intent(getApplicationContext(),SettingAccount.class);
                    startActivity(setting);
                }
            });
        }else{
            navigationView.getMenu().setGroupVisible(R.id.gr_second, false);
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
            navigationView.getMenu().setGroupVisible(R.id.gr_first, true);
            tv_header_name.setText("");
            tv_header_email.setText("");
            img_header_user.setImageResource(R.drawable.ic_image);
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_request_login),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}