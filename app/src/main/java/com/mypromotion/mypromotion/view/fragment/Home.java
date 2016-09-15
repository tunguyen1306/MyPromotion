package com.mypromotion.mypromotion.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mypromotion.mypromotion.Adapter.AdvertViewedAdapter;
import com.mypromotion.mypromotion.Adapter.ProjectFeaturedAdapter;
import com.mypromotion.mypromotion.Adapter.SlideAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.BrandDto;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.SlideDto;
import com.mypromotion.mypromotion.model.UserDto;
import com.mypromotion.mypromotion.view.activity.ResClient;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by DucTin on 2/9/2016.
 */
public class Home extends Fragment {

    ViewPager pager_banner;
    InkPageIndicator indicator_banel;
    View view;
    /////feauture////////////////
    TwoWayView lv_advert_feauture;
    List<BrandDto> ItemAdvertFeauture;
    List<Integer> listIdBrandFeauture = new ArrayList<>();
    List<String> listImgBrandFeauture = new ArrayList<>();
    List<String> listNameBrandFeauture = new ArrayList<>();
    List<Integer> listPercentBrandFeauture = new ArrayList<>();
    //////////End feauture////////
    /////AdvertViewed////////////////
    TwoWayView lv_advert_Viewed;
    List<ListingDto> ItemAdvertViewed;
    List<Integer> listIdAdvertViewed = new ArrayList<>();
    List<String> listImgAdvertViewed = new ArrayList<>();
    List<String> listNameAdvertViewed = new ArrayList<>();
    List<String> listPriceAdvertViewed = new ArrayList<>();
    //////////End AdvertViewed////////
    ////Slide///////
    List<SlideDto> ItemSlide;
    List<String> list_id_slide = new ArrayList<>();
    List<String> list_img_slide = new ArrayList<>();
    List<String> list_title_slide = new ArrayList<>();
    List<String> list_des_slide = new ArrayList<>();

    ////End Slide///
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        lv_advert_feauture = (TwoWayView) view.findViewById(R.id.lv_advert_feauture);
        indicator_banel=(InkPageIndicator)view.findViewById(R.id.indicator_slide);
        pager_banner=(ViewPager)  view.findViewById(R.id.pager_banner);

        ////Viewed
        lv_advert_Viewed = (TwoWayView) view.findViewById(R.id.lv_advert_viewed);
        ////End Viewed

        callServiceAdvertFeauture();
        callServiceSlide();
        callServiceAdvertViewed();
        return view;
    }
    ///LoadSlide///////
    private void loadSilde(){

        ItemSlide = getDataSlide();
        try {
            for (int i = 0; i < ItemSlide.size(); i++) {

                pager_banner.setAdapter(new SlideAdapter(getActivity(), ItemSlide));

                SlideAdapter slideAdapter =new SlideAdapter(getActivity(), ItemSlide);
                pager_banner.setAdapter(slideAdapter);
                indicator_banel.setViewPager(pager_banner);
            }
        }
        catch (Exception ex) {

        }
    }
    private List<SlideDto>getDataSlide(){
        List<SlideDto> items = new ArrayList<>();
        for (int i=0;i<list_id_slide.size();i++) {
            items.add(new SlideDto(list_id_slide.get(i),list_img_slide.get(i),list_title_slide.get(i),list_des_slide.get(i)));
        }
        return items;
    }
    public void callServiceSlide() {
        ResClient restClient = new ResClient();
        restClient.GetService().GetSlide(
                new Callback<List<SlideDto>>() {
                    @Override
                    public void success(List<SlideDto> slideDtos, Response response) {
                        for (int i = 0; i < slideDtos.size(); i++) {

                            list_id_slide.add(slideDtos.get(i).id_slide);
                            list_img_slide.add(slideDtos.get(i).img_slide);
                            list_des_slide.add(slideDtos.get(i).des_slide);
                            list_title_slide.add(slideDtos.get(i).title_slide);
                        }
                        loadSilde();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    ///End LoadSlide///

    ///LoadAdvertFeauture///
    private void loadDataAdvertFeauture() {

        ItemAdvertFeauture = getAllItemsAdvertFeauture();

        try {

            ProjectFeaturedAdapter adapter = new ProjectFeaturedAdapter(getActivity(), ItemAdvertFeauture, "project");
            lv_advert_feauture.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<BrandDto> getAllItemsAdvertFeauture() {
        List<BrandDto> items = new ArrayList<>();
        for (int i = 0; i < listIdBrandFeauture.size(); i++) {
            items.add(
                    new BrandDto(
                            listIdBrandFeauture.get(i),
                            listNameBrandFeauture.get(i),
                            listImgBrandFeauture.get(i),
                            listPercentBrandFeauture.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAdvertFeauture() {
        ResClient restClient = new ResClient();
        restClient.GetService().GetBrand(new Callback<List<BrandDto>>() {
            @Override
            public void success(List<BrandDto> userDtos, Response response) {
                for (int i = 0; i < userDtos.size(); i++) {

                    listIdBrandFeauture.add(userDtos.get(i).id_brand_promotiom);
                    listNameBrandFeauture.add(userDtos.get(i).name_brand_promotiom);
                    listPercentBrandFeauture.add(userDtos.get(i).percent_brand_promotiom);
                    listImgBrandFeauture.add(userDtos.get(i).img_brand_promotiom);
                }
                loadDataAdvertFeauture();
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "-------ERROR-------Slide");
                Log.d("myLogs", Log.getStackTraceString(error));
            }
        });
    }
    ///End LoadAdvertFeauture///

    ///LoadAdvertViewed///
    private void loadDataAdvertViewed() {

        ItemAdvertViewed = getAllItemsAdvertViewed();

        try {

            AdvertViewedAdapter adapter = new AdvertViewedAdapter(getActivity(), ItemAdvertViewed, "Viewed");
            lv_advert_Viewed.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }
    private List<ListingDto> getAllItemsAdvertViewed() {
        List<ListingDto> items = new ArrayList<>();
        for (int i = 0; i < listIdAdvertViewed.size(); i++) {
            items.add(
                    new ListingDto(
                            listIdAdvertViewed.get(i),
                            listNameAdvertViewed.get(i),
                            listImgAdvertViewed.get(i),
                            listPriceAdvertViewed.get(i)
                    )
            );
        }
        return items;
    }
    public void callServiceAdvertViewed() {
        ResClient restClient = new ResClient();
        restClient.GetService().GetAdvertSave(1
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        for (int i = 0; i < userDtos.size(); i++) {

                            listIdAdvertViewed.add(userDtos.get(i).AdvertId);
                            listNameAdvertViewed.add(userDtos.get(i).AdvertName);
                            listPriceAdvertViewed.add(userDtos.get(i).AdvertPrice);
                            listImgAdvertViewed.add(userDtos.get(i).AdvertImg);
                        }
                        loadDataAdvertViewed();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------Slide");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
    ///End LoadAdvertViewed///


}
