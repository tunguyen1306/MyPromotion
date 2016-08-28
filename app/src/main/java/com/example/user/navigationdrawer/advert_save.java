package com.example.user.navigationdrawer;

import android.app.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by user on 12/31/15.
 */
public class advert_save extends Fragment {

    View view;
    TextView txtAdvert_name;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.advert_save, container, false);
        txtAdvert_name=(TextView)view.findViewById(R.id.txtAdvert_name);
        cardView=(CardView)view.findViewById(R.id.card_view);
        GetAdvert(1);


        return view;
    }

    public void GetAdvert(int pageNum)
    {
        ResClient resClient=new ResClient();
        resClient.getService().GetAdvert(pageNum
                , new Callback<List<ListingDto>>() {
                    @Override
                    public void success(List<ListingDto> userDtos, Response response) {
                        for (int i=0;i<userDtos.size();i++){

                            txtAdvert_name.setText(userDtos.get(i).AdvertName);
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "-------ERROR-------");
                        Log.d("myLogs", Log.getStackTraceString(error));
                    }
                });
    }
}