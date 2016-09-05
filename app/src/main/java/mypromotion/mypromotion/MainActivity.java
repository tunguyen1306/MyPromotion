package mypromotion.mypromotion;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import mypromotion.mypromotion.ADapter.PageADapter;
import mypromotion.mypromotion.FragmentView.tabClothes;
import mypromotion.mypromotion.FragmentView.tabHome;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        setupTabLayout(tabLayout);
        setupViewPager(viewPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    //method setupTabLayout

    private void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
    }

    // method setupViewPager

    private void setupViewPager(ViewPager viewPager) {
        PageADapter adapter = new PageADapter(getSupportFragmentManager());
        adapter.addFrag(new tabHome(), "Home");
        adapter.addFrag(new tabClothes(), "Túi xách");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2); //num fragment load when startActivity
    }


}
