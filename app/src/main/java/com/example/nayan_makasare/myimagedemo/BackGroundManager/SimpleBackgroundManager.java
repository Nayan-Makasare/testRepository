package com.example.nayan_makasare.myimagedemo.BackGroundManager;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.app.BackgroundManager;
import android.util.DisplayMetrics;

import com.example.nayan_makasare.myimagedemo.R;


/**
 * Created by nayan_makasare on 26/3/18.
 */

public class SimpleBackgroundManager
{
    Activity mActivity ;
    private BackgroundManager mbackgroundManager ;

    Drawable def ;
    public SimpleBackgroundManager(Activity activity)
    {
        mActivity = activity;
        def = activity.getResources().getDrawable(R.drawable.logo3);
        mbackgroundManager = BackgroundManager.getInstance(activity);
        mbackgroundManager.attach(activity.getWindow());
        activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
    }


    public  void updateBackgroundImage(Drawable drawable)
    {
             mbackgroundManager.setDrawable(drawable);
    }

    public void clearbackground()
    {
        mbackgroundManager.setDrawable(def);
    }


}
