package com.mypromotion.mypromotion.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mypromotion.mypromotion.Adapter.AdvertSaveAdapter;
import com.mypromotion.mypromotion.Adapter.BrandRelateAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.BrandDto;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.view.activity.ResClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 09/17/2016.
 */
public class FraBrandRelate extends Fragment {
    CardView cardView;
    ListView list;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_brand_relate, container, false);
        cardView=(CardView)view.findViewById(R.id.card_view);
        list=(ListView)view.findViewById(R.id.lvBrandRelate);
        GetBrandRelate(1);
        return view;
    }
    public void GetBrandRelate(int idCategory)    {
        ResClient resClient=new ResClient();
        resClient.GetService().GetBrandByCategoryId(idCategory
                , new Callback<List<BrandDto>>() {
                    @Override
                    public void success(List<BrandDto> userDtos, Response response) {
                        list.setAdapter(new BrandRelateAdapter(getActivity(),userDtos));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
