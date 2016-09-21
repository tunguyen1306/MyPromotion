package com.mypromotion.mypromotion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.Preference;
import com.mypromotion.mypromotion.view.activity.DetailAdvert;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertInBrandAdapter extends RecyclerView.Adapter<AdvertInBrandAdapter.SimpleViewHolder> {
    private final Context mContext;
    List<ListingDto> _list = new ArrayList<>();
    String from_activity;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ln_view;
        ImageView image;
        TextView title;
        TextView price;

        public SimpleViewHolder(View view) {
            super(view);
            ln_view = (LinearLayout) view.findViewById(R.id.ln_view);
            image = (ImageView) view.findViewById(R.id.gallery_img);
            title = (TextView) view.findViewById(R.id.gallery_title);
            price = (TextView) view.findViewById(R.id.gallery_price);
        }
    }

    public AdvertInBrandAdapter(Context context, List<ListingDto> list, String from_activity) {
        mContext = context;
        this._list = list;
        this.from_activity = from_activity;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_advert_in_brand_adapter, parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        if (_list.get(position).getAdvertId() == 0) {
            try {
                holder.image.setImageResource(R.drawable.ic_image);
            } catch (Exception ex) {
            }
        } else {
            Picasso.with(mContext).load(_list.get(position).getAdvertImg()).resize(180, 180).into(holder.image);
        }
        holder.title.setText(_list.get(position).getAdvertName());
        holder.price.setText(_list.get(position).getAdvertPrice());
        holder.ln_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);
                Intent intent_login=new Intent(mContext,DetailAdvert.class);
                ListingDto.IdAdvert=_list.get(position).getAdvertId();
                ListingDto.NameAdvert = _list.get(position).getAdvertName();
                ListingDto.IdAdvertCategory=_list.get(position).getAdvertCategoryId();
                ListingDto.IdAdvertBrand=_list.get(position).getAdvertBrandId();
                Preference.savePreference(mContext.getApplicationContext());
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent_login);


            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return _list.size();
        } catch (Exception ex) {
            return 0;
        }
    }
}
