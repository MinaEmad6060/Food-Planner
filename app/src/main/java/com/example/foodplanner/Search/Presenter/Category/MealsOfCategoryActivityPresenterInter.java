package com.example.foodplanner.Search.Presenter.Category;

import com.example.foodplanner.Model.Meal;

public interface MealsOfCategoryActivityPresenterInter {
    void getAllMealsOfCategoriesPres(String category);
    void addFavMeal(Meal meal);
}
