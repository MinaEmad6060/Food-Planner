package com.example.foodplanner.Model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("strIngredient")
    private String ingredientName;
    private String thumbnail;

    public Ingredient(String ingredientName, String thumbnail) {
        this.ingredientName = ingredientName;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
