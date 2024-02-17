package com.example.foodplanner.db.FavDB;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface InterFavLocalDataSource {
    Flowable<List<Meal>> getAllMealsData();
    //LiveData<List<Product>> getAllProductsData();

    void insertMealData(Meal meal);

    void deleteMealData(Meal meal);

}
