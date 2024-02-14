package com.example.foodplanner.db;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface InterFavLocalDataSource {
    Flowable<List<Meal>> getAllMealsData();
    //LiveData<List<Product>> getAllProductsData();

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);

}
