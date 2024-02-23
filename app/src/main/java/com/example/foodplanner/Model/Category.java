package com.example.foodplanner.Model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("strCategory")
    private String name;
    @SerializedName("strCategoryThumb")
    private String thumbnail;

    public Category(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
