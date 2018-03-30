package com.example.nayan_makasare.myimagedemo.Model;

/**
 * Created by nayan_makasare on 27/3/18.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mash {

    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("link")
    @Expose
    private String link;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}