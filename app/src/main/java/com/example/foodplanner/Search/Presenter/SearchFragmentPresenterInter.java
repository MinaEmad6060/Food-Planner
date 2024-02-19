package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface SearchFragmentPresenterInter {

    void getSearchMealsPres(String query);

    void getDayMealsPres(String columnName);

    void addPlanMeal(Meal meal,String day);

    void removePlanMeal(String mealDetails,String day);

}
