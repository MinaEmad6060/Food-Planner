package com.example.foodplanner.Model;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("idMeal")
    private long id;
    @SerializedName("strMeal")
    private String name;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strArea")
    private String area;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String thumbnail;
    //@SerializedName("strIngredient")
    //private String[] ingredients;

    public Meal(long id, String name, String category, String area,
                String instructions, String thumbnail, String[] ingredients) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
        //this.ingredients = ingredients;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

//    public void setIngredients(String[] ingredients) {
//        this.ingredients = ingredients;
//    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getThumbnail() {
        return thumbnail;
    }

//    public String[] getIngredients() {
//        return ingredients;
//    }
}
