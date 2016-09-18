package com.mypromotion.mypromotion.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mypromotion.mypromotion.Adapter.AdvertInBrandAdapter;
import com.mypromotion.mypromotion.Adapter.AdvertViewedAdapter;
import com.mypromotion.mypromotion.Adapter.ExpandableTextView;
import com.mypromotion.mypromotion.Adapter.ImageAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.BrandDto;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.view.activity.DetailAdvert;
import com.mypromotion.mypromotion.view.activity.ResClient;
import com.viewpagerindicator.UnderlinePageIndicator;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by TuNguyen on 09/17/2016.
 */
public class FraBrandDetail extends Fragment {
    TextView detailPercent,detailBrandAddress,detailBrandId;
    ExpandableTextView detailBrandDesShort;
    private static ViewPager mPager;
    String title_name;
    View view;
    UnderlinePageIndicator indicator;
    private ArrayList<String> ImagesArray = new ArrayList<String>();
    /////AdvertViewed////////////////
    TwoWayView lvAdvertInBrand;
    List<ListingDto> ItemAdvertInBrand;
    List<Integer> listIdAdvertInBrand = new ArrayList<>();
    List<String> listImgAdvertInBrand= new ArrayList<>();
    List<String> listNameAdvertInBrand = new ArrayList<>();
    List<String> listPriceAdvertInBrand= new ArrayList<>();
    //////////End AdvertViewed////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_brand_detail, container, false);
        Preference.restorePreference(getActivity());
        GetBrandById(BrandDto.idBrandPromotiom);

        ////Create Control
        detailPercent=(TextView)view.findViewById(R.id.detail_price);
        detailBrandAddress=(TextView)view.findViewById(R.id.detail_address);
        detailBrandId=(TextView)view.findViewById(R.id.detail_advertId);
        detailBrandDesShort = (ExpandableTextView)view. findViewById(R.id.detail_description);
        indicator = (UnderlinePageIndicator)view.findViewById(R.id.indicator);
        mPager=(ViewPager)view.findViewById(R.id.pagerDetail);
        ////lvAdvertInBrand

        lvAdvertInBrand = (TwoWayView) view.findViewById(R.id.lv_advert_in_brand);
        ////End lvAdvertInBrand
        callServiceAdvertInBrand(BrandDto.idBrandPromotiom);
        return view;
    }
    public void GetBrandById(final int idBrand)    {
        ResClient resClient=new ResClient();
        resClient.GetService().GetBrandById(idBrand
                , new Callback<List<BrandDto>>() {
                    @Override
                    public void success(List<BrandDto> brandDtos, Response response) {
                        //detail_price.setText(brandDtos.get(0).AdvertPrice.toString());
                        detailBrandAddress.setText(brandDtos.get(0).address_brand_promotiom);
                        detailBrandDesShort.setText(brandDtos.get(0).short_des_brand_promotion);
                        String urlImage=brandDtos.get(0).getImg_brand_promotiom();
                        if (brandDtos.get(0).getImg_brand_promotiom()!=null) {
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
    ///LoadAdvertInBrand///
    private void loadDataAdvertInBrand() {

        ItemAdvertInBrand = getAllItemsAdvertInBrand();

        try {

            AdvertInBrandAdapter adapter = new AdvertInBrandAdapter(getActivity(), ItemAdvertInBrand, "Brand");
            lvAdvertInBrand.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<ListingDto> getAllItemsAdvertInBrand() {
        List<ListingDto> items = new ArrayList<>();
        for (int i = 0; i < listIdAdvertInBrand.size(); i++) {
            items.add(
                    new ListingDto(
                            listIdAdvertInBrand.get(i),
                            listNameAdvertInBrand.get(i),
                            listImgAdvertInBrand.get(i),
                            listPriceAdvertInBrand.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAdvertInBrand(final int idBrand) {
        ResClient restClient = new ResClient();
        restClient.GetService().GetAdvertByBrand(idBrand
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        for (int i = 0; i < userDtos.size(); i++) {

                            listIdAdvertInBrand.add(userDtos.get(i).AdvertId);
                            listNameAdvertInBrand.add(userDtos.get(i).AdvertName);
                            listPriceAdvertInBrand.add(userDtos.get(i).AdvertPrice);
                            listImgAdvertInBrand.add(userDtos.get(i).AdvertImg);
                        }
                        loadDataAdvertInBrand();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------Slide");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    ///End LoadAdvertInBrand///
}
