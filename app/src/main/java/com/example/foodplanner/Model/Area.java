package com.example.foodplanner.Model;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("strArea")
    private String areaName;
    public Area(String areaName) {
        this.areaName = areaName;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
