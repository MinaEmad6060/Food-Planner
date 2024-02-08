package com.example.foodplanner.network;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface CallBackInter {
    public void onSuccess(List<Meal> meals,String query);
    public void onSuccessRandom(List<Meal> meals);
    public void onFail(String err);
}
