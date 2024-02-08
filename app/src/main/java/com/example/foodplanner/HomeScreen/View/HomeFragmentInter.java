package com.example.foodplanner.HomeScreen.View;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface HomeFragmentInter {

    void showData(List<Meal> meals);

    void showErr(String err);
}
