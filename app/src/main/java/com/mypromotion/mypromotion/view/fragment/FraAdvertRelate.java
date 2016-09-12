package com.mypromotion.mypromotion.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mypromotion.mypromotion.R;

/**
 * Created by TuNguyen on 09/11/2016.
 */
public class FraAdvertRelate  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         container = (ViewGroup) inflater.inflate(
                R.layout.fragment_advert_relate, container, false);
        return container;
    }
}