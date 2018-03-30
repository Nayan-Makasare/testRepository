package com.example.nayan_makasare.myimagedemo.BackGroundManager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.v17.leanback.app.BackgroundManager;
import android.util.DisplayMetrics;

import com.example.nayan_makasare.myimagedemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;
import java.util.logging.Handler;

import static android.os.Looper.getMainLooper;

/**
 * Created by nayan_makasare on 26/3/18.
 */

public class PicasoBackgroundManager
{
    Activity mActivity ;

    private  BackgroundManager mbackgroundManager  ;

    PiccasoTarget mbackgroundTarget ;

    private DisplayMetrics metrics;
    public PicasoBackgroundManager(Activity activity)
    {
        mActivity = activity;
       // def = activity.getResources().getDrawable(R.drawable.logo3);
        mbackgroundManager = BackgroundManager.getInstance(activity);
        mbackgroundManager.attach(activity.getWindow());
        mbackgroundTarget = new PiccasoTarget(mbackgroundManager);
        metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);


    }


    public  void uploadImage(String url)
    {
       URI uri =  URI.create(url);

        Picasso.with(mActivity)
                .load(uri.toString())
                .resize(metrics.widthPixels , metrics.heightPixels)
                .centerCrop()
                .into(mbackgroundTarget);
    }

    public class PiccasoTarget implements Target
    {
        BackgroundManager manager;
        PiccasoTarget(BackgroundManager backgroundManager)
        {
            manager = backgroundManager ;

        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
        {
            manager.setBitmap(bitmap);

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
                manager.setDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

}
