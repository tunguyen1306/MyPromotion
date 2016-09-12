package com.mypromotion.mypromotion.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mypromotion.mypromotion.Adapter.ExpandableTextView;
import com.mypromotion.mypromotion.Adapter.ImageAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.view.activity.DetailAdvert;
import com.mypromotion.mypromotion.view.activity.ResClient;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 09/11/2016.
 */
public class FraAdvertDetail extends Fragment {
    TextView detail_price,detail_address,detail_advertId;
    ExpandableTextView info;
    private static ViewPager mPager;
    String title_name;
    View view;
    UnderlinePageIndicator indicator;
    private ArrayList<String> ImagesArray = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_advert_detail, container, false);
        Preference.restorePreference(getActivity());
        GetAdvertDetail(ListingDto.IdAdvert);
        ////Create Control
        detail_price=(TextView)view.findViewById(R.id.detail_price);
        detail_address=(TextView)view.findViewById(R.id.detail_address);
        detail_advertId=(TextView)view.findViewById(R.id.detail_advertId);
        info = (ExpandableTextView)view. findViewById(R.id.detail_description);
        indicator = (UnderlinePageIndicator)view.findViewById(R.id.indicator);
        mPager=(ViewPager)view.findViewById(R.id.pagerDetail);

        return view;
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
                        String urlImage=userDtos.get(0).getAdvertImg();
                        if (userDtos.get(0).getAdvertImg()!=null) {
                            String[] listImg=urlImage.split(",");
                            for (int i=0;i<listImg.length;i++) {
                                ImagesArray.add(listImg[i]);
                            }
                        }

                        mPager.setAdapter(new ImageAdapter(getActivity(), ImagesArray));
                        indicator.setViewPager(mPager);
                        indicator.setFades(false);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }

}
