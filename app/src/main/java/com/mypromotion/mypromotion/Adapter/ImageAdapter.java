package com.mypromotion.mypromotion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mypromotion.mypromotion.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by TuNguyen on 09/11/2016.
 */
public class ImageAdapter extends PagerAdapter {
    //private CustomProgressDialog myProgress;

    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    private ImageLoader mImageLoader;


    public ImageAdapter(Context context,ArrayList<String> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);



    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int  position) {
        View imageLayout = inflater.inflate(R.layout.image_detail, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
//        myProgress = new CustomProgressDialog(context);
//        myProgress.show("");
        DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.empty)
                .showImageOnFail(R.drawable.fail)
                .showImageOnLoading(R.drawable.loading)
                .resetViewBeforeLoading(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .diskCacheExtraOptions(1280, 780, null)
                .defaultDisplayImageOptions(mDisplayImageOptions)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(50 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .threadPoolSize(2)
                .writeDebugLogs()
                .build();
        mImageLoader=ImageLoader.getInstance();
        mImageLoader.getInstance().init(config);
        mImageLoader.getInstance().handleSlowNetwork(true);
//        mImageLoader.clearDiskCache();
//        mImageLoader.clearMemoryCache();
        mImageLoader.displayImage(IMAGES.get(position), imageView,mDisplayImageOptions,new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {


            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                myProgress.dismiss("");

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                myProgress.dismiss("");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
//                myProgress.dismiss("");
            }
        });

        view.addView(imageLayout, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(context, ZoomImage.class);
//                i.putStringArrayListExtra("image_url", (ArrayList<String>) IMAGES);
//                i.putExtra("currentImage",position);
//                context.startActivity(i);
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
