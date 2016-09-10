package com.mypromotion.mypromotion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertSaveAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<ListingDto> listingDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public AdvertSaveAdapter(Context context, List<ListingDto> advertSaveListView) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            listViewHolder = new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_advert_save,parent,false);
            listViewHolder.txt_tile=(TextView)convertView.findViewById(R.id.txtAdvert_name);
            listViewHolder.txtAdvertSaveAddress=(TextView)convertView.findViewById(R.id.txtAdvertSaveAddress);
            listViewHolder.txtAdvertSavePrice=(TextView)convertView.findViewById(R.id.txtAdvertSavePrice);
            listViewHolder.img_advert_save=(ImageView) convertView.findViewById(R.id.img_advert_save);
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.txt_tile.setText(listingDtos.get(position).AdvertName);
        listViewHolder.txtAdvertSaveAddress.setText(listingDtos.get(position).AdvertStreet);
        listViewHolder.txtAdvertSavePrice.setText(listingDtos.get(position).AdvertPrice);
        Picasso.with(_Context).load(listingDtos.get(position).AdvertImg).error(R.drawable.ic_image).into(listViewHolder.img_advert_save);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListingDto listingDto=listingDtos.get(position);
                //Toast.makeText(_Context,_Context.getResources().getInteger(listingDto.AdvertId), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txt_tile,txtAdvertSaveAddress,txtAdvertSavePrice;
        ImageView img_advert_save;
    }
}
