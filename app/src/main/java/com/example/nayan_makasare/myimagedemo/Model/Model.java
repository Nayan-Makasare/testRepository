package com.example.nayan_makasare.myimagedemo.Model;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by nayan_makasare on 26/3/18.
 */

public class Model implements Serializable
{

    String imageurl ;
    int width ;
    int height ;
    String title ;
    String content ;
    String description ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public URI convertUrlTOUri()
    {
        return URI.create(getImageurl());
    }

}
