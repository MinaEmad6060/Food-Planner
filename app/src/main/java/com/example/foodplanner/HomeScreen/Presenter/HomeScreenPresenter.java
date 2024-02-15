package com.example.foodplanner.HomeScreen.Presenter;

import android.annotation.SuppressLint;

import com.example.foodplanner.HomeScreen.View.HomeFragmentInter;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.network.CallBackInter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class HomeScreenPresenter implements
        HomeScreenPresenterInter, CallBackInter {

    private HomeFragmentInter homeFragmentInter;

    private static MealRepositoryInter mealRepositoryInter;


    public HomeScreenPresenter(HomeFragmentInter interAllProductsView,
                                MealRepositoryInter interProductsRepository) {
        this.homeFragmentInter = interAllProductsView;
        this.mealRepositoryInter = interProductsRepository;
    }


//    @Override
//    public void getChickenMeals(String query) {
//
//        mealRepositoryInter.getAllChickenMeals(this,query);
//    }
//
//    @Override
//    public void getCategoryMeals(String query) {
//        mealRepositoryInter.getAllBeefMeals(this,query);
//
//    }

//    @Override
//    public void getCategoryMeals() {
//        mealRepositoryInter.getAllMeals(this);
//    }

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

//    @Override
//    public void getRandomMeal() {
//        mealRepositoryInter.getRandomMeal(this);
//    }

    @Override
    public void addFavMeal(Meal meal) {
        mealRepositoryInter.insertProduct(meal);
    }

//    @Override
//    public static void addFavMeal(Meal meal) {
//        mealRepositoryInter.insertProduct(meal);
//    }

    @Override
    public void onSuccessChicken(List<Meal> meals) {
            homeFragmentInter.showChickenCategory(meals);
    }

    @Override
    public void onSuccessBeef(List<Meal> meals) {
        homeFragmentInter.showBeefCategory(meals);
    }

    @Override
    public void onSuccessSeaFood(List<Meal> meals) {
        homeFragmentInter.showSeaFoodCategory(meals);
    }

    @Override
    public void onSuccessCategory(List<Category> categories) {

    }

    @Override
    public void onSuccessSearch(List<Meal> meals) {

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
