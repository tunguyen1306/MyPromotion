package com.mypromotion.mypromotion.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mypromotion.mypromotion.Adapter.ProjectFeaturedAdapter;
import com.mypromotion.mypromotion.Adapter.SlideAdapter;
import com.mypromotion.mypromotion.R;
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
    List<ListingDto> ItemAdvertFeauture;
    List<Integer> listIdAdvertFeauture = new ArrayList<>();
    List<String> listImgAdvertFeauture = new ArrayList<>();
    List<String> listNameAdvertFeauture = new ArrayList<>();
    List<String> listPriceAdvertFeauture = new ArrayList<>();
    //////////End feauture////////

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
        callServiceAdvertFeauture();
        callServiceSlide();
        return view;
    }
    ///LoadSlide///////
    private void loadSilde(){

        ItemSlide = getDataSlide();
        try {
            for (int i = 0; i < ItemSlide.size(); i++) {
<<<<<<< HEAD

                pager_banner.setAdapter(new SlideAdapter(getActivity(), ItemSlide));
=======
                SlideAdapter slideAdapter =new SlideAdapter(getActivity(), ItemSlide);
                pager_banner.setAdapter(slideAdapter);
>>>>>>> 62618b9327129a7b21623605fa4b87c7d8b4e69f
                indicator_banel.setViewPager(pager_banner);
            }
        }
        catch (Exception ex) {

        }
    }
    ///End LoadSlide///
    private void loadDataAdvertFeauture() {

        ItemAdvertFeauture = getAllItemsAdvertFeauture();

        try {

            ProjectFeaturedAdapter adapter = new ProjectFeaturedAdapter(getActivity(), ItemAdvertFeauture, "project");
            lv_advert_feauture.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }


    private List<ListingDto> getAllItemsAdvertFeauture() {
        List<ListingDto> items = new ArrayList<>();
        for (int i = 0; i < listIdAdvertFeauture.size(); i++) {
            items.add(
                    new ListingDto(
                            listIdAdvertFeauture.get(i),
                            listNameAdvertFeauture.get(i),
                            listImgAdvertFeauture.get(i),
                            listPriceAdvertFeauture.get(i)
                    )
            );
        }
        return items;
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

    public void callServiceAdvertFeauture() {
        ResClient restClient = new ResClient();
        restClient.GetService().GetAdvertSave(1
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        for (int i = 0; i < userDtos.size(); i++) {

                            listIdAdvertFeauture.add(userDtos.get(i).AdvertId);
                            listNameAdvertFeauture.add(userDtos.get(i).AdvertName);
                            listPriceAdvertFeauture.add(userDtos.get(i).AdvertPrice);
                            listImgAdvertFeauture.add(userDtos.get(i).AdvertImg);
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
}
