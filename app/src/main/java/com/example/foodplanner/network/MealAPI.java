package com.example.foodplanner.network;

import com.example.foodplanner.Model.MealList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealAPI {
    @GET("filter.php")
    Call<MealList> getByCategoryMealsAPI(@Query("c") String category);
}
