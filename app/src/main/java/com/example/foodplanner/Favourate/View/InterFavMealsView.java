package com.example.foodplanner.Favourate.View;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface InterFavMealsView {
    void showData(Flowable<List<Meal>> meals);
}

