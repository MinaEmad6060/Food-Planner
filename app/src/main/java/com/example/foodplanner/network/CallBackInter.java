package com.example.foodplanner.network;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;

import java.util.List;

public interface CallBackInter {
    public void onSuccessChicken(List<Meal> meals);
    public void onSuccessBeef(List<Meal> meals);
    public void onSuccessSeaFood(List<Meal> meals);

    public  void onSuccessCategory(List<Category> categories);
    public  void onSuccessSearch(List<Meal> meals);

    public void onSuccessRandom(List<Meal> meals);
    public void onFail(String err);
}
