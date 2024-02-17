package com.example.foodplanner.db.PlanDB;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface InterPlanLocalDataSource {
    Flowable<List<String>> getDayMealsData(String columnName);

    void insertMeal(Plan plan);



    void deleteSatMeal(String mealDetails);
    void deleteSunMeal(String mealDetails);
    void deleteMonMeal(String mealDetails);
    void deleteTueMeal(String mealDetails);
    void deleteWedMeal(String mealDetails);
    void deleteThMeal(String mealDetails);
    void deleteFriMeal(String mealDetails);


//    void deleteFromDayMeal(String columnName,String mealDetails);


}
