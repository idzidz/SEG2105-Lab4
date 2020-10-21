package com.example.profilemanager;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Team {
    String name;
    String postCode;
    int img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
