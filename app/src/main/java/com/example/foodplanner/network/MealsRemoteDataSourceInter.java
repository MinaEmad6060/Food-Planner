package com.example.foodplanner.network;

import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.MealList;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRemoteDataSourceInter {

    //Home Screen
    Observable<MealList> getRandomMealRemote();
    Observable<MealList> getMealsOfCategoryRemote(String category);


    //Search Screen
    Observable<MealList> getSearchMealsRemote(String query);
    Observable<CategoryList> getAllCategoriesRemote();

}
