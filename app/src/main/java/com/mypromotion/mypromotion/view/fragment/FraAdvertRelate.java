package com.mypromotion.mypromotion.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.mypromotion.mypromotion.Adapter.AdvertRelateAdapter;
import com.mypromotion.mypromotion.Adapter.BrandRelateAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.BrandDto;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.view.activity.ResClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 09/11/2016.
 */
public class FraAdvertRelate  extends Fragment {
    CardView cardView;
    ListView list;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advert_relate, container, false);
        cardView=(CardView)view.findViewById(R.id.cardViewAdvertRelate);
        list=(ListView)view.findViewById(R.id.lvAdverRelate);
        Preference.restorePreference(getActivity());
        GetAdvertRelate(ListingDto.IdAdvert,ListingDto.IdAdvertCategory,1,ListingDto.IdAdvertBrand);
        return view;
    }
    public void GetAdvertRelate(int idAdvert ,int idCategory,int pageNum ,int IdBrand )    {
        ResClient resClient=new ResClient();
        resClient.GetService().GetAdvertRelate(idAdvert ,idCategory,pageNum,IdBrand
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        list.setAdapter(new AdvertRelateAdapter(getActivity(),userDtos));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}