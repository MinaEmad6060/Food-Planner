package com.example.foodplanner.HomeScreen.Presenter;

import com.example.foodplanner.Model.Meal;

public interface HomeScreenPresenterInter {
    public void getCategoryMeals();

    public void getRandomMeal();

    public void addFavMeal(Meal meal);
    //public void addFavProduct(Meal meal);
}
