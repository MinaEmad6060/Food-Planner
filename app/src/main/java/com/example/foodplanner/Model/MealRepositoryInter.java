package com.example.foodplanner.Model;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealRepositoryInter {
    Observable<MealList> getRandomMealRepo();
    Observable<MealList> getMealsOfCategoryRepo(String category);


    Observable<MealList> getSearchMealsRepo(String query);
    Observable<CategoryList>  getAllCategoriesRepo();

    Observable<AreasList> getAllAreasRepo();

    Observable<IngredientsList> getAllIngredientRepo();


    Flowable<List<Meal>> getStoredProducts();

    void insertProduct(Meal meal);

    public void deleteProduct(Meal meal);



}
