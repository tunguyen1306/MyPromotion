package com.mypromotion.mypromotion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.BrandDto;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.view.activity.DetailBrand;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertRelateAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<ListingDto> listingDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public AdvertRelateAdapter(Context context, List<ListingDto> AdvertRelateListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listingDtos = AdvertRelateListView;

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
            convertView=layoutInflater.inflate(R.layout.item_advert_relate,parent,false);
            listViewHolder.txtNameAdvertRelate=(TextView)convertView.findViewById(R.id.txtNameAdvertRelate);
            listViewHolder.txtAddressAdvertRelate=(TextView)convertView.findViewById(R.id.txtAddressAdvertRelate);
            listViewHolder.txtPriceAdvertRelate=(TextView)convertView.findViewById(R.id.txtPriceAdvertRelate);
            listViewHolder.imgAdvertRelate=(ImageView) convertView.findViewById(R.id.imgAdvertRelate);
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.txtNameAdvertRelate.setText(listingDtos.get(position).AdvertName);
        listViewHolder.txtAddressAdvertRelate.setText(listingDtos.get(position).AdvertStreet);
//        listViewHolder.txtPercentBrandRelate.setText(brandDtos.get(position).percent_brand_promotiom);
        Picasso.with(_Context).load(listingDtos.get(position).AdvertImg).error(R.drawable.ic_image).into(listViewHolder.imgAdvertRelate);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login=new Intent(_Context,DetailBrand.class);
                ListingDto.IdAdvert=listingDtos.get(position).getAdvertId();
                ListingDto.NameAdvert = listingDtos.get(position).getAdvertName();
                ListingDto.IdAdvertCategory = listingDtos.get(position).getAdvertCategoryId();
                ListingDto.IdAdvertBrand = listingDtos.get(position).getAdvertBrandId();
                Preference.savePreference(_Context.getApplicationContext());
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _Context.startActivity(intent_login);



            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameAdvertRelate,txtAddressAdvertRelate,txtPriceAdvertRelate;
        ImageView imgAdvertRelate;
    }
}
