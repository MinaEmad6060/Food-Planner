package com.example.foodplanner.HomeScreen.Presenter;

import com.example.foodplanner.Model.Meal;

public interface HomeScreenPresenterInter {
    void getRandomMealPres();
    void getMealsOfCategoryPres(String query);

    public void addFavMeal(Meal meal);
}
