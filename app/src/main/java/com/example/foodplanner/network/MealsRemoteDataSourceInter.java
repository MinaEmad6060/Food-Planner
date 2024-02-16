package com.example.foodplanner.network;

import com.example.foodplanner.Model.AreasList;
import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.IngredientsList;
import com.example.foodplanner.Model.MealList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsRemoteDataSourceInter {

    //Home Screen
    Observable<MealList> getRandomMealRemote();
    Observable<MealList> getMealsOfCategoryRemote(String category);

    Observable<MealList> getMealsOfAreaRemote(String category);

    Observable<MealList> getMealsOfIngredientsRemote(String category);


    //Search Screen
    Observable<MealList> getSearchMealsRemote(String query);
    Observable<CategoryList> getAllCategoriesRemote();
    Observable<AreasList> getAllAreasRemote();
    Observable<IngredientsList> getAllIngredientRemote();

//    Observable<MealList> getAllMealsOfCategoriesRemote();
}
