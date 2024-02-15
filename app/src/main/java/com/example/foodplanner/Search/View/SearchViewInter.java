package com.example.foodplanner.Search.View;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface SearchViewInter {
    void showCategories(List<Category> categories);

    void showSearchMeals(List<Meal> searchMeals);
}
