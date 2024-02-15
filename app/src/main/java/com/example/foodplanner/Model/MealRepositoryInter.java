package com.example.foodplanner.Model;

import com.example.foodplanner.network.CallBackInter;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealRepositoryInter {
    Observable<MealList> getRandomMealRepo();

    Observable<MealList> getMealsOfCategoryRepo(String category);
    Observable<MealList> getSearchMealsRepo(String query);
    Observable<CategoryList>  getAllCategoriesRepo();




    Flowable<List<Meal>> getStoredProducts();

    void insertProduct(Meal meal);

    public void deleteProduct(Meal meal);



}
