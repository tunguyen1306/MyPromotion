package com.example.user.navigationdrawer.Business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.navigationdrawer.ListingDto;
import com.example.user.navigationdrawer.R;
import com.example.user.navigationdrawer.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 08/29/2016.
 */
public class customAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<ListingDto> listingDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public customAdapter(Context context, List<ListingDto> advertSaveListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listingDtos = advertSaveListView;

    }
    @Override
    public int getCount() {
        return listingDtos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.advert_save,parent,false);
            listViewHolder.txt_tile=(TextView)convertView.findViewById(R.id.txtAdvert_name);
            listViewHolder.img_advert_save=(ImageView) convertView.findViewById(R.id.img_advert_save);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.txt_tile.setText(listingDtos.get(position).AdvertName);
        Picasso.with(_Context).load(listingDtos.get(position).AdvertImg).into(listViewHolder.img_advert_save);

        return convertView;
    }
    public  class  ViewHolder{
        TextView txt_tile;
        ImageView img_advert_save;
    }
}
