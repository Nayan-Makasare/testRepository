package com.example.nayan_makasare.myimagedemo.Model;

/**
 * Created by nayan_makasare on 26/3/18.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Property {

    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("mash")
    @Expose
    private List<Mash> mash = null;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Mash> getMash() {
        return mash;
    }

    public void setMash(List<Mash> mash) {
        this.mash = mash;
    }

}
