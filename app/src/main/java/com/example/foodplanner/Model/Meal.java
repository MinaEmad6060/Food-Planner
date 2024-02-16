package com.example.foodplanner.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "meal_table")
public class Meal {
    @PrimaryKey
    @NonNull
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
    @SerializedName("strIngredient1")
    private String ingredient1;
    @SerializedName("strIngredient2")
    private String ingredient2;
    @SerializedName("strIngredient3")
    private String ingredient3;
    @SerializedName("strIngredient4")
    private String ingredient4;
    @SerializedName("strIngredient5")
    private String ingredient5;
    @SerializedName("strIngredient6")
    private String ingredient6;
    @SerializedName("strIngredient7")
    private String ingredient7;
    @SerializedName("strIngredient8")
    private String ingredient8;
    @SerializedName("strIngredient9")
    private String ingredient9;
    @SerializedName("strIngredient10")
    private String ingredient10;

    @SerializedName("strIngredient11")
    private String ingredient11;
    @SerializedName("strIngredient12")
    private String ingredient12;
    @SerializedName("strIngredient13")
    private String ingredient13;
    @SerializedName("strIngredient14")
    private String ingredient14;
    @SerializedName("strIngredient15")
    private String ingredient15;
    @SerializedName("strIngredient16")
    private String ingredient16;
    @SerializedName("strIngredient17")
    private String ingredient17;
    @SerializedName("strIngredient18")
    private String ingredient18;
    @SerializedName("strIngredient19")
    private String ingredient19;
    @SerializedName("strIngredient20")
    private String ingredient20;

    @SerializedName("strYoutube")
    private String videoLink;


    public Meal(long id, String name, String category, String area,
                String instructions, String thumbnail) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
        this.ingredient1 = "";
        this.ingredient2 = "";
        this.ingredient3 = "";
        this.ingredient4 = "";
        this.ingredient5 = "";
        this.ingredient6 = "";
        this.ingredient7 = "";
        this.ingredient8 = "";
        this.ingredient9 = "";
        this.ingredient10 = "";
        this.ingredient11 = "";
        this.ingredient12 = "";
        this.ingredient13 = "";
        this.ingredient14 = "";
        this.ingredient15 = "";
        this.ingredient16 = "";
        this.ingredient17 = "";
        this.ingredient18 = "";
        this.ingredient19 = "";
        this.ingredient20 = "";
        this.videoLink="";

    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
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

    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }

    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }

    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }

    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }

    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }

    public void setIngredient16(String ingredient16) {
        this.ingredient16 = ingredient16;
    }

    public void setIngredient17(String ingredient17) {
        this.ingredient17 = ingredient17;
    }

    public void setIngredient18(String ingredient18) {
        this.ingredient18 = ingredient18;
    }

    public void setIngredient19(String ingredient19) {
        this.ingredient19 = ingredient19;
    }

    public void setIngredient20(String ingredient20) {
        this.ingredient20 = ingredient20;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public String getIngredient16() {
        return ingredient16;
    }

    public String getIngredient17() {
        return ingredient17;
    }

    public String getIngredient18() {
        return ingredient18;
    }

    public String getIngredient19() {
        return ingredient19;
    }

    public String getIngredient20() {
        return ingredient20;
    }

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

    public String getIngredient1() {
        return ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }
}
