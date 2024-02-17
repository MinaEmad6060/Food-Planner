package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface SearchFragmentPresenterInter {

    void getSearchMealsPres(String query);

    void addPlanMeal(Meal meal,String day);

}
