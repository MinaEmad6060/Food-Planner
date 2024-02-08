package com.example.foodplanner.HomeScreen.Presenter;

import com.example.foodplanner.HomeScreen.View.HomeFragmentInter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.network.CallBackInter;

import java.util.List;

public class HomeScreenPresenter implements
        HomeScreenPresenterInter, CallBackInter {

    private HomeFragmentInter homeFragmentInter;

    private MealRepositoryInter mealRepositoryInter;


    public HomeScreenPresenter(HomeFragmentInter interAllProductsView,
                                MealRepositoryInter interProductsRepository) {
        this.homeFragmentInter = interAllProductsView;
        this.mealRepositoryInter = interProductsRepository;
    }


    @Override
    public void getMeals(String query) {

        mealRepositoryInter.getAllMeals(this,query);
    }

    @Override
    public void getRandomMeal() {
        mealRepositoryInter.getRandomMeal(this);
    }

    @Override
    public void onSuccess(List<Meal> meals, String query) {
        homeFragmentInter.showData(meals,query);
    }

    @Override
    public void onSuccessRandom(List<Meal> meals) {
        homeFragmentInter.showRandomMeal(meals);
    }

    @Override
    public void onFail(String err) {
        //homeFragmentInter.showErr(err);
    }
}
