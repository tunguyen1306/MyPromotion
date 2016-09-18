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
import com.mypromotion.mypromotion.view.activity.DetailAdvert;
import com.mypromotion.mypromotion.view.activity.DetailBrand;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class BrandRelateAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<BrandDto> brandDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public BrandRelateAdapter(Context context, List<BrandDto> BrandRelateListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        brandDtos = BrandRelateListView;

    }
    @Override
    public int getCount() {
        return brandDtos.size();
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
            convertView=layoutInflater.inflate(R.layout.item_brand_relate,parent,false);
            listViewHolder.txtNameBrandRelate=(TextView)convertView.findViewById(R.id.txtNameBrandRelate);
            listViewHolder.txtAddressBrandRelate=(TextView)convertView.findViewById(R.id.txtAddressBrandRelate);
            listViewHolder.txtPercentBrandRelate=(TextView)convertView.findViewById(R.id.txtPercentBrandRelate);
            listViewHolder.imgBrandRelate=(ImageView) convertView.findViewById(R.id.imgBrandRelate);
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.txtNameBrandRelate.setText(brandDtos.get(position).name_brand_promotiom);
        listViewHolder.txtAddressBrandRelate.setText(brandDtos.get(position).address_brand_promotiom);
//        listViewHolder.txtPercentBrandRelate.setText(brandDtos.get(position).percent_brand_promotiom);
        Picasso.with(_Context).load(brandDtos.get(position).img_brand_promotiom).error(R.drawable.ic_image).into(listViewHolder.imgBrandRelate);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login=new Intent(_Context,DetailBrand.class);
                BrandDto.idBrandPromotiom=brandDtos.get(position).getId_brand_promotiom();
                BrandDto.NameBrandPromotiom = brandDtos.get(position).getName_brand_promotiom();
                BrandDto.idCategory = brandDtos.get(position).getCategory_id_brand_promotion();
                Preference.savePreference(_Context.getApplicationContext());
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _Context.startActivity(intent_login);



            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameBrandRelate,txtAddressBrandRelate,txtPercentBrandRelate;
        ImageView imgBrandRelate;
    }
}
