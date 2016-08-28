package com.example.user.navigationdrawer.Business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.navigationdrawer.ListingDto;

import java.util.List;

/**
 * Created by TuNguyen on 08/29/2016.
 */
public class customAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _cContext;
    List<ListingDto> listingDtos;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
