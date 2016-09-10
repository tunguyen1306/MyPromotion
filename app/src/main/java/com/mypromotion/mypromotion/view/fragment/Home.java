package com.mypromotion.mypromotion.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mypromotion.mypromotion.Adapter.ProjectFeaturedAdapter;
import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.UserDto;
import com.mypromotion.mypromotion.view.activity.ResClient;

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
    TwoWayView lv_advert_feauture;
    List<ListingDto> ItemAdvertFeauture;
    List<Integer> listIdAdvertFeauture = new ArrayList<>();
    List<String> listImgAdvertFeauture = new ArrayList<>();
    List<String> listNameAdvertFeauture = new ArrayList<>();
    List<String> listPriceAdvertFeauture = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lv_advert_feauture = (TwoWayView) view.findViewById(R.id.lv_advert_feauture);
        callServiceAdvertFeauture();
        return view;
    }

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

    public void callServiceAdvertFeauture() {
        ResClient restClient = new ResClient();
        restClient.GetService().GetAdvert(1
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        for (int i = 0; i < userDtos.size(); i++) {
                            int t = userDtos.get(i).AdvertId;
                            listIdAdvertFeauture.add(userDtos.get(i).AdvertId);
                            listNameAdvertFeauture.add(userDtos.get(i).AdvertName);
                            listPriceAdvertFeauture.add(userDtos.get(i).AdvertPrice);
                            listImgAdvertFeauture.add(userDtos.get(i).AdvertImg);
                        }
                        loadDataAdvertFeauture();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}
