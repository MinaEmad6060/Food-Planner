package com.example.foodplanner.HomeScreen.View;

import com.example.foodplanner.Model.Meal;
import java.util.List;

public interface HomeFragmentInter {
    void showChickenCategory(List<Meal> meals);
    void showBeefCategory(List<Meal> meals);
    void showSeaFoodCategory(List<Meal> meals);
    void showRandomMeal(List<Meal> meals);
    void showErr(String err);
}
