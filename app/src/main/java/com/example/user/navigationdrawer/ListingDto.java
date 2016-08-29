package com.example.user.navigationdrawer;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TuNguyen on 08/28/2016.
 */
public class ListingDto {
    public int getAdvertId() {
        return AdvertId;
    }

    public void setAdvertId(int advertId) {
        AdvertId = advertId;
    }

    public String getAdvertName() {
        return AdvertName;
    }

    public void setAdvertName(String advertName) {
        AdvertName = advertName;
    }

    public String getAdvertDescription() {
        return AdvertDescription;
    }

    public void setAdvertDescription(String advertDescription) {
        AdvertDescription = advertDescription;
    }

    public String getAdvertCreatedDate() {
        return AdvertCreatedDate;
    }

    public void setAdvertCreatedDate(String advertCreatedDate) {
        AdvertCreatedDate = advertCreatedDate;
    }

    public String getAdvertExpiresDate() {
        return AdvertExpiresDate;
    }

    public void setAdvertExpiresDate(String advertExpiresDate) {
        AdvertExpiresDate = advertExpiresDate;
    }

    public String getAdvertModifiDate() {
        return AdvertModifiDate;
    }

    public void setAdvertModifiDate(String advertModifiDate) {
        AdvertModifiDate = advertModifiDate;
    }

    public String getAdvertPostedDate() {
        return AdvertPostedDate;
    }

    public void setAdvertPostedDate(String advertPostedDate) {
        AdvertPostedDate = advertPostedDate;
    }

    public String getAdvertStreet() {
        return AdvertStreet;
    }

    public void setAdvertStreet(String advertStreet) {
        AdvertStreet = advertStreet;
    }

    public String getAdvertCity() {
        return AdvertCity;
    }

    public void setAdvertCity(String advertCity) {
        AdvertCity = advertCity;
    }

    public String getAdvertDistrict() {
        return AdvertDistrict;
    }

    public void setAdvertDistrict(String advertDistrict) {
        AdvertDistrict = advertDistrict;
    }

    public String getAdvertWard() {
        return AdvertWard;
    }

    public void setAdvertWard(String advertWard) {
        AdvertWard = advertWard;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getAdvertImg() {
        return AdvertImg;
    }

    public void setAdvertImg(String advertImg) {
        AdvertImg = advertImg;
    }

    public String getAdvertPrice() {
        return AdvertPrice;
    }
    public void setAdvertPrice(String advertPrice) {
        AdvertPrice = advertPrice;
    }

    public int AdvertId;
    public String AdvertName;
    public String AdvertDescription;
    public String AdvertCreatedDate;
    public String AdvertExpiresDate;
    public String AdvertModifiDate;
    public String AdvertPostedDate;
    public String AdvertStreet;
    public String AdvertCity;
    public String AdvertDistrict;
    public String AdvertWard;
    public String CategoryName;
    public String AdvertImg;


    public String AdvertPrice;
//    public void GetAdvert(int pageNum)
//    {
//        ResClient resClient=new ResClient();
//        resClient.getService().GetAdvert(pageNum
//                , new Callback<List<ListingDto>>() {
//                    @Override
//                    public void success(List<ListingDto> userDtos, Response response) {
//                        for (int i=0;i<userDtos.size();i++){
//                            AdvertName=userDtos.get(i).AdvertDescription;
//                        }
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("myLogs", "-------ERROR-------");
//                        Log.d("myLogs", Log.getStackTraceString(error));
//                    }
//                });
//    }

}
