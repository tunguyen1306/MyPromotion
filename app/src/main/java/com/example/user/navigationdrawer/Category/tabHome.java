package com.example.user.navigationdrawer.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.navigationdrawer.R;
import android.support.v4.app.Fragment;

/**
 * Created by TuNguyen on 08/30/2016.
 */
public class tabHome extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_home,container,false);
    }
}
