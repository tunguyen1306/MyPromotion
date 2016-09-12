package com.mypromotion.mypromotion.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.mypromotion.mypromotion.R;
import com.mypromotion.mypromotion.model.ListingDto;
import com.mypromotion.mypromotion.model.SlideDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 09/13/2016.
 */
public class SlideAdapter extends PagerAdapter {

    List<SlideDto> list_slide;
    private LayoutInflater inflater;
    private Context context;
    final Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18001207"));

    public SlideAdapter(Context context, List<SlideDto> list_slide) {
        this.context = context;
        this.list_slide=list_slide;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list_slide.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int  position) {
        View imageLayout = inflater.inflate(R.layout.slide_image, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.SlideImage);

        String a="http://www.muabannhadat.vn/uploads/images/005/023/808/e9D5XQuWLU-Z_ATx2XW-Ag_2.jpg";
//        Picasso.with(context).load(list_banellisting.get(position).getAnh_duan()).into(imageView);
        Picasso.with(context).load(a).into(imageView);

        view.addView(imageLayout, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                v.startAnimation(animation);

            }
        });
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
