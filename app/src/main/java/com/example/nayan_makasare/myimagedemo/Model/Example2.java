package com.example.nayan_makasare.myimagedemo.Model;

/**
 * Created by nayan_makasare on 27/3/18.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example2 {

    @SerializedName("baseurl")
    @Expose
    private String baseurl;
    @SerializedName("link")
    @Expose
    private List<String> link = null;

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public List<String> getLink() {
        return link;
    }

    public void setLink(List<String> link) {
        this.link = link;
    }

}
