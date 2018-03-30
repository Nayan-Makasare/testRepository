package com.example.nayan_makasare.myimagedemo.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by nayan_makasare on 27/3/18.
 */

public class TempModel
{
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    String appName ;
    Drawable icon ;

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    String packname ;

}
