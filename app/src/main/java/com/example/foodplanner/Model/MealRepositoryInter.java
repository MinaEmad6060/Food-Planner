package com.example.foodplanner.Model;

import java.util.List;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealRepositoryInter {
    Observable<MealList> getRandomMealRepo();
    Observable<MealList> getMealsOfCategoryRepo(String category);
    Observable<MealList> getAllMealsOfAreasRepo(String category);
    Observable<MealList> getAllMealsIngredientRepo(String category);

    Observable<MealList> getSearchMealsRepo(String query);
    Observable<CategoryList>  getAllCategoriesRepo();
    Observable<AreasList> getAllAreasRepo();
    Observable<IngredientsList> getAllIngredientRepo();
    Flowable<List<Meal>> getStoredMeals();
    Flowable<List<Plan>> getAllPlansRepo();
    void insertMeals(Meal meal);
    void deleteMeals(Meal meal);
    void deleteAllFavMeals();

    void deleteAllPlanMeals();
    Observable<List<String>> getDayMealsRepo(String columnName);
    void insertDayMeal(Plan plan);
    void deleteSatMeal(String mealDetails);
    void deleteSunMeal(String mealDetails);
    void deleteMonMeal(String mealDetails);
    void deleteTueMeal(String mealDetails);
    void deleteWedMeal(String mealDetails);
    void deleteThMeal(String mealDetails);
    void deleteFriMeal(String mealDetails);
}
