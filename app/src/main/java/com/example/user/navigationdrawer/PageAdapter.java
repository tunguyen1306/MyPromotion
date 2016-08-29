package com.example.user.navigationdrawer;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.navigationdrawer.Category.tabClothes;
import com.example.user.navigationdrawer.Category.tabHome;


/**
 * Created by TuNguyen on 08/29/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    public PageAdapter(FragmentManager fm,int _tabCount) {
        super(fm);
        this.tabCount=_tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                tabHome tabHome= new tabHome();
                return tabHome;
            case 1:
                tabClothes tabClothes= new tabClothes();
                return tabClothes;
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
