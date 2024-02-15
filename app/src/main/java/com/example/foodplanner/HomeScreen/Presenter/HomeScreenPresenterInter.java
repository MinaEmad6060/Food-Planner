package com.example.foodplanner.HomeScreen.Presenter;

import com.example.foodplanner.Model.Meal;

public interface HomeScreenPresenterInter {
    void getMealsOfCategoryPres(String query);
    void getRandomMealPres();

//
//    public void getRandomMeal();

    public void addFavMeal(Meal meal);
}
