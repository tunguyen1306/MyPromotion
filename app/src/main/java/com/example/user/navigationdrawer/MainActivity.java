package com.example.user.navigationdrawer;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        if (User.Login){
        TextView txtshow=(TextView)header.findViewById(R.id.txtShowName);
        txtshow.setText(User.FullName);
            TextView txtshowEmail=(TextView)header.findViewById(R.id.txtShowEmail);
            txtshowEmail.setText(User.email);
        img=(ImageView)header.findViewById(R.id.imageView);
        Picasso.with(getApplicationContext()).load(User.UrlImage).into(img);
            hideItem();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        User.RestorReferen(getApplicationContext());

    }

    @Override
    protected void onPause() {
        super.onPause();
        User.savePreferen(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
//        if (id == R.id.nav_login) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment())
//                 .commit();
//
//
//     }
//        Intent login= new Intent(getApplicationContext(),Login.class);
//        startActivity(login);


        switch (id){
            case R.id.nav_login:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
                break;
            case R.id.nav_register:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Register()).commit();
                break;
            case R.id.nav_logout:
                User.Login=false;
                User.savePreferen(getApplicationContext());
                Toast.makeText(getApplicationContext(),"Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
                FacebookSdk.sdkInitialize(MainActivity.this);
                LoginManager.getInstance().logOut();
                onResume();
                Intent login= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(login);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideItem()
    {
        NavigationView  navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
        nav_Menu.findItem(R.id.nav_register).setVisible(false);
    }

}
