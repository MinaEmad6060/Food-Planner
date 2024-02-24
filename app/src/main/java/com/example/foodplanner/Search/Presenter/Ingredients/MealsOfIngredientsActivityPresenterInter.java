package com.example.foodplanner.Search.Presenter.Ingredients;

import com.example.foodplanner.Model.Meal;

public interface MealsOfIngredientsActivityPresenterInter {
    void getAllMealsOfIngredientsPres(String category);
    void addFavMeal(Meal meal);

}
