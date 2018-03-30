package com.example.nayan_makasare.myimagedemo.Model;

import android.graphics.drawable.Drawable;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by nayan_makasare on 27/3/18.
 */

public class AllAppModel
{
    Drawable icon ;
    String appName ;
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AllAppModel(String name , Drawable icon)
    {
        appName = name ;
        this.icon = icon ;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public URI convertUrlToUri()
    {
        try {
            return URI.create(getImageUrl());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
