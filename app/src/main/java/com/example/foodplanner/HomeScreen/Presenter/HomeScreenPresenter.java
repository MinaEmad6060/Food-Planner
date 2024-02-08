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
    public void getMeals() {
        mealRepositoryInter.getAllProducts(this);
    }

    @Override
    public void onSuccess(List<Meal> products) {
        homeFragmentInter.showData(products);
    }

    @Override
    public void onFail(String err) {
        homeFragmentInter.showErr(err);
    }
}
