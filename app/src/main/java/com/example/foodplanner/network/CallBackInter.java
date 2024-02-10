package com.example.foodplanner.network;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface CallBackInter {
    public void onSuccessChicken(List<Meal> meals);
    public void onSuccessBeef(List<Meal> meals);

    public void onSuccessRandom(List<Meal> meals);
    public void onFail(String err);
}