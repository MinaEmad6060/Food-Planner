package com.example.foodplanner.network;

import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.MealList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealAPI {
    @GET("filter.php")
    Call<MealList> getChickenCategoryMealsAPI(@Query("c") String category);

    @GET("random.php")
    Call<MealList> getRandomMealAPI();
    @GET("categories.php")
    Call<CategoryList> getAllCategoriesAPI();

    @GET("search.php")
    Observable<MealList> getSearchMealsAPI(@Query("s") String category);
}
