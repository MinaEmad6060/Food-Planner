package com.example.foodplanner.Favourate.Presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface InterFavMealsPresenter {

    public Flowable<List<Meal>> getStoredDataDB();
    public void removeFavProduct(Meal meal);
}
