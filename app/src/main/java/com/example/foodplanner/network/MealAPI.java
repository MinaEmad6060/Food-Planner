package com.example.foodplanner.network;

import com.example.foodplanner.Model.AreasList;
import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.IngredientsList;
import com.example.foodplanner.Model.MealList;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealAPI {
    @GET("random.php")
    Observable<MealList> getRandomMealAPI();
    @GET("filter.php")
    Observable<MealList> getMealsOfCategoryAPI(@Query("c") String category);
    @GET("filter.php")
    Observable<MealList> getMealsOfAreaAPI(@Query("a") String category);
    @GET("filter.php")
    Observable<MealList> getMealsOfIngredientsAPI(@Query("i") String category);


    @GET("search.php")
    Observable<MealList> getSearchMealsAPI(@Query("s") String category);
    @GET("categories.php")
    Observable<CategoryList> getAllCategoriesAPI();
    @GET("list.php")
    Observable<AreasList> getAllAreasAPI(@Query("a") String category);
    @GET("list.php")
    Observable<IngredientsList> getAllIngredientsAPI(@Query("i") String category);

}
