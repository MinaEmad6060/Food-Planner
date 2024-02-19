package com.example.foodplanner.HomeScreen.Presenter;

import android.annotation.SuppressLint;

import com.example.foodplanner.HomeScreen.View.HomeFragmentInter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class HomeScreenPresenter implements HomeScreenPresenterInter{

    private HomeFragmentInter homeFragmentInter;

    private static MealRepositoryInter mealRepositoryInter;


    public HomeScreenPresenter(HomeFragmentInter interAllProductsView,
                                MealRepositoryInter interProductsRepository) {
        this.homeFragmentInter = interAllProductsView;
        this.mealRepositoryInter = interProductsRepository;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getMealsOfCategoryPres(String category) {
        Observable<MealList> observable =
                mealRepositoryInter.getMealsOfCategoryRepo(category);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            if(category.equals("Chicken")){
                                homeFragmentInter.showChickenCategory(mealList.meals);
                            } else if (category.equals("Beef")) {
                                homeFragmentInter.showBeefCategory(mealList.meals);
                            } else if (category.equals("Seafood")) {
                                homeFragmentInter.showSeaFoodCategory(mealList.meals);
                            }
                        },
                        err -> {}
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void getRandomMealPres() {
        Observable<MealList> observable =
                mealRepositoryInter.getRandomMealRepo();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            homeFragmentInter.showRandomMeal(mealList.meals);
                        },
                        err -> {}
                );
    }


    @Override
    public void addFavMeal(Meal meal) {
        mealRepositoryInter.insertMeals(meal);
    }

}
