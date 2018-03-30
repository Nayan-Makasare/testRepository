package com.example.nayan_makasare.myimagedemo.Model;

/**
 * Created by nayan_makasare on 26/3/18.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("imagebaseUrl")
    @Expose
    private String imagebaseUrl;
    @SerializedName("properties")
    @Expose
    private List<Property> properties = null;

    public String getImagebaseUrl() {
        return imagebaseUrl;
    }

    public void setImagebaseUrl(String imagebaseUrl) {
        this.imagebaseUrl = imagebaseUrl;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
